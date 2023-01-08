package videoplayer;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.net.URL;

import Animation.SizeAnimation;

public class ActionButton extends NewButton {
	
	private SizeAnimation sa = new SizeAnimation(this);
	
	public ActionButton() {
		
	}

	public ActionButton(URL file) {
		super(file);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		

	}

	@Override
	public void pressAnimation() {
		sa.setDuration(250);
		Dimension size1 = new Dimension(this.getWidth(), this.getHeight());
		Dimension size2 = new Dimension(this.getWidth() / 2, this.getHeight());
		sa.setSize(size1, size2);
		sa.runLoop(2);
	}

}
