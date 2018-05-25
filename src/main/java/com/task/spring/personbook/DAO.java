package com.task.spring.personbook;

import java.util.List;

import com.task.spring.entity.Book;
import com.task.spring.entity.Person;

public interface DAO {
	public String assignBook(Person person,Book book);
	public Person viewPerson(String bookId);
	public List<Book> viewBook(String personId);
	public String unassignBook(Person person,Book book);
	public String addPerson(Person person);
	public List<Person> displayAll();
	public Book getBook(String bookId);
	public Person getPerson(String personId);
}
