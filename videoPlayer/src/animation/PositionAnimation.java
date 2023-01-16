package animation;

import java.awt.Point;
import javax.swing.JComponent;
import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;

public class PositionAnimation extends AnimationHub<Point>{
	private JComponent comp;
	private Builder timeline;
	private Point point;
	private long duration;
	
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
	public void setDuration(long millisecond) {
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
	public void setValue(Point value1, Point value2) {
		timeline.addPropertyToInterpolate("point", value1, value2);
		this.point = this.comp.getLocation();
	}
}
