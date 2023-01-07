package Animation;

import java.awt.Dimension;
import javax.swing.JComponent;

public class SizeAnimation{
	private int width;
	private int height;
	private int duration = 1000;
	private JComponent comp;
	public static final int WIDTH = 0;
	public static final int HEIGHT = 1;
	private int type;
	private int value1;
	private int value2;
	
	public SizeAnimation(JComponent comp) {
		this.comp = comp;
	}
	
	public SizeAnimation(JComponent comp, int value1, int value2, int type) {
		this(comp);
		setValues(value1, value2, type);
	}
	
	public void setValues(int value1, int value2, int type) {
		this.type = type;
		this.value1 = value1;
		this.value2 = value2;
	}
	
	public void setDuration(int millisecond) {
		this.duration = millisecond;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int[] getValues() {
		int[] arr = {this.value1, this.value2};
		return arr;
	}
	
	public void setWidth(int width) {
		this.comp.setSize(new Dimension(width, this.comp.getHeight()));
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.comp.setSize(new Dimension(this.comp.getWidth(), height));
		this.height = height;
	}
	
	public void run() {
		SizeAnimationEngine engine = new SizeAnimationEngine(this);
		engine.getOrder(SizeAnimationEngine.NORMAL);
	}
	
	public void runLoop(int count) {
		SizeAnimationEngine engine = new SizeAnimationEngine(this);
		engine.getOrder(SizeAnimationEngine.LOOP, count);
	}
	
	public void runReverse() {
		SizeAnimationEngine engine = new SizeAnimationEngine(this);
		engine.getOrder(SizeAnimationEngine.REVERSE);
	}
}
