package com.encore.spring.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
public class Board implements Serializable {
	private int no        	 ;
	private String id        ;
	private String title     ;
	private String contents  ;
	private String file_name;
	
	
	public Board() {};
	public Board(String id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}
	
	public Board(String id, String title, String contents, String file_name) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.file_name = file_name;
	}
	public Board(int no, String id, String title, String contents, String file_name) {
		super();
		this.no = no;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.file_name = file_name;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", id=" + id + ", title=" + title + ", contents=" + contents + ", fileName="
				+ file_name + "]";
	}
	public String getFileName() {
		return file_name;
	}
	public void setFileName(String file_name) {
		this.file_name = file_name;
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
	
}
