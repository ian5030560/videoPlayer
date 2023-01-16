package component;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import videoplayer.MenuButtonStyle;

public class MenuButton extends JButton {

	public MenuButton(String text) {
		super(text);
		this.addMouseListener(new MenuButtonStyle(this));
	}


}
