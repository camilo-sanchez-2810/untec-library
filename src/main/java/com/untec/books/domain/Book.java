package com.untec.books.domain;

import java.util.UUID;

public class Book {
	private UUID id;
	private String title;
	private String coverUrl;
	private String description;
	private FileType fileType;
	private String fileUrl;
	private int languague;
	private int year;
	
	
	public Book(UUID id, String title, String coverUrl, String description, FileType fileType, String fileUrl, int languague,
			int year) {
		this.id = id;
		this.title = title;
		this.coverUrl = coverUrl;
		this.description = description;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.languague = languague;
		this.year = year;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCoverUrl() {
		return coverUrl;
	}


	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public FileType getFileType() {
		return fileType;
	}


	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


	public int getLanguague() {
		return languague;
	}


	public void setLanguague(int languague) {
		this.languague = languague;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
	
}
