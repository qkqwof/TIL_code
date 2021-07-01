package com.encore.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		System.out.println("BookVO: "+book);
		
		String isbn1 = request.getParameter("isbn1").trim();
		String isbn2 = request.getParameter("isbn2").trim();
		String isbn3 = request.getParameter("isbn3").trim();
		book.setIsbn(isbn1+"-"+isbn2+"-"+isbn3);
		
		System.out.println("register BookVO after:: "+book);
		
		String msg = "";
		String path = "Error.jsp";
		
		try {
			bookService.insertBook(book); //���������� book�� add �Ǿ���.
			// db�� ���������� ����� �ϸ� �ٽ� �̰����� ���ϵȴ�.
			msg = "å ������ ���������� ����Ǿ����ϴ�.";
			path = "result.jsp";
		}catch(Exception e) {
			System.out.println(e);
			msg="å ��� ����";
		}
		
		// ���ε�
		session.setAttribute("msg", msg); // ������ �̵� �����
		
		return new ModelAndView("redirect:"+path);
	}
	
	@RequestMapping("bookSearch.do")
	public ModelAndView search(String searchField, String searchText, HttpServletRequest request) throws Exception{
		System.out.println(searchField + ", "+searchText);
		
		List<BookVO> list = null;
		String path = "find_failed";
		
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
			request.setAttribute("field", searchField);
			request.setAttribute("text", searchText);
			
		}catch(Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "���� �˻� �� ������ �߻��߽��ϴ�.");
		}
		return new ModelAndView(path, "list", list);
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
			request.setAttribute("msg", "isbn���� �˻� �� ������ �߻��߽��ϴ�.");
		}
		return new ModelAndView(path, "b", book);
	}

	@RequestMapping("bookDesc.do")
	public ModelAndView bookDesc(String isbn, HttpServletRequest request) throws Exception{
		String path = "find_fail.jsp";
		BookVO book = null;
		System.out.println("isbn: "+isbn);
		String description="";
		
		try {
			book = bookService.searchByIsbn(isbn);
			
			System.out.println("book: "+book);

		}catch(Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "isbn���� �˻� �� ������ �߻��߽��ϴ�.");
		}
		return new ModelAndView("JsonView", "book", book);
	}
	
	
	

}
