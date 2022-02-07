package com.music.music.dto;


public class YtDlpDTO {
	
	private String fileName;
	private String videoURL;
	
	private YtDlpDTO(Builder builder) {
		this.fileName = builder.fileName;
		this.videoURL = builder.videoURL;
	}
	
	public static class Builder{
		
		private String fileName;
		private String videoURL;
		
		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public Builder videoURL(String videoURL) {
			this.videoURL = videoURL;
			return this;
		}
		
		
		public YtDlpDTO build() {
			return new YtDlpDTO(this);
		}
	}
	
	
	public String getFileName() {
		return fileName;
	}
	
	
	public String getVideoURL() {
		return videoURL;
	}

}
