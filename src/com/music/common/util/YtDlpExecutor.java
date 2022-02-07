package com.music.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

import com.music.music.dto.YtDlpDTO;


/**
 * yt-dlp를 사용해 유튜브의 영상을 mp3파일로 다운로드하는 쓰래드
 * @author azi
 *
 */
public class YtDlpExecutor implements Runnable{
	
	private LinkedBlockingQueue<YtDlpDTO> tasks;
	private static String savePath = System.getProperty("user.home") + "\\youtube-music\\";

	public YtDlpExecutor(LinkedBlockingQueue<YtDlpDTO> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * LinkedBlockingQueue안에 music이 없을 때 까지 반복하여 다운로드
	 */
	@Override
	public void run() {
		
		while (!tasks.isEmpty()) {
			try {
				execute(tasks.take());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void execute(YtDlpDTO dto) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("yt-dlp",  
				"--extract-audio"
				, "--audio-format", "mp3"
				, "--audio-quality", "0"
				, "-o", savePath + dto.getFileName() + ".%(ext)s",
				dto.getVideoURL());

		Process p = pb.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while (true) {
			String line = r.readLine();
			if (line == null) break;
			System.out.println(line);
		}
	}

	
}
