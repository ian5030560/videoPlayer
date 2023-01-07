package videoplayer;

import javax.swing.JLabel;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.TimelineScenario;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class Test {
	private float value;

	public void setValue(float newValue) {
		System.out.println(this.value + " -> " + newValue);
		this.value = newValue;
	}
	
	public static void main(String[] args) {
		Test helloWorld = new Test();
		Timeline.builder(helloWorld)
			.addPropertyToInterpolate("value", 0.0f, 1.0f)
			.play();
		
//		try {
//			Thread.sleep(3000);
//		} catch (Exception exc) {
//		}
	}

}
