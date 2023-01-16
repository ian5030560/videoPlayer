package videoplayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JSlider;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.formdev.flatlaf.FlatLightLaf;

import animation.MenuAnimation;
import animation.VolumeAnimation;
import component.MenuPanel;
import component.PlayButton;
import video.VideoPanel;

import java.awt.Cursor;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import component.MenuButton;


public class VideoPlayer extends JFrame {

	private VideoPanel videoPanel;
	private JSlider volumeSlider;
	private JButton fileButton;
	private JButton forwardButton;
	private PlayButton playButton;
	private JButton backwardButton;
	private JSlider progressSilder;
	private JButton volumeButton;
	private JLabel timeLabel;
	private JPanel panel_1;
	private JPanel panel_7;
	private JPanel panel_8;
	private MenuButton menuButton;
	private JPanel panel_9;
	private MenuPanel menuPanel;
	private JToggleButton modeButton;

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
	 * @throws IOException 
	 */
	public VideoPlayer() throws IOException {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 646);
		videoPanel = new VideoPanel();
		videoPanel.setBackground(new Color(0, 0, 0));
		videoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(videoPanel);
		videoPanel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(255, 255, 255)));
		videoPanel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		panel_2.add(panel);
		
		ImageIcon fileIcon = new ImageIcon(this.getClass().getResource("/file.png"));
		Image fileImage = fileIcon.getImage().getScaledInstance(60, 40, Image.SCALE_SMOOTH);
		fileIcon.setImage(fileImage);
		fileButton = new JButton(fileIcon);
		fileButton.addActionListener(new ActionListener() {
			Thread thread;
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileNameExtensionFilter("video file", "mp4", "wav"));
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					thread = new Thread() {
						@Override
						public void run() {
							videoPanel.fetchVideo(chooser.getSelectedFile());
							progressSilder.setMaximum((int) videoPanel.getDuration());
							int time = (int) (videoPanel.getDuration() / 1000000);
							timeLabel.setText(String.format("%02d:%02d", time / 60, time % 60));
							menuPanel.addItem(chooser.getSelectedFile().getAbsolutePath());
						}
					};
					thread.start();
				}
			}
		});
		fileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(fileButton);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setHgap(30);
		panel_2.add(panel_4);
		
		ImageIcon backIcon = new ImageIcon(this.getClass().getResource("/backward.png"));
		Image backImage = backIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		backIcon.setImage(backImage);
		backwardButton = new JButton(backIcon);
		backwardButton.setPreferredSize(new Dimension(50, 50));
		backwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				videoPanel.setPosition(videoPanel.getPosition() - 5000000);
			}
			
		});
		panel_4.add(backwardButton);
		
		playButton = new PlayButton();
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!playButton.PLAY) {
					videoPanel.play();
					playButton.setPlay();
				}
				else {
					videoPanel.stop();
					playButton.setPause();
				}
			}
			
		});
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(playButton);

		ImageIcon forwardIcon = new ImageIcon(this.getClass().getResource("/forward.png"));
		Image forwardImage = forwardIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		forwardIcon.setImage(forwardImage);
		forwardButton = new JButton(forwardIcon);
		forwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forwardButton.setPreferredSize(new Dimension(50, 50));
		panel_4.add(forwardButton);
		forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				videoPanel.setPosition(videoPanel.getPosition() + 5000000);
			}
			
		});

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setHgap(20);
		panel_2.add(panel_6);
		
		modeButton = new JToggleButton("單曲");
		modeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modeButton.addActionListener(new ActionListener() {
			private boolean LOOP = true;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LOOP) {
					modeButton.setText("循環");
				}
				else {
					modeButton.setText("單曲");
				}
				videoPanel.setLoop(LOOP);
				LOOP = !LOOP;
			}
			
		});
		modeButton.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		panel_6.add(modeButton);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		panel_3.add(panel_5, BorderLayout.WEST);
		
		BufferedImage volumeImage = ImageIO.read(this.getClass().getResource("/volume control.png"));
		ImageIcon volumeIcon = new ImageIcon(volumeImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		volumeButton = new JButton(volumeIcon);
		volumeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_5.add(volumeButton);
		
		volumeSlider = new JSlider(-80, 6);
		volumeSlider.setValue(100);
		volumeSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				videoPanel.setVolume(volumeSlider.getValue());
			}
			
		});
		panel_5.add(volumeSlider);
		
		volumeButton.addMouseListener(new VolumeAnimation(volumeSlider));
		
		progressSilder = new JSlider();
		progressSilder.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				videoPanel.setPosition(progressSilder.getValue());
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseDragged(e);
			}
		});
		progressSilder.setValue(0);
		panel_3.add(progressSilder, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		
		timeLabel = new JLabel("00:00");
		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timeLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		panel_3.add(timeLabel, BorderLayout.EAST);
		
		videoPanel.processSignal.link(progressSilder, "setValue");
		videoPanel.processSignal.link(playButton, "endState");
		videoPanel.processSignal.link(this, "timeRecord");
		
		panel_7 = new JPanel();
		panel_7.setOpaque(false);
		videoPanel.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		panel_8 = new JPanel();
		panel_8.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_8, BorderLayout.NORTH);
		
		menuButton = new MenuButton("≡");
		menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuButton.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 25));
		panel_8.add(menuButton);
		
		panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_9.setPreferredSize(new Dimension(200, 400));
		panel_7.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		menuPanel = new MenuPanel();
		menuPanel.setPreferredSize(new Dimension(0, 400));
		panel_9.add(menuPanel);
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		menuButton.addActionListener(new MenuAnimation(menuPanel));
	}
	
	public void timeRecord(int time) {
		time = (int) ((videoPanel.getDuration() - time) / 1000000);
		timeLabel.setText(String.format("%02d:%02d", time / 60, time % 60));
	}
}
