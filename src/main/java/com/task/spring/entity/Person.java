package com.task.spring.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	@Id
	@Column(name="personid")
	private String personid;
	@Column(name="person_name")
	private String personName;
	@Column(name="Address")
	private String address;
	@OneToMany(fetch=FetchType.LAZY,cascade= {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name="personbook",
	joinColumns=@JoinColumn(name="personid"),
	inverseJoinColumns=@JoinColumn(name="bookId")
			)
	private List<Book> books;
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String personid, String personName, String address) {
		super();
		this.personid = personid;
		this.personName = personName;
		this.address = address;
	}
	@Override
	public String toString() {
		return ("********************************************************************"+"\n"+
		"PersonId:"+getPersonid()+"\n"+
		"PersonName : "+getPersonName()+"\n"+
		"Address: "+getAddress()+"\n");
	}
/*	public void addBook(Book book)
	{
		if(books==null)
		{
			books=new ArrayList<>();
		}
		books.add(book);
		//book.setPerson(this);
	}
	private int indexOf(Book book)
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
	public String deleteBook(Book book)
	{
		int index=indexOf(book);
		if(index!=-1)
		{
			books.remove(index);
			return "Successfully Unassigned";
		}
		return "No Such Book Assigned";
	}*/
}
