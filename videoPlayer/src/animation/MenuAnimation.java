package animation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MenuAnimation implements ActionListener {
	private JPanel panel;
	private boolean OPEN = false;
	private SizeAnimation sa;
	public MenuAnimation(JPanel panel) {
		this.panel = panel;
		sa = new SizeAnimation(panel);
		sa.setDuration(250);
		Dimension size1 = new Dimension(200, 400);
		Dimension size2 = new Dimension(0, 400);
		sa.setValue(size1, size2);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(OPEN) {
			sa.run();
		}
		else {
			sa.runReverse();
		}	
		OPEN = !OPEN;
		panel.getParent().repaint();
		panel.getParent().revalidate();
		
	}
}
