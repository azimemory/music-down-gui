package com.music.music.dto;

public class Music {
	
	private String musician;
	private String title;
	private String url;
	
	public Music(String musician, String title) {
		super();
		this.title = title;
		this.musician = musician;
	}

	public String getTitle() {
		return title;
	}

	public String getMusician() {
		return musician;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}