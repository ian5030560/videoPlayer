package videoplayer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;

import Animation.SizeAnimation;

public class PlayButton extends NewButton{
	private URL pause = this.getClass().getResource("/pause.png");
	private URL play = this.getClass().getResource("/play.png");
	private boolean PLAY = false;
	private SizeAnimation sa = new SizeAnimation(this);
	public PlayButton() {
		super();
		this.setImage(pause);
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
		Dimension size2 = new Dimension(this.getWidth() / 2, this.getHeight() / 2);
		sa.setSize(size1, size2);
		sa.runLoop(2);
		if(!PLAY) {
			setImage(play);
		}
		else {
			setImage(pause);
		}
		PLAY = !PLAY;
	}

}
