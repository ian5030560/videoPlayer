package videoplayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Cursor;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class VideoPlayer extends JFrame {

	private JPanel contentPane;
	private JSlider volumeSlider;
	private JLabel fileButton;
	private JLabel forwardButton;
	private JLabel playButton;
	private JLabel backwardButton;
	private JLabel modeButton;
	private JLabel speedButton;
	private JPanel panel_7;
	private JLabel menuButton;
	private JPanel panel_8;
	private MenuPanel menuPanel;
	private JSlider progressSilder;
	private JLabel volumeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoPlayer frame = new VideoPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VideoPlayer() {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_7, BorderLayout.NORTH);
		
		menuButton = new JLabel("≡");
		menuButton.setHorizontalAlignment(SwingConstants.CENTER);
		menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuButton.setPreferredSize(new Dimension(30, 30));
		menuButton.addMouseListener(new MenuButtonStyle(menuButton));
		menuButton.setOpaque(true);
		menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuButton.setFont(new Font("新細明體", Font.BOLD, 25));
		panel_7.add(menuButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(255, 255, 255)));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		panel_2.add(panel);
		
		ImageIcon fileImage = new ImageIcon(this.getClass().getResource("/file.png"));
		Image fileImg = fileImage.getImage();
		fileImg = fileImg.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
		fileImage.setImage(fileImg);
		fileButton = new JLabel(fileImage);
		fileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(fileButton);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setHgap(30);
		panel_2.add(panel_4);
		
		ImageIcon backwardImage = new ImageIcon(this.getClass().getResource("/backward.png"));
		Image backwardImg = backwardImage.getImage();
		backwardImg = backwardImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		backwardImage.setImage(backwardImg);
		backwardButton = new JLabel(backwardImage);
		backwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(backwardButton);
		
		playButton = new PlayButton();
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(playButton);
			
		ImageIcon forwardImage = new ImageIcon(this.getClass().getResource("/forward.png"));
		Image forwardImg = forwardImage.getImage();
		forwardImg = forwardImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		forwardImage.setImage(forwardImg);
		forwardButton = new JLabel(forwardImage);
		forwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(forwardButton);
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setHgap(20);
		panel_2.add(panel_6);
		
		modeButton = new JLabel("模式");
		modeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_6.add(modeButton);
		
		speedButton = new JLabel("速度");
		speedButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_6.add(speedButton);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.WEST);

		ImageIcon volumeImage = new ImageIcon(this.getClass().getResource("/volume control.png"));
		Image volumeImg = volumeImage.getImage();
		volumeImg = volumeImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		volumeImage.setImage(volumeImg);
		volumeButton = new JLabel(volumeImage);
		volumeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_5.add(volumeButton);
		
		volumeSlider = new JSlider();
		volumeSlider.setValue(0);
		panel_5.add(volumeSlider);
		
		volumeButton.addMouseListener(new VolumeAnimation(volumeSlider));
		progressSilder = new JSlider();
		progressSilder.setValue(0);
		panel_3.add(progressSilder);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		menuPanel = new MenuPanel();
		menuPanel.setBackground(new Color(255, 255, 255));
		menuPanel.setPreferredSize(new Dimension(0, 10));
		panel_8.add(menuPanel, BorderLayout.WEST);
		
		menuButton.addMouseListener(new MenuAnimation(menuPanel));
	}

}
