package com.encore.spring.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadDataVO {
	private String userName;
	private MultipartFile uploadFile; //uploadFile이름으로 해야지만 업로드한 파일정보를 담을 수 있다.
	
	private List<MultipartFile> file;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
}
