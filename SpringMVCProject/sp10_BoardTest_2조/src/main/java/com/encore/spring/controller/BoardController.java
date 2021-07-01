package com.encore.spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.Board;
import com.encore.spring.domain.UploadDataVO;
import com.encore.spring.model.BoardService;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("boardInput.do")
	public ModelAndView boardInput() {		
		System.out.println("boardInput.do method in");
		//게시판 입력폼인 board/boardInsert.jsp로 결과 페이지 연결한다.
		return new ModelAndView("boardInsert");
	}

	@RequestMapping("boardInsert.do")
	public ModelAndView boardInsert(Board board, UploadDataVO upload, HttpServletRequest request) throws Exception {		
		
		String path;
		
		try {
			
			MultipartFile mFile = upload.getUploadFile();
			System.out.println("mFile :: " + mFile);
			
			if((mFile.isEmpty())!=true) { // 업로드된 파일이 있다면
				System.out.println("파일의 사이즈:: " + mFile.getSize());
				System.out.println("업로드된 파일명:: " + mFile.getOriginalFilename());
				System.out.println("파일의 파라미터 명:: " + mFile.getName());
				
				String root = request.getSession().getServletContext().getRealPath("/");
				System.out.println("ROOT :: "+ root);
				String path1 = root +"upload\\";
				
				File copyFile = new File(path1 + mFile.getOriginalFilename());
				mFile.transferTo(copyFile);
				
				board.setFilename(mFile.getOriginalFilename());
			}
			else {
				board.setFilename(null);
			}
			System.out.println(board.getFilename());
			
			boardService.insert(board);
			path = "/boardList.do";
		}catch(Exception e){
			System.out.println(e);
			request.getSession().setAttribute("msg", "글 입력시 에러 발생!");
			path = "Error.jsp";
		}
		
		return new ModelAndView("redirect:" + path);
	}

	@RequestMapping("boardList.do")
	public ModelAndView boardList(HttpServletRequest request) throws Exception {
		System.out.println("boardList.do method in");
		
		String path;
		List<Board> list = null;
		
		try {
			list = boardService.selectAll();
			path = "boardList";
		}catch(Exception e){
			System.out.println(e);
			request.setAttribute("msg", "목록 가져오는 중 에러 발생!");
			path = "Error.jsp";
		}
		
		return new ModelAndView(path,"list",list);
	}

	@RequestMapping("boardDetail.do")
	public ModelAndView boardDetail(String no,HttpServletRequest request) throws Exception {
		System.out.println("boardDetail.do method in");
		
		String path;
		Board board = null;
		
		try {
			board = boardService.selectOne(no);
			path = "boardDetail";
		}catch(Exception e){
			System.out.println(e);
			request.setAttribute("msg", "글 가져오는 중 에러 발생!");
			path = "Error.jsp";
		}
		
		return new ModelAndView(path, "vo", board);
	}
	
	@RequestMapping("fileDown.do")
	public ModelAndView filedown(HttpServletRequest request, String filename) throws Exception{
		System.out.println("filename :: " + filename);
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root +"upload\\";
		
		HashMap map = new HashMap();
		map.put("path", path);
		return new ModelAndView("downloadView", map);
	}
}
