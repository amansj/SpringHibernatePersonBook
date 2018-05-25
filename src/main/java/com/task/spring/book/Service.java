package com.task.spring.book;

import java.util.List;

import com.task.spring.entity.Book;

public interface Service {
	public String add(Book book,String bookType);
	public String edit(Book book,String bookType);
	public String delete(String bookId);
	public Book list(String bookId);
	public List<Book> display();
}
