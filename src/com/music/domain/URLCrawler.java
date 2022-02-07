package com.music.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.music.common.chrome.ChromeDriverFactory;
import com.music.dto.Music;

/**
 * selenium을 사용해 다운 받을 음악의 url을 크롤링하는 쓰래드
 * @author azi
 *
 */
public class URLCrawler implements Runnable{
	
	private LinkedBlockingQueue<Music> musicQueue;
	
	public URLCrawler(LinkedBlockingQueue<Music> musicQueue) {
		this.musicQueue = musicQueue;
	}
	
	
	/**
	 * LinkedBlockingQueue안에 music이 없을 때 까지 반복하여 크롤링
	 */
	@Override
	public void run() {
		while(!musicQueue.isEmpty()) {
			try {
				crawlingURL(musicQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void crawlingURL(Music music) {
		
		WebDriver driver = ChromeDriverFactory.getInstance().getChromeDriver(); 
		String url = "";
		String search = music.getMusician() + " - " + music.getTitle();
		
		try {
			
			String encoded = URLEncoder.encode(search, "UTF-8");
			driver.get("https://www.youtube.com/results?search_query="+encoded);
			
			Thread.sleep(2000);
			
			WebElement element = driver.findElement(By.cssSelector("a[href^=\"/watch?\"]"));
			url = element.getAttribute("href");
			music.setUrl(url);
			
		} catch (InterruptedException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
			driver.close(); 
		}
	}

	

	
}
