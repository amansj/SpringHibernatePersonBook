package com.task.spring.personbook;

import java.util.ArrayList;
import java.util.List;

import com.task.spring.entity.Book;
import com.task.spring.entity.Person;

public class HibernateService implements Service {
	DAO dao;
	public HibernateService() {
		// TODO Auto-generated constructor stub
		
	}
	HibernateService(DAO dao)
	{
		this.dao=dao;
	}
	public List<Book> addBook(List<Book> books,Book book)
	{
		if(books==null)
		{
			books=new ArrayList<>();
		}
		books.add(book);
		return books;
		//book.setPerson(this);
	}
	private int indexOf(List<Book> books,Book book)
	{
		for(int i=0;i<books.size();i++)
		{
			if(books.get(i).getId().equals(book.getId()))
			{
				return i;
			}
		}
		return -1;
	}
	public int deleteBook(List<Book> books,Book book)
	{
		int index=indexOf(books,book);
		if(index!=-1)
		{
			books.remove(index);
			
		}
		return index;
	}
	@Override
	public String assign(String bookId, String personId) {
		// TODO Auto-generated method stub
		Book book=dao.getBook(bookId);
		if(book==null)
		{
			return "No Such Book Found";
		}
		Person person=dao.getPerson(personId);
		if(person==null)
		{
			return "No Such Person Found";
		}
		List<Book> books=dao.viewBook(personId);
		books=addBook(books,book);
		try {
		//	book.setPerson(person);
			person.setBooks(books);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return dao.assignBook(person,book);
	}

	@Override
	public Person viewPerson(String bookId) {
		// TODO Auto-generated method stub
		return dao.viewPerson(bookId);
	}

	@Override
	public List<Book> viewBooks(String personId) {
		// TODO Auto-generated method stub
		return dao.viewBook(personId);
	}

	@Override
	public String unassign(String personId, String bookId) {
		// TODO Auto-generated method stub
		Book book=dao.getBook(bookId);
		if(book==null)
		{
			return "No Such Book Found";
		}
		Person person=dao.getPerson(personId);
		if(person==null)
		{
			return "No Such Person Found";
		}
		List<Book> books=dao.viewBook(personId);
		int index=deleteBook(books, book);
		if(index==-1)
		{
			return "No Such Book Assigned";
		}
		person.setBooks(books);
		book.setPerson(null);
		return dao.unassignBook(person, book);
	}

	@Override
	public String addPerson(Person person) {
		// TODO Auto-generated method stub
		return dao.addPerson(person);
	}

	@Override
	public List<Person> displayAll() {
		// TODO Auto-generated method stub
		return dao.displayAll();
	}

	@Override
	public Book list(String bookId) {
		// TODO Auto-generated method stub
		return dao.getBook(bookId);
	}

}
