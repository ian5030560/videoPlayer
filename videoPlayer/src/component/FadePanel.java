package component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FadePanel extends JPanel implements MouseListener{
	private float alpha = 0f;
	private Thread mainThread;
	private int EVENT = -1;
	private static final double SPEED = 0.1;
	public FadePanel() {
		this.setOpaque(false);
		initThread();
		this.addMouseListener(this);
	}

	public FadePanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public FadePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public FadePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
	}
	
	private void initThread() {
		mainThread = new Thread() {
			@Override
			public void run() {
				
				while(true) {
					if(EVENT == MouseEvent.MOUSE_ENTERED) {
						setOpaque(true);
						alpha = 1f;
					}
					else if(EVENT == MouseEvent.MOUSE_EXITED) {
						if(alpha > 0.1f) {
							alpha -= SPEED;
						}
						else {
							setOpaque(false);
						}
					}
					
//					try {
//						Thread.sleep(50);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		};
		mainThread.start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		EVENT = e.getID();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		EVENT = e.getID();
		
	}
}
