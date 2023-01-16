package video;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class LoadVideoFrame extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoadVideoFrame frame = new LoadVideoFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoadVideoFrame(int clips) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("新細明體", Font.BOLD, 25));
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(300, 30));
		contentPane.add(progressBar);
		progressBar.setMaximum(clips);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void record(int value) {
		if(this.progressBar.getValue() == this.progressBar.getMaximum()) {
			dispose();
			setVisible(false);
		}
		else {
			this.progressBar.setValue(value);
		}
	}
}
