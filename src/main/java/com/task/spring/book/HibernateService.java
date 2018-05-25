package com.task.spring.book;

import java.util.List;

import com.task.spring.entity.Book;

public class HibernateService implements Service {
	DAO dao;
	public HibernateService() {
		// TODO Auto-generated constructor stub
		
	}
	HibernateService(DAO dao)
	{
		this.dao=dao;
	}
	@Override
	public String add(Book book, String bookType) {
		// TODO Auto-generated method stub
		return dao.addBookDetails(book, bookType);
	}

	@Override
	public String edit(Book book, String bookType) {
		// TODO Auto-generated method stub
		return dao.editBookDetails(book,bookType);
	}

	@Override
	public String delete(String bookId) {
		// TODO Auto-generated method stub
		return dao.deleteBookDetails(bookId);
	}

	@Override
	public Book list(String bookId) {
		// TODO Auto-generated method stub
		return dao.listBookDetails(bookId);
	}

	@Override
	public List<Book> display() {
		// TODO Auto-generated method stub
		return dao.displayAll();
	}

}
