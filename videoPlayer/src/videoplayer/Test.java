package videoplayer;

import javax.swing.JLabel;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.TimelineScenario;
import org.pushingpixels.radiance.animation.api.ease.Linear;

public class Test {

	public static void main(String[] args) {

		// Create a JLabel to animate
		JLabel label = new JLabel("Hello World");

		// Create a Timeline and add a TimelineScenario to it
		Builder timeline = Timeline.builder(label);
		timeline.addPropertyToInterpolate("opacity", 0f, 1f);
		timeline.setDuration(1000);
		timeline.setEase(new Linear());
		timeline.build();
		timeline.play();
//		TimelineScenario scenario = new TimelineScenario.Builder()
//		    .addPropertyToInterpolate("opacity", 0f, 1f) // Interpolate the "opacity" property from 0 to 1
//		    .setDuration(1000) // Set the duration of the animation to 1000 milliseconds
//		    .setEase(new Linear()) // Use a linear easing function
//		    .build();
//		timeline.addScenario(scenario);

		// Start the animation
		timeline.play();
	}

}
