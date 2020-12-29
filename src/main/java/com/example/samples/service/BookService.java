package com.example.samples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.samples.dao.BooksMapper;
import com.example.samples.vo.BookInfo;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
	
	@Autowired
	BooksMapper mapper;

	public int registerBook(BookInfo info) throws Exception{
		return mapper.registerBook(info);
	}

	public List<BookInfo> getBookList(Map<String, Object> param) throws Exception{
		return mapper.getBookList(param);
	};
	public BookInfo getBook(int bookNo) throws Exception {
			return mapper.getBook(bookNo);
	}

	public int updateBook(BookInfo info) throws Exception {
		return mapper.updateBook(info);
	}
	public int deleteBook(int bookNo) throws Exception{
		return mapper.deleteBook(bookNo);
	}
}
