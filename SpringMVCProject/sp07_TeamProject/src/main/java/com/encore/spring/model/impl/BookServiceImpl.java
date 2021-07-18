package com.encore.spring.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.spring.domain.MyBook;
import com.encore.spring.model.MyBookDAO;
import com.encore.spring.model.MyBookService;

@Service
public class BookServiceImpl implements MyBookService{
	@Autowired
	private MyBookDAO MyBookDAO;
		
	
	@Override
	public void addBook(MyBook book) throws Exception {
		System.out.println(book+"service");
		MyBookDAO.addBook(book);
	}
	@Override
	public List<MyBook> getBookList() throws Exception {
		return MyBookDAO.getBookList();
	}
}
