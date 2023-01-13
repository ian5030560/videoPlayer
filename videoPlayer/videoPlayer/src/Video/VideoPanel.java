package Video;

import java.awt.Graphics;
import java.awt.Image;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JPanel;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import Video.ReadVideo.VideoPackage;

public class VideoPanel extends JPanel{
	public long duration;
	public Image videoImage;
	private SourceDataLine dataline;
	private Thread videoThread;
	private boolean running;
	private VideoPackage[] packages;
	
	public VideoPanel() {
		this.videoThread = new Thread() {
			@Override
			public void run() {
				excuteVideo();
			}
		};
		
	}
	
	private void excuteVideo() {
		
		try {
			dataline.open(dataline.getFormat());
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		dataline.start();
		
		int i = 0;
		long currentTime = System.currentTimeMillis();
		while(i < this.packages.length) {
			if(!running)continue;
			
			VideoPackage videoPackage = this.packages[i];
			if((System.currentTimeMillis() - currentTime) * 1000 < videoPackage.getTimeStamp()) {
				try {
					Thread.sleep(videoPackage.getTimeStamp() - (System.currentTimeMillis() - currentTime) * 1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			if(videoPackage.getSamples() != null) {
				byte[] audio = this.getAudio(videoPackage.getSamples());
				dataline.write(audio, 0, audio.length);
			}
			
			if(videoPackage.getImage() != null) {
				Image image = videoPackage.getImage();
				image = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
				this.videoImage = image;
				repaint();
			}
			i ++;
		}
		this.videoThread = null;
		this.running = false;
		this.videoImage = null;
		repaint();
	}
	
	private byte[] getAudio(Buffer[] samples) {
		ShortBuffer shortBuffer = (ShortBuffer) samples[0];
		shortBuffer.rewind();
		ByteBuffer byteBuffer = ByteBuffer.allocate(shortBuffer.capacity() * 2);
		for(int i = 0; i < shortBuffer.capacity(); i ++) {
			byteBuffer.putShort(shortBuffer.get(i));
		}
		return byteBuffer.array();
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public void fetchVideo(String video) {
		ReadVideo reader = new ReadVideo(video);
		this.packages = reader.loadVideo();
		this.dataline = reader.getDataLine();
		this.duration = reader.getDuration();
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
			this.videoThread.start();
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public Thread getThread() {
		return this.videoThread;
	}
	
}
