package videoplayer;

import java.awt.LayoutManager;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ContainerListener{

	public MenuPanel() {
		
	}

	public MenuPanel(LayoutManager layout) {
		super(layout);
		
	}

	public MenuPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public MenuPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}

	@Override
	public void componentAdded(ContainerEvent e) {
		
		
	}

	@Override
	public void componentRemoved(ContainerEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
