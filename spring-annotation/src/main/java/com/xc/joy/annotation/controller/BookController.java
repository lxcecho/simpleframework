package com.xc.joy.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xc.joy.annotation.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
}
