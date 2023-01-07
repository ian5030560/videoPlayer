package videoplayer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import Animation.SizeAnimation;

public class VolumeAnimation implements MouseListener{
	private Timer timer;
	private boolean ACTIVE = true;
	private JSlider silder;
	private int width;
	private JPanel panel;
	public VolumeAnimation(JSlider silder) {
		this.silder = silder;
		width = silder.getPreferredSize().width;
		this.panel = (JPanel) silder.getParent();
	}
	
	private void anime(int width1, int width2, int millisecond) {
		int speed = width1 < width2 ? 10: -10;
		int interval = millisecond / Math.abs(width1 - width2);
		int height = silder.getPreferredSize().height;
		timer = new Timer(interval, new ActionListener() {
			int width = width1;
			@Override
			public void actionPerformed(ActionEvent e) {
				width += speed;
				silder.setPreferredSize(new Dimension(width, height));

				if(speed > 0 && width >= width2) {
					silder.setPreferredSize(new Dimension(width - (width - width2), height));
					timer.stop();
				}
				else if(speed < 0 && width <= width2){
					silder.setPreferredSize(new Dimension(width + (width - width2), height));
					timer.stop();
				}

				panel.repaint();
				panel.revalidate();
			}
			
		});
		timer.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			if(ACTIVE) {
				SizeAnimation sa = new SizeAnimation(silder);
				sa.setDuration(500);
				sa.setValues(width, 0, SizeAnimation.WIDTH);
				sa.run();
			}
			else {
				SizeAnimation sa = new SizeAnimation(silder);
				sa.setDuration(500);
				sa.setValues(0, width, SizeAnimation.WIDTH);
				sa.run();
			}
			
			ACTIVE = !ACTIVE;		
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
