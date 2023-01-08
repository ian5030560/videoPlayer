package videoplayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import Animation.SizeAnimation;

public abstract class NewButton extends JButton implements MouseListener{
	
	private BufferedImage image;
	
	public NewButton() {
		this.addMouseListener(this);
	}
	
	public NewButton(URL file) {
		this();
		setImage(file);
	}
	
	public void setImage(URL file) {
		this.setContentAreaFilled(false);
		try {
			this.image = ImageIO.read(file);
			repaint();
		}
		catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int width = this.getSize().width;
		int height = this.getSize().height;
		Image image = this.image.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH);
		g2d.drawImage(image, 3, 3, null);
	}
	
	public abstract void pressAnimation();
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 1) {
			pressAnimation();
		}
	}
}
