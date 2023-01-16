package video;

import java.awt.Image;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameGrabber.Exception;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class ReadVideo {
	private FFmpegFrameGrabber grabber;
	private long duration;
	private double fps;
	private SourceDataLine dataline;
	public ReadVideo(File videoFile) {
		this.grabber = new FFmpegFrameGrabber(videoFile);
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public double getFramePerSecond() {
		return this.fps;
	}
	
	public VideoPackage[] loadVideo() {
		VideoPackage[] videoPackages = null;
		try {
			this.grabber.start();
			AudioFormat format = new AudioFormat(this.grabber.getSampleRate(), 16,
					this.grabber.getAudioChannels(), true, true);
			DataLine.Info datainfo = new DataLine.Info(SourceDataLine.class, format);
			this.dataline = (SourceDataLine) AudioSystem.getLine(datainfo);
			this.duration = grabber.getLengthInTime();
			this.fps = grabber.getVideoFrameRate();
			ArrayList<VideoPackage> packageList = new ArrayList<VideoPackage>();
			this.grabClips(packageList);
			videoPackages = new VideoPackage[this.grabber.getLengthInAudioFrames() + this.grabber.getLengthInVideoFrames()];
			videoPackages = packageList.toArray(videoPackages);
			this.sortPackages(videoPackages);
			this.grabber.stop();
			this.grabber.close();
			this.grabber.release();
		}
		
		catch (org.bytedeco.javacv.FrameGrabber.Exception | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		return videoPackages;
	}
	
	private void grabClips(ArrayList<VideoPackage> packageList) throws Exception {
		LoadVideoFrame loadFrame = new LoadVideoFrame(this.grabber.getLengthInAudioFrames() + this.grabber.getLengthInVideoFrames() - 128);
		loadFrame.setVisible(true);
		Java2DFrameConverter converter;
		Frame frame;
		VideoPackage videoPackage;
		ShortBuffer shortBuffer;
		ByteBuffer byteBuffer;
		Image image;
		int j = 0;
		while((frame = grabber.grab()) != null) {

			videoPackage = new VideoPackage(frame.timestamp);
			if(frame.samples != null) {
				shortBuffer = (ShortBuffer) frame.samples[0];
				shortBuffer.rewind();
				byteBuffer = ByteBuffer.allocate(shortBuffer.capacity() * 2);
				for(int i = 0; i < shortBuffer.capacity(); i ++) {
					byteBuffer.putShort(shortBuffer.get(i));
				}
				videoPackage.setSamples(byteBuffer.array());;
				byteBuffer.clear();
			}
			if(frame.image != null) {
				converter = new Java2DFrameConverter(); ;
				image = converter.convert(frame);
				videoPackage.setImage(image);
				converter.close();
				image.flush();
			}
			loadFrame.record(j);
			packageList.add(videoPackage);
			frame.close();
			j ++;
		}
	}
	
	private void sortPackages(VideoPackage[] packages) {
		VideoPackage tmp;
		for(int i = 0; i < packages.length - 1; i ++) {
			boolean flag = false;
			for(int j = 0; j < packages.length - 1 - i; j ++ ) {
				if(packages[j].getTimeStamp() > packages[j + 1].getTimeStamp() ) {
					tmp = packages[j];
					packages[j] = packages[j + 1];
					packages[j + 1] = tmp;
					flag = true;
				}
			}
			if(!flag) break;
		}
		
	}
	
	public SourceDataLine getDataLine() {
		return dataline;
	}
	
	
	class VideoPackage{
		private Image image;
		private byte[] samples;
		private long timeStamp;
		
		public VideoPackage(long timeStamp) {
			this.timeStamp = timeStamp;
		}
		
		public void setSamples(byte[] samples) {
			this.samples = samples;
		}
		
		public void setImage(Image image) {
			this.image = image;
		}
		
		public Image getImage() {
			return this.image;
		}
		
		public long getTimeStamp() {
			return this.timeStamp;
		}
		
		public byte[] getSamples() {
			return this.samples;
		}
	}
}
