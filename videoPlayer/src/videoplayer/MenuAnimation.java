package videoplayer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuAnimation implements MouseListener {
	private JPanel panel;
	private Timer timer;
	private boolean OPEN = false;
	public MenuAnimation(JPanel panel) {
		this.panel = panel;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			if(OPEN) {
				runAnime(200, 0, 200);
			}
			else {
				runAnime(0, 200, 200);
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
	
	private void runAnime(int width1, int width2, int millisecond) {
		int speed = width1 < width2 ? 10: -10;
		int interval = millisecond / Math.abs(width1 - width2);
		int height = panel.getPreferredSize().height;
		timer = new Timer(interval, new ActionListener() {
			int width = width1;
			@Override
			public void actionPerformed(ActionEvent e) {
				width += speed;
				panel.setPreferredSize(new Dimension(width, height));

				if(speed > 0 && width >= width2) {
					panel.setPreferredSize(new Dimension(width - (width - width2), height));
					timer.stop();
				}
				else if(speed < 0 && width <= width2){
					panel.setPreferredSize(new Dimension(width + (width - width2), height));
					timer.stop();
				}
				
				panel.repaint();
				panel.revalidate();
			}
			
		});
		timer.start();
	}
}
