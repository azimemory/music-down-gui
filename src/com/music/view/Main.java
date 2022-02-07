package com.music.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import com.music.music.controller.MusicController;


/**
 * 어플리케이션 main 화면
 * @author azi
 *
 */
public class Main {
	
	MusicController musicController = new MusicController();

	private JFrame frame;
	private JTextField threads;
	

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel readme = new JLabel("[가수-노래제목] 형태로 입력해주세요");
		readme.setBounds(12, 10, 387, 28);
		frame.getContentPane().add(readme);
		
		JLabel readme2 = new JLabel(" ex) queen-we will rock you");
		readme2.setBounds(12, 36, 246, 15);
		frame.getContentPane().add(readme2);
		
		JLabel musicLabel = new JLabel("노래제목들");
		musicLabel.setBounds(12, 73, 221, 21);
		frame.getContentPane().add(musicLabel);
		
		JTextArea musicTextArea = new JTextArea();
		musicTextArea.setBounds(12, 98, 182, 155);
		frame.getContentPane().add(musicTextArea);
		
		JButton btnDownload = new JButton("다운로드");
		
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicController.downloadMusic(musicTextArea.getText(), threads.getText());
			}
		});
		
		btnDownload.setBounds(206, 215, 216, 38);
		frame.getContentPane().add(btnDownload);
		
		threads = new JTextField();
		threads.setBounds(206, 128, 116, 21);
		frame.getContentPane().add(threads);
		threads.setColumns(10);
		
		JLabel ThreadLabel = new JLabel("Thread 숫자");
		ThreadLabel.setBounds(206, 97, 116, 27);
		frame.getContentPane().add(ThreadLabel);
	}
}
