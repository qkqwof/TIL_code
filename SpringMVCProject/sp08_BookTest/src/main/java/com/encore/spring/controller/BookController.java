package com.encore.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.service.BookService;
import com.encore.spring.vo.BookVO;


@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("bookList.do")
	public ModelAndView getBooks() throws Exception{
		List<BookVO> list = bookService.getBooks();
		
		for(BookVO b : list) System.out.println(b);
		
		return new ModelAndView("book/bookList", "list", list);
	}
	
	@RequestMapping("bookRegister.do")
	public ModelAndView register(BookVO book, HttpServletRequest request, HttpSession session) throws Exception{
		System.out.println("register BookVO before :: "+book);
		
		String isbn1 = request.getParameter("isbn1").trim();
		String isbn2 = request.getParameter("isbn2").trim();
		String isbn3 = request.getParameter("isbn3").trim();
		book.setIsbn(isbn1+"-"+isbn2+"-"+isbn3);
		
		System.out.println("register BookVO after :: "+book);
		
		String msg = "";
		String path = "Error.jsp";
		
		try {
			bookService.insertBook(book);//정상적으로 book이 add 되었다.
			//디비에 성공적으로 등록을 하면 다시 이곳으로 리턴된다.
			msg = "책 정보가 정상적으로 디비에 저장되었습니다.";
			path = "result.jsp";
		}catch(Exception e) {
			//디비에 성공적으로 등록을 못했다면 이곳으로 리턴된다.
			msg = "책 정보 저장중 오류 발생...";
		}
		//바인딩
		session.setAttribute("msg",msg); //페이지 이동 방법을 
		return new ModelAndView("redirect:"+path);
	}
	
	@RequestMapping("bookSearch.do")
	public ModelAndView search(HttpServletRequest request, String searchField, String searchText) throws Exception{
		System.out.println(searchField+","+searchText);
		
		List<BookVO> list = null;
		String path = "find_fail";
		
		try {
			switch(searchField) {
			case "TITLE":
				list = bookService.searchByTitle(searchText);
				break;
			case "PUBLISHER":
				list = bookService.searchByPublisher(searchText);
				break;
			case "PRICE":
				list = bookService.searchByPrice(Integer.parseInt(searchText));
				break;
				
			default:
				list = bookService.getBooks();
			}//switch
			
			path = "book/bookList";
			request.setAttribute("field",searchField);
			request.setAttribute("text",searchText);
		}catch(Exception e) {
			System.out.println(e);
			request.setAttribute("msg","도서 검색중 오류 발생했습니다.");
		}
		
//		if(searchField.equals("LIST")) {
//			list = bookService.getBooks();
//		} else if(searchField.equals("TITLE")) {
//			list = bookService.searchByTitle(searchText);
//		} else if(searchField.equals("PUBLISHER")) {
//			list = bookService.searchByPublisher(searchText);
//		} else if(searchField.equals("PRICE")) {
//			list = bookService.searchByPrice(Integer.parseInt(searchText));
//		}
		
		return new ModelAndView(path,"list",list);
	}
	
	@RequestMapping("bookView.do")
	public ModelAndView bookview(String isbn, HttpServletRequest request) throws Exception{
		String path = "find_fail.jsp";
		BookVO book = null;
		
		try {
			book = bookService.searchByIsbn(isbn);
			path = "book/bookView";
		}catch(Exception e) {
			System.out.println(e);
			request.setAttribute("msg","isbn으로 검색중 오류 발생했습니다.");
		}
		
		
		return new ModelAndView(path,"b",book);
	}
	
	@RequestMapping("bookAjax.do")
	public String bookAjax(String isbn, Model model) throws Exception {
		//System.out.println(isbn);
		BookVO book = bookService.searchByIsbn(isbn);
		//System.out.println(book);
		model.addAttribute("bvo", book);
		
		return "JsonView";
	}
}
