package Animation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class SizeAnimation implements ActionListener, Runnable {
	private Timer timer;
	private int value1;
	private int value2;
	private int SPEED = 10;
	private int duration = 1000;
	private JComponent comp;
	public static final int WIDTH = 0;
	public static final int HEIGHT = 1;
	private int hint;
	
	public SizeAnimation(JComponent comp) {
		this.comp = comp;
	}
	
	public SizeAnimation(JComponent comp, int value1, int value2, int type) {
		this(comp);
		this.setValues(value1, value2, type);
	}
	
	public void setDuration(int millisecond) {
		this.duration = millisecond;
	}
	
	public void setValues(int value1, int value2, int type) {
		this.value1 = value1;
		this.value2 = value2;
		this.SPEED = value1 < value2 ? 10: -10;
		this.hint = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.value1 += SPEED;

		if(this.hint == WIDTH) {
			int height = this.comp.getHeight();
			this.comp.setPreferredSize(new Dimension(this.value1, height));
			if(this.SPEED > 0 && value1 >= value2) {
				this.comp.setPreferredSize(new Dimension(value1 - (value1 - value2), height));
				this.timer.stop();
			}
			else if(this.SPEED < 0 && value1 <= value2){
				this.comp.setPreferredSize(new Dimension(value1 + (value1 - value2), height));
				this.timer.stop();
			}
		}
		else {
			int width = this.comp.getWidth();
			this.comp.setPreferredSize(new Dimension(width, this.value1));
			if(this.SPEED > 0 && value1 >= value2) {
				this.comp.setPreferredSize(new Dimension(width, value1 - (value1 - value2)));
				this.timer.stop();
			}
			else if(this.SPEED < 0 && value1 <= value2){
				this.comp.setPreferredSize(new Dimension(width, value1 + (value1 - value2)));
				this.timer.stop();
			}
		}

		comp.repaint();
		comp.revalidate();
	}

	@Override
	public void run() {
		int interval = duration / Math.abs(value1 - value2);
		timer = new Timer(interval, this);
		timer.start();
	}
	
	public void runReverse() {
		this.setValues(value2, value1, this.hint);
		run();
	}
	
	public void runLoop(int count) {
		
	}
	
}
