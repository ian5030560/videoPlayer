package component;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private List<String> menu = new ArrayList<String>();
	
	public MenuPanel() {
	}
	
	public void addItem(String item) {
		JButton itemButton = new JButton(item);
		itemButton.setPreferredSize(new Dimension(300, 40));
		menu.add(item);
		this.add(itemButton);
	}
	
}
