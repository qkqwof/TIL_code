package com.encore.spring.controller;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.model.BoardService;
import com.encore.spring.vo.Board;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("boardInput.do")
	public ModelAndView boardInput() {		
		return new ModelAndView("boardInput");
	}

	@RequestMapping("boardInsert.do")
	public ModelAndView boardInsert(Board board,MultipartFile file,HttpServletRequest request) throws Exception {
		ModelAndView mav = null;
		try {
			if(file!=null) {
				String path = request.getSession().getServletContext().getRealPath("/")+"\\upload\\";
				File copyFile = new File(path+file.getOriginalFilename());
				board.setFileName(file.getOriginalFilename());
				file.transferTo(copyFile);
				board.setFileName(file.getOriginalFilename());				
			}
			boardService.insert(board);	
			mav = new ModelAndView("redirect:boardList.do");
		}
		catch(Exception e){
			mav = new ModelAndView("errorPage","msg","글 입력중 에러가 발생하였습니다.");
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("boardList.do")
	public ModelAndView boardList() throws Exception {		
		return new ModelAndView("boardList","list",boardService.selectAll());
	}

	@RequestMapping("boardDetail.do")
	public ModelAndView boardDetail(String no) throws Exception {	
		return new ModelAndView("boardDetail","vo",boardService.selectOne(no));
	}
	
	@RequestMapping("fileDown.do")
	public ModelAndView filedown(HttpServletRequest request, String filename) throws Exception{
		System.out.println("filename::"+filename);
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root+"\\upload\\";
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("path",path);
		return new ModelAndView("downloadView",map);
	}
}
