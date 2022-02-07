package com.music.domain;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import com.music.dto.Music;

public class MusicDownloader {
	
	public void downloadMusic(ArrayList<Music> musicList, int threadCnt) {
		crawlingMusicLink(musicList, threadCnt);
		executYtDlp(musicList, threadCnt);
	}
	
	
	private void crawlingMusicLink(ArrayList<Music> musicList, int threadCnt) {
		LinkedBlockingQueue<Music> musicQueue = new LinkedBlockingQueue<>(musicList);
		ThreadPoolExecutor
		.getInstance()
		.executeTasks(new URLCrawler(musicQueue),threadCnt);
	}
	
	private void executYtDlp(ArrayList<Music> musicList, int threadCnt) {
		LinkedBlockingQueue<Music> musicQueue = new LinkedBlockingQueue<>(musicList);
		ThreadPoolExecutor
		.getInstance()
		.executeTasks(new YtDlpExecutor(musicQueue),threadCnt);
	}

}
