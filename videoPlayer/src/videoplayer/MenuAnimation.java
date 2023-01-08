package videoplayer;

import java.awt.Dimension;
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
		if(e.getClickCount() == 1) {
			SizeAnimation sa = new SizeAnimation(panel);
			sa.setDuration(250);
			Dimension size1 = new Dimension(200, this.panel.getHeight());
			Dimension size2 = new Dimension(0, this.panel.getHeight());
			sa.setSize(size1, size2);
			if(OPEN) {
				sa.run();
			}
			else {
				sa.runReverse();
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
