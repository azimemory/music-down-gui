package com.music.common.util;

import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.music.common.chrome.ChromeDriverFactory;
import com.music.music.dto.CrawlerDTO;

/**
 * selenium을 사용해 다운 받을 음악의 url을 크롤링하는 쓰래드
 * @author azi
 *
 */
public class URLCrawler implements Runnable{
	
	private LinkedBlockingQueue<CrawlerDTO> taskQueue;
	
	public URLCrawler(LinkedBlockingQueue<CrawlerDTO> taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	
	/**
	 * LinkedBlockingQueue안에 music이 없을 때 까지 반복하여 크롤링
	 */
	@Override
	public void run() {
		while(!taskQueue.isEmpty()) {
			try {
				crawlingURL(taskQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void crawlingURL(CrawlerDTO dto) {
	
		WebDriver driver = ChromeDriverFactory.getInstance().getChromeDriver(); 
		
		try {
			
			driver.get(dto.getUrl());
			
			Thread.sleep(2000);
			
			WebElement element = driver.findElement(By.cssSelector(dto.getCssSelector()));
			dto.setResult(element.getAttribute(dto.getHtmlAttribute()));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			driver.close(); 
		}
	}
	

	

	
}
