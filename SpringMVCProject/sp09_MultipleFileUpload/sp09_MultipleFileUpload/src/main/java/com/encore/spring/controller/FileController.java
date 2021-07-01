package com.encore.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.model.UploadDataVO;

@Controller
public class FileController {

	@RequestMapping("fileupload.do")
	public ModelAndView fileUpload(HttpServletRequest request ,UploadDataVO vo) throws Exception{
		List<MultipartFile> list=vo.getUploadFile();
		List<String> fnameList = new ArrayList<String>();
		/*
		 * 2. 업로드된 파일이 있다면 
		 *	  파일의 사이즈
		 *	  업로드한 파일의 이름
		 *    업로드한 파일의 파라미터명?
		 *    
		 */
		for(MultipartFile mFile :list) {
			
			if((list.isEmpty())!=true) {//업로드된 파일이 있다면
			System.out.println("파일의 사이즈 :: "+mFile.getSize());
			System.out.println("업로드된 파일명 :: "+mFile.getOriginalFilename());
			System.out.println("파일의 파라미터명 :: "+mFile.getName());	}
		
			//3.업로드한 파일을 지정한 경로에다 copy해서 이동시킴... /upload/copy해온 파일이 저장됨 
			String root=request.getSession().getServletContext().getRealPath("/");
			String path=root+"\\upload\\";
			
			//4.File 객체 생성.. mFile.transferTo()
			File copyFile=new File(path+mFile.getOriginalFilename());
			mFile.transferTo(copyFile);//업로드한 파일의 카피본이 해당 경로에 저장된다..
			System.out.println("path :: "+path);
			System.out.println(mFile.getOriginalFilename());
			fnameList.add(mFile.getOriginalFilename());
			}
			System.out.println(fnameList);
			return new ModelAndView("upload_result","fnameList",fnameList);
			
		}
	
	@RequestMapping("fileDown.do")
	public ModelAndView fileDown(HttpServletRequest request ,String filename) throws Exception{
		System.out.println("filename :: "+filename);
		String root=request.getSession().getServletContext().getRealPath("/");
		String path=root+"\\upload\\";
		
		HashMap map=new HashMap();
		map.put("path", path);
		return new ModelAndView("downloadView",map);
	}

}
