package com.music.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import com.music.dto.Music;


/**
 * yt-dlp를 사용해 유튜브의 영상을 mp3파일로 다운로드하는 쓰래드
 * @author azi
 *
 */
public class Downloader implements Runnable{
	
	private LinkedBlockingQueue<Music> musics;
	private static String savePath = System.getProperty("user.home") + "\\youtube-music\\";

	public Downloader(LinkedBlockingQueue<Music> musics) {
		this.musics = musics;
	}
	
	/**
	 * LinkedBlockingQueue안에 music이 없을 때 까지 반복하여 다운로드
	 */
	@Override
	public void run() {
		
		while (!musics.isEmpty()) {
			try {
				download(musics.take());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void download(Music music) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("yt-dlp",  
				"--extract-audio"
				, "--audio-format", "mp3"
				, "--audio-quality", "0"
				, "-o", savePath + music.getMusician() + "_" + music.getTitle() + ".%(ext)s",
				music.getUrl());

		Process p = pb.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while (true) {
			String line = r.readLine();
			if (line == null) break;
			System.out.println(line);
		}
	}

	
}
