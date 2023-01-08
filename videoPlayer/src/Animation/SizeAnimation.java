package Animation;

import java.awt.Dimension;
import javax.swing.JComponent;
import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class SizeAnimation{
	private Dimension size;
	private int duration = 1000;
	private JComponent comp;
	private Builder timeline;
	
	public SizeAnimation(JComponent comp) {
		this.comp = comp;
		timeline = Timeline.builder(this);
		timeline.setEase(new Linear());
	}
	
	public SizeAnimation(JComponent comp, Dimension value1, Dimension value2) {
		this(comp);
		setSize(value1, value2);
	}
	
	public void setSize(Dimension value) {
		this.size = value;
		this.comp.setSize(this.size);
	}
	
	public void setSize(Dimension value1, Dimension value2) {
		timeline.addPropertyToInterpolate("size", value1, value2);
	}
	
	public void setDuration(int millisecond) {
		this.duration = millisecond;
		timeline.setDuration(this.duration);
	}
	
	public void run() {
		timeline.play();
	}
	
	public void runLoop(int count) {
		if(count == -1) {
			timeline.playLoop(RepeatBehavior.REVERSE);
		}
		else {
			timeline.playLoop(count, RepeatBehavior.REVERSE);
		}
		
	}
	
	public void runReverse() {
		timeline.playReverse();
	}
}
