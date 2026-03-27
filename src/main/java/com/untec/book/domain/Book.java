package com.untec.book.domain;

import java.util.UUID;

public class Book {
	private UUID id;
	private String title;
	private String coverUrl;
	private String description;
	private FileType fileType;
	private String fileUrl;
	private String language;
	private String authors;
	private String categories;
	private int year;
	
	
	public Book(UUID id, String title, String coverUrl, String description, FileType fileType, String fileUrl, String language, String authors, String categories,
			int year) {
		this.id = id;
		this.title = title;
		this.coverUrl = coverUrl;
		this.description = description;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.language = language;
		this.authors = authors;
		this.categories = categories;
		this.year = year;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public String getCategories() {
		return categories;
	}


	public void setCategories(String categories) {
		this.categories = categories;
	}


	public void setLanguage(String language) {
		this.language = language;
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
	
	public String getLanguage() {
		return language;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
	
}
