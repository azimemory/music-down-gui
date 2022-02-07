package com.music;

import java.awt.EventQueue;

import com.music.view.Main;

/**
 * 실행 클래스
 * @author azi
 *
 */
public class Run {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			try {
				new Main();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
