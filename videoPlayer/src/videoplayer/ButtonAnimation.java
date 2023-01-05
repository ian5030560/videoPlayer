package videoplayer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ButtonAnimation implements MouseListener {
	
	private JLabel button;
	private ImageIcon icon;
	private Image img;
	private Timer timer;
	public ButtonAnimation(JLabel button) {
		this.button = button;
		this.icon = (ImageIcon) button.getIcon();
		this.img = this.icon.getImage();
	}	

//	private void anime(int width1, int width2, int millisecond) {
//		int speed = width1 < width2 ? 10: -10;
//		int interval = millisecond / Math.abs(width1 - width2);
//		int height = silder.getPreferredSize().height;
//		timer = new Timer(interval, new ActionListener() {
//			int width = width1;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				width += speed;
//				silder.setPreferredSize(new Dimension(width, height));
//
//				if(speed > 0 && width >= width2) {
//					silder.setPreferredSize(new Dimension(width - (width - width2), height));
//					timer.stop();
//				}
//				else if(speed < 0 && width <= width2){
//					silder.setPreferredSize(new Dimension(width + (width - width2), height));
//					timer.stop();
//				}
//
//				panel.repaint();
//				panel.revalidate();
//			}
//			
//		});
//		timer.start();
//	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
