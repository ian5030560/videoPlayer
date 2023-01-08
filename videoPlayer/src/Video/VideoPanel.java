package Video;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import src.main.java.net.bramp.ffmpeg.*;

public class VideoPanel extends JPanel implements ActionListener{
	private Timer timer = new Timer(1000, this);
	private BufferedImage image;
	
	public VideoPanel() {
		
	}

	public VideoPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public VideoPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public VideoPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}
	
	public void fetchVideo() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graphics2D g = (Graphics2D) this.getGraphics();
		this.update(g);
//		Image image = 
//		g.drawImage(, WIDTH, SOMEBITS, PROPERTIES, HEIGHT, FRAMEBITS, ERROR, ALLBITS, ABORT, getFocusCycleRootAncestor())
	}
}
