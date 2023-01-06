package videoplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class NewButton extends JButton {
	
	private BufferedImage image;
	
	public NewButton(URL file) {
//		this.setContentAreaFilled(false);
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int width = this.getSize().width;
		int height = this.getSize().height;
		Image image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		g2d.drawImage(image, 0, 0, null);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
//		Color currentColor = this.getBackground();
		if(this.getModel().isRollover()) {
			Graphics2D g2d = (Graphics2D) g;
			int width = this.getSize().width;
			int height = this.getSize().height;
//			this.setBackground(Color.GRAY);
			g2d.drawRoundRect(0, 0, width, height, 20, 20);			
		}
//		this.setBackground(currentColor);
	}
}
