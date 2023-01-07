package Animation;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class SizeAnimationEngine {
	private Builder timeline;
	private static final int WIDTH = 0;
	private static final int HEIGHT = 1;
	public static final int NORMAL = 0;
	public static final int REVERSE = 1;
	public static final int LOOP = 2;
	
	public SizeAnimationEngine(SizeAnimation sa) {
		timeline = Timeline.builder(sa);
		timeline.setDuration(sa.getDuration());
		if(sa.getType() == WIDTH) {
			timeline.addPropertyToInterpolate("width", sa.getValues()[0], sa.getValues()[1]);
		}
		else if(sa.getType() == HEIGHT){
			timeline.addPropertyToInterpolate("height", sa.getValues()[0], sa.getValues()[1]);
		}
		timeline.setEase(new Linear());
	}
	
	public void getOrder(int order) {
		if(order == NORMAL) {
			timeline.play();
		}
		else if(order == REVERSE) {
			timeline.playReverse();
		}
	}
	
	public void getOrder(int order, int loopCount) {
		if(order == LOOP) {
			if(loopCount == -1) {
				timeline.playLoop(RepeatBehavior.REVERSE);
			}
			else {
				timeline.playLoop(loopCount, RepeatBehavior.REVERSE);
			}
		}
	}
}
