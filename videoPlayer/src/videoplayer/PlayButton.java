package videoplayer;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Animation.SizeAnimation;

public class PlayButton extends NewButton{
	private URL pause = this.getClass().getResource("/pause.png");
	private URL play = this.getClass().getResource("/play.png");
	private boolean PLAY = false;
	
	public PlayButton() {
		super();
		this.setImage(pause);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!PLAY) {
					setImage(play);
				}
				else {
					setImage(pause);
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
			
		});
	}

}
