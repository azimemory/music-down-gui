package com.music.music.controller;

import java.util.ArrayList;
import com.music.common.component.SwingComponent;
import com.music.music.dto.Music;
import com.music.music.service.MusicDownloader;

/**
 * Music 컨트롤러, 사용자 입력값 파싱, 입력값 유효성 검사, 알맞은 비지니스 클래스 호출
 * @author azi
 *
 */
public class MusicController {
	
	private final MusicDownloader musicDownloader = new MusicDownloader();


	public void downloadMusic(String searchingKeyword, String threads) {
		
		int threadCnt = validateThreadCnt(threads);
		if(threadCnt < 0) return;
		
		ArrayList<Music> musicList = createMusicList(searchingKeyword);
		musicDownloader.downloadMusic(musicList, threadCnt);
	}
	
	/**
	 * 사용자가 입력한 검색키워드를 Music DTO로 변환
	 * @param searchingKeyword 사용자 입력 키워드
	 * @return MusicDTO 리스트
	 */
	private ArrayList<Music> createMusicList(String keywords){
		
		ArrayList<Music> musicList = new ArrayList<Music>();
		
		String[] searches = keywords.split("\n");
		
		for (String search : searches) {
			String[] parse = search.split("-");
			musicList.add(new Music(parse[0].trim(), parse[1].trim()));
		}
		
		return musicList;
	}
	
	private int validateThreadCnt(String threads) {
		
		int threadCnt = 1;
		
		try {
			threadCnt = Integer.valueOf(threads);
		} catch (NumberFormatException e) {
			SwingComponent.infoBox("잘못된 양식의 입력값입니다.", "ERROR");
			return -1;
		}
		
		return threadCnt;
	}

	

	
}
