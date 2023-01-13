package Animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;
import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class SizeAnimation extends AnimationHub{
	private Dimension size;
	private Builder timeline;
	private int duration = 1000;
	private JComponent comp;
	private Point point;
	public SizeAnimation(JComponent comp) {
		super(comp);
		this.comp = comp;
		this.timeline = Timeline.builder(this);
		this.timeline.setEase(new Linear());
		this.timeline.setDuration(this.duration);
	}
	
	public SizeAnimation(JComponent comp, Dimension value1, Dimension value2) {
		this(comp);
		setValue(value1, value2);
	}
	
	public void setSize(Dimension value) {
		this.size = value;
		this.comp.setSize(this.size);
	}
	
	@Override
	public void setDuration(int millisecond) {
		this.duration = millisecond;
		timeline.setDuration(this.duration);
	}

	@Override
	public void run() {
		timeline.play();
	}

	@Override
	public void runLoop(int count) {
		if(count == -1) {
			timeline.playLoop(RepeatBehavior.REVERSE);
		}
		else {
			timeline.playLoop(count, RepeatBehavior.REVERSE);
		}
	}

	@Override
	public void runReverse() {
		timeline.playReverse();
	}

	@Override
	public void setValue(Object value1, Object value2) {
		Dimension value3 = (Dimension) value1;
		Dimension value4 = (Dimension) value2;
		this.timeline.addPropertyToInterpolate("size", value3, value4);
		this.size = this.comp.getSize();
		this.point = this.comp.getLocation();
	}
}
