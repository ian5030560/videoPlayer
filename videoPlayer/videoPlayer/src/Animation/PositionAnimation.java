package Animation;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;

public class PositionAnimation extends AnimationHub{
	private JComponent comp;
	private Builder timeline;
	private Point point;
	private int duration;
	
	public PositionAnimation(JComponent comp) {
		super(comp);
		this.comp = comp;
		this.point = this.comp.getLocation();
		this.timeline = Timeline.builder(this);
	}
	
	public PositionAnimation(JComponent comp, Point value1, Point value2) {
		this(comp);
		this.setValue(value1, value1);
	}

	public void setPoint(Point value) {
		this.comp.setLocation(value);
		this.point = value;
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
		Point value3 = (Point) value1;
		Point value4 = (Point) value2;
		timeline.addPropertyToInterpolate("point", value3, value4);
		this.point = this.comp.getLocation();
	}
}
