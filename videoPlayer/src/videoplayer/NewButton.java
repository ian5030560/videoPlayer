package videoplayer;

import java.awt.BasicStroke;
import java.awt.Color;
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

public class NewButton extends JButton implements MouseListener{
	
	private BufferedImage image;
	private Color defaultColor = this.getBackground();
	
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
		
		if(this.getModel().isRollover()) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(3));
			g2d.drawRoundRect(0, 0, width, height, 20, 20);
		}
		
		if(this.getModel().isPressed()) {
			this.setBackground(Color.GRAY);
		}
		else {
			this.setBackground(defaultColor);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 1) {
			SizeAnimation sa = new SizeAnimation(this);
			sa.setValues(this.getWidth(), this.getWidth() / 2, SizeAnimation.WIDTH);
			sa.setDuration(250);
			sa.runLoop(2);
			
			SizeAnimation sa1 = new SizeAnimation(this);
			sa1.setDuration(250);
			sa1.setValues(this.getHeight(), this.getHeight() / 2, SizeAnimation.HEIGHT);
			sa1.runLoop(2);		
		}
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
