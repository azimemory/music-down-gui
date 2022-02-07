package com.music.common.component;

import javax.swing.JOptionPane;

public class SwingComponent {
	
	/**
	 * 스윙 알림창을 화면에 출력
	 * @param msg 화면에 띄울 메시지
	 * @param titleBar 타이틀바
	 */
	public static void infoBox(String msg, String titleBar){
        JOptionPane.showMessageDialog(null, msg, "msg: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
