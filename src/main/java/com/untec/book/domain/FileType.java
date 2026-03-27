package com.untec.book.domain;

public enum FileType {
	PDF("pdf"),EPUB("epub");
	
	private final String type;
	
	private FileType(String type) {
		this.type = type;
	}
	
	public static FileType fromString(String text) {
		for (FileType ft : FileType.values()) {
			if (ft.type.equalsIgnoreCase(text)) {
				return ft;
			}
		}
		return null;
	}
	
	public String getType() {
		return type;
	}
}
