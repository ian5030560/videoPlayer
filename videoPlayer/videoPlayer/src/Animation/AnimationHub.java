package Animation;

import javax.swing.JComponent;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;

public abstract class AnimationHub{
	
	public AnimationHub(JComponent comp) {

	}
	
	public AnimationHub(JComponent comp, Object value1, Object value2) {
		
	}
	
	public abstract void setDuration(int millisecond);
	
	public abstract void setValue(Object value1, Object value2);
	
	public abstract void run();
	
	public abstract void runLoop(int count);
	
	public abstract void runReverse();

}
