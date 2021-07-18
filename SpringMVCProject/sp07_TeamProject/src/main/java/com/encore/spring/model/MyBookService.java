package com.encore.spring.model;

import java.util.List;

import com.encore.spring.domain.MyBook;

public interface MyBookService {
	void addBook(MyBook book) throws Exception;
	List<MyBook> getBookList()throws Exception;
}
