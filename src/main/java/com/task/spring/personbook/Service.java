package com.task.spring.personbook;

import java.util.List;

import com.task.spring.entity.Book;
import com.task.spring.entity.Person;

public interface Service {
	public String assign(String bookId,String personId);
	public Person viewPerson(String bookId);
	public List<Book> viewBooks(String personId);
	public String unassign(String personId,String bookId);
	public String addPerson(Person person);
	public List<Person> displayAll();
	public Book list(String bookId);
}
