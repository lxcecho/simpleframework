package com.xc.joy.annotation.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.xc.joy.annotation.dao.BookDao;

@Service
public class BookService {

	/*@Qualifier("bookDao")
	@Autowired(required=false)*/
//	@Resource
	@Inject
	private BookDao bookDao;
	
	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao + "]";
	}

	public void print() {
		System.out.println(bookDao);
	}
}
