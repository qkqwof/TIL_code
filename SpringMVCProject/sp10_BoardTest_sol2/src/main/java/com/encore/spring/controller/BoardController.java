package com.encore.spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.service.BoardService;
import com.encore.spring.vo.Board;
import com.encore.spring.vo.UploadDataVO;


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

	@RequestMapping(value="boardInsert.do")
	public ModelAndView boardInsert(HttpServletRequest request,Board board,UploadDataVO vo) throws Exception {
		String msg="";
		String path2="";
		
		MultipartFile mFile = vo.getUploadFile();
		System.out.println("mFile ::" + mFile);
		
		/*
		 * 2. 업로드된 파일이 있다면
		 *    파일의 사이즈
		 *    업로드한 파일의 이름
		 *    업로드한 파일의 파라미터명
		 */
		if((mFile.isEmpty())!=true) {//업로드된 파일이 있다면
			System.out.println("파일의 사이즈  :: " + mFile.getSize());
			System.out.println("업로드된 파일명  :: " + mFile.getOriginalFilename());
			System.out.println("파일의 파라미터명:: " + mFile.getName());
		}
		
		//3. 업로드한 파일을 지정한 경로에 copy해서 이동시킴... /upload/copy 해온 파일이 저장됨.
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root::"+root);
		String path = root + "\\upload\\";
		
		//4. File 객체 생성... mFile.transferTo()
		File copyFile = new File(path+mFile.getOriginalFilename());
		mFile.transferTo(copyFile);//업로드한 파일의 카피본이 해당경로에 저장된다.. 이동한다
		System.out.println("path :: "+ path);
		
		try {
			board.setFileName(mFile.getOriginalFilename());
			boardService.insert(board);
			path2="index.jsp";
			msg="게시글이 작성되었습니다.";
		}catch(Exception e) {
			msg="게시글이 작성 실패하였습니다.";
			path2="error.jsp";
			System.out.println(e);
		}
		
		request.getSession().setAttribute("msg", msg);//로그인 성공 여부에 따라서 메세지가 달라진다.//request사용 시 session에 바인딩 필요
		return new ModelAndView("redirect:"+path2);
	}

	@RequestMapping("boardList.do")
	public ModelAndView boardList() throws Exception {
		System.out.println("boardList.do method in");
		List<Board> list = boardService.selectAll();
		return new ModelAndView("boardList","list",list);
	}

	@RequestMapping("boardDetail.do")
	public ModelAndView boardDetail(String no) throws Exception {
		System.out.println("boardDetail.do method in");
		
		Board board = boardService.selectOne(no);
		System.out.println(board);
		return new ModelAndView("boardDetail","vo",board);
	}
	@RequestMapping("fileDown.do")
	public ModelAndView filedown(HttpServletRequest request,String filename)throws Exception{
		System.out.println("filename :: "+filename);
		String root = request.getSession().getServletContext().getRealPath("/");		
		String path = root+"\\upload\\";
		
		HashMap map = new HashMap();
		map.put("path", path);
		return new ModelAndView("downloadView",map);
	}
	@RequestMapping("delete.do")
	public ModelAndView boarddelete(String no) throws Exception {
		System.out.println("boardDetail.do method in");
		
		boardService.delete(no);
		
		return new ModelAndView("redirect:index.jsp");
	}
}
