package videoplayer;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageEditor extends ImageIcon{
	private int width;
	private int height;
	private Image image;
	private JLabel label;
	public ImageEditor(String file) {
		super(file);
		load();
	}
	
	public ImageEditor (URL location) {
		super(location);
		load();
	}
	
	public ImageEditor(Image image) {
		super(image);
		load();
	}
	
	public ImageEditor(JLabel label) {
		link(label);
	}
	
	private void load() {
		image = this.getImage();
		width = this.getIconWidth();
		height = this.getIconHeight();
	}
	
	public void setSize(int width, int height) {
		this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.setImage(this.image);
		this.width = width;
		this.height = height;
		if(label != null) {
			label.setIcon(new ImageEditor(this.image));
		}
	}
	
	public void setWidth(int width) {
		this.setSize(width, this.height);
	}
	
	public void setHeight(int height) {
		this.setSize(this.width, height);
	}
	
	public void link(JLabel label) {
		this.label = label;
		ImageIcon icon = (ImageIcon) label.getIcon();
		image = icon.getImage();
		width = icon.getIconWidth();
		height = icon.getIconHeight();
	}
}
