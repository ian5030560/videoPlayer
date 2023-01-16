package videoplayer;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JLabel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameGrabber.Exception;
import org.bytedeco.javacv.Frame;
import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.TimelineScenario;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class Test {
	
	public static void main(String[] args){
		System.out.println(System.currentTimeMillis());
	}
}
