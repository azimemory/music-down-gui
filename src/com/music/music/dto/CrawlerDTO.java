package com.music.music.dto;

public class CrawlerDTO {
	
	private String url;
	private String cssSelector;
	private String htmlAttribute;
	private String result;
	
	private CrawlerDTO(Builder builder) {
		super();
		this.url = builder.url;
		this.cssSelector = builder.cssSelector;
		this.htmlAttribute = builder.htmlAttribute;
	}
	
	public static class Builder {
		
		private String url;
		private String cssSelector;
		private String htmlAttribute;
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder cssSelector(String cssSelector) {
			this.cssSelector = cssSelector;
			return this;
		}
		
		public Builder htmlAttribute(String htmlAttribute) {
			this.htmlAttribute = htmlAttribute;
			return this;
		}
		
		public CrawlerDTO build() {
			return new CrawlerDTO(this);
		}
	}

	public String getUrl() {
		return url;
	}
	
		
	public String getCssSelector() {
		return cssSelector;
	}
	

	public String getHtmlAttribute() {
		return htmlAttribute;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
