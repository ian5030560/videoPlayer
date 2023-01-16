package video;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JPanel;
import video.ReadVideo.VideoPackage;

public class VideoPanel extends JPanel{
	public static int VIDEO_END;
	public long duration;
	private Image videoImage;
	private SourceDataLine dataline;
	private Thread videoThread;
	private boolean running;
	private VideoPackage[] packages;
	public VideoSignal<Integer> processSignal = new VideoSignal<Integer>(int.class);
	private FloatControl audioControl;
	private int position = 0;
	private boolean loop = false;
	private float volume;
	
	public VideoPanel() {
		this.initThread();
	}	
	
	private void initThread() {
		this.videoThread = new Thread() {
			@Override
			public void run() {
				excuteVideo();
			}
		};
	}
	
	private void excuteVideo(){
		
		try {	
			dataline.open(dataline.getFormat());
			this.audioControl = (FloatControl) dataline.getControl(FloatControl.Type.MASTER_GAIN);	
			this.audioControl.setValue(this.volume);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		dataline.start();
		
		do {
			try {
				videoProcess();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		while(loop);
		this.running = false;
		this.processSignal.shoot(VIDEO_END);
	}
	
	private void videoProcess() throws InterruptedException {
		this.position = 0;
		long currentTime = System.currentTimeMillis();
		while(this.position < this.packages.length) {
			long timeStamp = System.currentTimeMillis() - currentTime;
			
			if(!running) {
				Thread.sleep(1);
				currentTime = System.currentTimeMillis() - timeStamp;
				continue;
			}
			
			VideoPackage videoPackage = this.packages[this.position];
			if(timeStamp * 1000 < videoPackage.getTimeStamp()) {
				Thread.sleep((videoPackage.getTimeStamp() - timeStamp * 1000) / 1000);
			}
			
			this.processSignal.shoot((int)videoPackage.getTimeStamp());
			
			if(videoPackage.getSamples() != null) {
				byte[] audio = videoPackage.getSamples();
				dataline.write(audio, 0, audio.length);
			}
			
			if(videoPackage.getImage() != null) {
				Image image = videoPackage.getImage();
				image = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
				this.videoImage = image;
				repaint();
			}
			this.position ++;
		}
		this.videoImage = null;
		repaint();
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public void fetchVideo(File video) {
		ReadVideo reader = new ReadVideo(video);
		this.packages = reader.loadVideo();
		this.dataline = reader.getDataLine();
		this.duration = reader.getDuration();
		VIDEO_END = (int) this.duration;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.videoImage != null) {
			g.drawImage(this.videoImage, 0, 0, null);
		}
	}
	
	public void play() {
		running = true;
		if(!this.videoThread.isAlive()) {
			try {
				this.videoThread.start();
			}
			catch(IllegalThreadStateException e){
				this.initThread();
				this.videoThread.start();
			}
		}
	}
	
	public void setLoop(boolean value) {
		this.loop = value;
	}
	
	public int getPosition() {
		return (int) this.packages[this.position].getTimeStamp();
	}
	
	public void stop() {
		running = false;
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
		if(this.videoThread.isAlive()) {
			this.audioControl.setValue(this.volume);
		}
	}
		
	public void setPosition(int position) {
		if(this.videoThread.isAlive()) {
			if(position > 0 && position < this.duration) {
				if(this.packages[this.position].getTimeStamp() > position) {
					while(this.packages[this.position].getTimeStamp() > position) {
						this.position --;
					}
				}
				else {
					while(this.packages[this.position].getTimeStamp() < position) {
						this.position ++;
					}
				}	
			}
		}
	}
	
	public class VideoSignal<E>{
		private ArrayList<Method> methods = new ArrayList<Method>();
		private ArrayList<Object> targets = new ArrayList<Object>();
		private Class<?> paramemterType;
		
		public VideoSignal(Class<?> paramemterType) {
			this.paramemterType = paramemterType;
		}
		
		public void shoot(E value) {
			try {
				for(int i = 0; i < this.methods.size(); i ++) {
					Method method = this.methods.get(i);
					method.invoke(this.targets.get(i), value);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void link(Object target, String methodName) {
			try {
				this.targets.add(target);
				Method method = target.getClass().getDeclaredMethod(methodName, paramemterType);
				this.methods.add(method);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
