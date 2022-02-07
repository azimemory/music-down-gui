package com.music.music.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import com.music.common.util.ThreadPoolExecutor;
import com.music.common.util.URLCrawler;
import com.music.common.util.YtDlpExecutor;
import com.music.music.dto.CrawlerDTO;
import com.music.music.dto.Music;
import com.music.music.dto.YtDlpDTO;


public class MusicDownloader {
	
	public void downloadMusic(ArrayList<Music> musicList, int threadCnt) {
		
		Map<String, CrawlerDTO> crawlerDTOs = createCrawlerDTOs(musicList);
		crawlingMusicLink(crawlerDTOs.values(), threadCnt);
		
		ArrayList<YtDlpDTO> ytDlpDTOs = createYtDlpDtos(crawlerDTOs);
		executeYtDlp(ytDlpDTOs, threadCnt);
	}


	private ArrayList<YtDlpDTO> createYtDlpDtos(Map<String, CrawlerDTO> crawlerDTOs) {
		ArrayList<YtDlpDTO> ytDlpDTOs = new ArrayList<YtDlpDTO>();
		for (Entry<String, CrawlerDTO> entry : crawlerDTOs.entrySet()) {
			ytDlpDTOs.add(new YtDlpDTO.Builder()
					.fileName(entry.getKey())
					.videoURL(entry.getValue().getResult())
					.build());
		}
		return ytDlpDTOs;
	}


	private Map<String, CrawlerDTO> createCrawlerDTOs(ArrayList<Music> musicList) {
		
		Map<String, CrawlerDTO> crawlerDTOs = new LinkedHashMap<>();
		String url = "https://www.youtube.com/results?search_query=";
		
		for (Music music : musicList) {
			
			String fileName = music.getMusician()+"-"+music.getTitle();
			
			crawlerDTOs.put(fileName, new CrawlerDTO.Builder()
					.url(url + music.getMusician()+"-"+music.getTitle())
					.cssSelector("a[href^=\"/watch?\"]")
					.htmlAttribute("href")
					.build());
		}
		
		return crawlerDTOs;
	}
	
	
	private void crawlingMusicLink(Collection<CrawlerDTO> crawlerDTOs, int threadCnt) {
		
		LinkedBlockingQueue<CrawlerDTO> crawlerDTOQueue = new LinkedBlockingQueue<>(crawlerDTOs);
		ThreadPoolExecutor
		.getInstance()
		.executeTasks(new URLCrawler(crawlerDTOQueue),threadCnt);
	}
	
	private void executeYtDlp(ArrayList<YtDlpDTO> ytDlpDTOs, int threadCnt) {
		
		LinkedBlockingQueue<YtDlpDTO> musicQueue = new LinkedBlockingQueue<>(ytDlpDTOs);
		ThreadPoolExecutor
		.getInstance()
		.executeTasks(new YtDlpExecutor(musicQueue),threadCnt);
	}

}
