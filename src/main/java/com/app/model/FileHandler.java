package com.app.model;

import java.time.LocalDateTime;

public class FileHandler {
	private int fileId;
	private String fileName;
	private LocalDateTime lastAccessed;
	
	public FileHandler(int fileId, String fileName, LocalDateTime lastAccessed) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.lastAccessed = lastAccessed;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
}
