package Video;

import java.awt.Image;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class ReadVideo {
	private FFmpegFrameGrabber grabber;
	private long duration;
	private double fps;
	private SourceDataLine dataline;
	public ReadVideo(String videoFile) {
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
			Java2DFrameConverter converter;
			
			Frame frame;
			while((frame = grabber.grab()) != null) {
				VideoPackage videoPackage = new VideoPackage(frame.timestamp);
				if(frame.samples != null) {
					videoPackage.setSamples(frame.samples);;
				}
				if(frame.image != null) {
					converter = new Java2DFrameConverter();
					Image image = converter.convert(frame);
					videoPackage.setImage(image);
					converter.close();
				}
				packageList.add(videoPackage);
			}
			
			videoPackages = new VideoPackage[this.grabber.getLengthInAudioFrames() + this.grabber.getLengthInVideoFrames()];
			videoPackages = packageList.toArray(videoPackages);
			this.sortPackages(videoPackages);
			this.grabber.close();
			this.grabber.release();
			
			for(VideoPackage p: videoPackages) {
				System.out.println(p.getTimeStamp());
			}
			
		}
		
		catch (org.bytedeco.javacv.FrameGrabber.Exception | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		return videoPackages;
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
		private Buffer[] samples;
		private long timeStamp;
		
		public VideoPackage(long timeStamp) {
			this.timeStamp = timeStamp;
		}
		
		public void setSamples(Buffer[] samples) {
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
		
		public Buffer[] getSamples() {
			return this.samples;
		}
	}
}
