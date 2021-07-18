package com.encore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.rest.service.BookService;
import com.encore.rest.vo.Book;

@RestController
@RequestMapping("api")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	//http://127.0.0.1:8888/rest/api/book
	@GetMapping("book")
	public ResponseEntity<List<Book>> getAllBook() throws Exception { // 그냥 List를 리턴타입으로 하는것보다 ResponseEntity안에 넣으면 Powerful한 서비스가 가능하다
		List<Book> books = bookService.getBooks();
		if(books.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT); //에러(실패해서)...내용 없는 경우
		else return new ResponseEntity(books, HttpStatus.OK); // 성공했을 경우
	}
	
	// 특정한 isbn에 해당하는 책 1권을 받아오는 요청을 작성
	////http://127.0.0.1:8888/rest/api/book/1233-111-111
	@GetMapping("book/{isbn}") //Restful Service에서는 Convention이 있기 때문에 Convention 중요
	public ResponseEntity<Book> getBook(@PathVariable String isbn) throws Exception {
		Book book = bookService.searchByIsbn(isbn);
		if(book==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(book, HttpStatus.OK);
	}
	
	//특정한 책을 등록하는 기능..insert..doPost()
	@PostMapping("book")
	public ResponseEntity<Book> insertBook(@RequestBody Book book) throws Exception { //insert에서는 RequestBody를 통해서 추가하고자 하는 객체를 넣는다
		boolean check = bookService.insertBook(book);
		if(!check) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(book, HttpStatus.OK); // 추가 된 경우
	}
	
	//특정한 책을 수정하는 기능..UPDATE..doPut()
	@PutMapping("book")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) throws Exception {
		boolean check = bookService.update(book);
		if(!check) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(HttpStatus.OK); //리턴되는 것 없는 void이므로 update 잘됐다는 ok사인만 필요함...위와 다르게 book 필요없음
	}
	
	//특정한 책을 삭제하는 기능..DELETE..doDelete()..삭제할 때는 특정한 isbn을 넘겨야 한다.
	@DeleteMapping("book/{isbn}")
	public ResponseEntity<List<Book>> deleteBook(@PathVariable String isbn) throws Exception {
		boolean check = bookService.delete(isbn);
		if(!check) return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		else return new ResponseEntity<List<Book>>(HttpStatus.OK);//리턴되는 것 없는 void이므로 delete 잘됐다는 ok사인만 필요함...
	}
}