package com.encore.spring.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board implements Serializable {
	private int no        	 ;
	private String id        ;
	private String title     ;
	private String contents  ;
	private String filename;
	
	
	public Board() {};
	public Board(String id, String title, String contents, String filename) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.filename = filename;
	}

	public Board(int no, String id, String title,  String contents, String filename) {
		super();
		this.no = no;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.filename = filename;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [no=").append(no).append(", id=").append(id).append(", title=").append(title)
				.append(", contents=").append(contents).append(filename);
		return builder.toString();
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
