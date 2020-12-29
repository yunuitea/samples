package com.example.samples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.samples.service.BookService;
import com.example.samples.vo.BookInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BooksController {
	
	
	@Autowired
	BookService service;
	
	@RequestMapping(value="/registerForm")
	public ModelAndView registerForm() {
		ModelAndView view = new ModelAndView();
		view.setViewName("book/register");
		return view;
	}


	@RequestMapping(value="/list")
	public ModelAndView bookList(@RequestParam(value = "searchText", required = false) String searchText) {
		ModelAndView view = new ModelAndView();

		List<BookInfo> bookInfoList = null;
		Map<String, Object> param = new HashMap<>();

		try {

			if(searchText !=null && searchText.length()  > 0) {
				param.put("searchText", searchText);
			}

			bookInfoList = service.getBookList(param);

			if(bookInfoList != null) {
				view.addObject("bookSize", bookInfoList.size());
				view.addObject("bookList", bookInfoList);
			}else {
				view.addObject("bookSize", 0);
				view.addObject("bookList", new ArrayList<BookInfo>());
			}

		}catch (Exception e) {
			e.printStackTrace();
			view.addObject("bookSize", 0);
			view.addObject("bookList", new ArrayList<BookInfo>());
		}

		view.setViewName("book/bookList");
		return view;
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView registerBook(BookInfo info)  {
		
		ModelAndView view = new ModelAndView();
		int resultCode =0;
		
		try {
			
			resultCode = service.registerBook(info);
			
			if(resultCode > 0) {
				view.setViewName("book/registerDone");
			}else {
				throw new Exception("insert Error");
			}
			
		}catch (Exception e) {
			System.out.println("Error!! :" + e.getMessage());
			view.setViewName("book/registerError");
		}
		
		return view;
	}


	@RequestMapping(value = "/modify")
	public ModelAndView modifyBook(@RequestParam(value = "bookNo") String bookNo)  {

		ModelAndView view = new ModelAndView();
		BookInfo vo  = new BookInfo();

		try {
			Map<String, Object> param = new HashMap<>();
			param.put("bookNo", Integer.parseInt(bookNo));
			vo = service.getBook(Integer.parseInt(bookNo));

		}catch (Exception e) {
			System.out.println("Error!! :" + e.getMessage());
		}

		view.addObject("book", vo);
		view.setViewName("book/modifyBook");
		return view;
	}


	@RequestMapping(value = "/update")
	public ModelAndView modifyBook(BookInfo info)  {

		ModelAndView view = new ModelAndView();
		int resultCode =0;

		try {
			resultCode = service.updateBook(info);
			if(resultCode > 0) {
				view.setViewName("book/registerDone");
			}else {
				throw new Exception("insert Error");
			}

		}catch (Exception e) {
			System.out.println("Error!! :" + e.getMessage());
			view.setViewName("book/registerError");
		}
		return view;
	}


	@RequestMapping(value = "/delete")
	public ModelAndView deleteBook(@RequestParam(value = "bookNo") String bookNo)  {

		ModelAndView view = new ModelAndView();
		int resultCode =0;

		try {
			Map<String, Object> param = new HashMap<>();
			param.put("bookNo", Integer.parseInt(bookNo));

			resultCode = service.deleteBook(Integer.parseInt(bookNo));
			if(resultCode > 0) {
				view.setViewName("book/deleteDone");
			}else {
				throw new Exception("insert Error");
			}

		}catch (Exception e) {
			System.out.println("Error!! :" + e.getMessage());
			view.setViewName("book/registerError");
		}
		return view;
	}

}
