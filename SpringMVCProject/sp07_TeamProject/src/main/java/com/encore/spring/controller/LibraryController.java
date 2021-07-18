package com.encore.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.MyBook;
import com.encore.spring.domain.User;
import com.encore.spring.model.MyBookService;
import com.encore.spring.model.UserService;


@Controller
public class LibraryController {
	@Autowired
	private MyBookService myBookService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("front.do")
	public ModelAndView login(String userid,String password) throws Exception{
		
		User user = new User(userid,password);
		User user2=userService.login(user);
		if(user2!=null) {
			return new ModelAndView("LoginSucess", "vo",user2);
		}else {
			return new ModelAndView("error");
		}
		
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		session.invalidate();
		return new ModelAndView("logout");
	}
	
	@RequestMapping("addBook.do")
	public ModelAndView addBook(String isbn, String title, String catalogue, String nation, String publish_date, String publisher,
			String author, int price, String currency, String description) throws Exception{
		
		System.out.println(isbn+title+catalogue+nation+publish_date+publisher+author+price+currency+description);
		MyBook book = new MyBook(isbn, title, catalogue, nation,publish_date,publisher,author, price, currency,description);
		
		System.out.println(book);
		try {
			myBookService.addBook(book);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("BookSucess","vo",book);
		
		
	}
	@RequestMapping("bookList.do")
	public ModelAndView bookList() throws Exception{
		
		List<MyBook> list = myBookService.getBookList();
	
		return new ModelAndView("BookAllView","list",list);
		
	}	
}
