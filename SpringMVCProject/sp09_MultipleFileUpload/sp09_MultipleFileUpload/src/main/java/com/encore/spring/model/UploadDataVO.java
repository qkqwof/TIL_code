package com.encore.spring.model;
import java.util.List;

/*
 * 파일 업로드 폼을 잘 보고 만들어야 한다.
*/
import org.springframework.web.multipart.MultipartFile;

public class UploadDataVO {
	private String userName;
	private List<MultipartFile> uploadFile;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile>  uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
}
