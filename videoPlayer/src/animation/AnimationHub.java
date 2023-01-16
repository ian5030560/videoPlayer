package animation;

import javax.swing.JComponent;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;

public abstract class AnimationHub <E>{
	
	public AnimationHub(JComponent comp) {

	}
	
	public AnimationHub(JComponent comp, E value1, E value2) {
		
	}
	
	public abstract void setDuration(long millisecond);
	
	public abstract void setValue(E value1, E value2);
	
	public abstract void run();
	
	public abstract void runLoop(int count);
	
	public abstract void runReverse();

}
