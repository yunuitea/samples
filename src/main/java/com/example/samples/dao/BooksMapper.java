package com.example.samples.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.samples.vo.BookInfo;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BooksMapper {
	int registerBook(BookInfo info) throws Exception;
	List<BookInfo> getBookList(Map<String, Object> param) throws Exception;
	BookInfo getBook(int bookNo) throws Exception;
	int updateBook(BookInfo info) throws Exception;
	int deleteBook(int bookNo) throws Exception;
}
