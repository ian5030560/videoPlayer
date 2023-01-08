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
	private boolean ACTIVE = true;
	private JSlider silder;
	
	public VolumeAnimation(JSlider silder) {
		this.silder = silder;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			SizeAnimation sa = new SizeAnimation(silder);
			sa.setDuration(250);
			Dimension size1 = new Dimension(200, this.silder.getHeight());
			Dimension size2 = new Dimension(0, this.silder.getHeight());
			sa.setSize(size1, size2);
			if(ACTIVE) {
				sa.run();
			}
			else {
				sa.runReverse();
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
