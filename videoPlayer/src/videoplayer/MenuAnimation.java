package videoplayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import Animation.SizeAnimation;

public class MenuAnimation implements MouseListener {
	private JPanel panel;
	private boolean OPEN = false;
	
	public MenuAnimation(JPanel panel) {
		this.panel = panel;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		SizeAnimation sa = new SizeAnimation(panel);
		sa.setDuration(250);
		if(e.getClickCount() == 1) {
			if(OPEN) {
				sa.setValues(200, 0, SizeAnimation.WIDTH);
				sa.run();
			}
			else {
				sa.setValues(0, 200, SizeAnimation.WIDTH);
				sa.run();
			}	
			OPEN = !OPEN;
		}
		
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
