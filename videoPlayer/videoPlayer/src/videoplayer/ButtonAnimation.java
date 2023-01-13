package videoplayer;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.pushingpixels.radiance.animation.api.Timeline;
import org.pushingpixels.radiance.animation.api.Timeline.Builder;
import org.pushingpixels.radiance.animation.api.Timeline.RepeatBehavior;
import org.pushingpixels.radiance.animation.api.TimelinePropertyBuilder.PropertySetter;
import org.pushingpixels.radiance.animation.api.ease.Linear;

import Animation.SizeAnimation;

public class ButtonAnimation implements MouseListener {
	
	private JLabel button;
	private ImageEditor icon;
	public ButtonAnimation(JLabel button) {
		this.button = button;
		this.icon = (ImageEditor) button.getIcon();
	}	
	
	private void imageAnimation() {
//		int width = this.icon.getIconWidth();
//		int height = this.icon.getIconHeight();
//		Builder timeline = Timeline.builder(this.icon);
//		timeline.addPropertyToInterpolate("width", width, width / 2);
//		timeline.addPropertyToInterpolate("height", height, height / 2);
//		timeline.setDuration(100);
//		timeline.playLoop(2, RepeatBehavior.REVERSE);
		SizeAnimation sa = new SizeAnimation(this.button);
		sa.setDuration(1000);
		sa.setValues(this.button.getWidth(), this.button.getWidth() / 2, SizeAnimation.WIDTH);
//		sa.runLoop(1);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		imageAnimation();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
