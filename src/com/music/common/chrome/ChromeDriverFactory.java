package com.music.common.chrome;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 크롬 드라이버 펙토리 클래스
 * @author azi
 *
 */
public class ChromeDriverFactory {
	
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "chromedriver.exe";
	private final ChromeOptions options = new ChromeOptions();
	private static final ChromeDriverFactory INSTANCEC = new ChromeDriverFactory();
	
	public static ChromeDriverFactory getInstance() {
		return INSTANCEC;
	}
	
	private ChromeDriverFactory() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	}
	
	public ChromeDriver getChromeDriver() {
		return new ChromeDriver(options);
	}
	
	

}
