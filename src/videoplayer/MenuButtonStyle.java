package videoplayer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

public class MenuButtonStyle implements MouseListener {
	private JLabel button;
	private Color backgroundColor;
	public MenuButtonStyle(JLabel button) {
		this.button = button;
		this.backgroundColor = button.getBackground();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		button.setBackground(new Color(79, 79, 4));

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button.setBackground(Color.GRAY);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		button.setBackground(Color.GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setBackground(backgroundColor);

	}

}
