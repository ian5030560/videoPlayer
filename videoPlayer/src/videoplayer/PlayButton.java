package videoplayer;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayButton extends JLabel implements MouseListener {
	private ImageIcon pauseImage = new ImageIcon(this.getClass().getResource("/pause.png"));
	private Image pauseImg = pauseImage.getImage();
	private ImageIcon playImage = new ImageIcon(this.getClass().getResource("/play.png"));
	private Image playImg = playImage.getImage();
	private final int WIDTH = 70;
	private final int HEIGHT = 70;
	private boolean PLAY = false;
	public PlayButton() {
		pauseImg = pauseImg.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		pauseImage.setImage(pauseImg);
		playImg = playImg.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		playImage.setImage(playImg);
		this.setIcon(pauseImage);
		this.addMouseListener(this);
	}

	public PlayButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public PlayButton(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	public PlayButton(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public PlayButton(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public PlayButton(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!PLAY) {
			this.setIcon(playImage);
		}
		else {
			this.setIcon(pauseImage);
		}
		PLAY = !PLAY;
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
