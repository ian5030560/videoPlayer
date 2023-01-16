package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import video.VideoPanel;

public class PlayButton extends JButton{
	private URL pause = this.getClass().getResource("/pause.png");
	private URL play = this.getClass().getResource("/play.png");
	private BufferedImage image;
	public boolean PLAY = false;
	
	public PlayButton() {
		this.setPreferredSize(new Dimension(80, 80));
		setPause();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp;
		if(this.getModel().isPressed()) {
			gp = new GradientPaint(0f, 0f, Color.GRAY, 0, this.getHeight() / 2, Color.WHITE, true);
		}
		else {
			gp = new GradientPaint(0f, 0f, Color.WHITE, 0, this.getHeight() / 2, Color.GRAY, true);
		}
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		Image image = this.image.getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_SMOOTH);
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public void setPlay() {
		try {
			this.image = ImageIO.read(play);
			repaint();
			PLAY = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPause() {
		try {
			this.image = ImageIO.read(pause);
			repaint();
			PLAY = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void endState(int endState) {
		if(endState == VideoPanel.VIDEO_END) {
			setPause();
		}
	}
}
