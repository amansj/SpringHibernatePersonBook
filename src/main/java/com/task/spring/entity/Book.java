package com.task.spring.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="book")
public class Book {
	
	@XmlAttribute(name="id")
	@Id
	@Column(name="bookId")
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name = "author")
	@Column(name="author")
	private String author;
	@XmlElement(name = "title")
	@Column(name="title")
	private String title;
	@XmlElement(name = "genre")
	@Column(name="genre")
	private String genre;
	@XmlElement(name = "publish_date")
	@Column(name="publish_date")
	private Date publish_date;
	@XmlElement(name = "description")
	@Column(name="description")
	private String description;
	@XmlElement(name = "price")
	@Column(name="price")
	private double price;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})		
	@JoinColumn(name="booktype")
	private BookType bookType;
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	//
	@OneToOne(fetch=FetchType.LAZY,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="personbook",
	joinColumns=@JoinColumn(name="bookId"),
	inverseJoinColumns=@JoinColumn(name="personid")
			)
	private Person person;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String d=formatter.format(getPublish_date());
		
		//String d=(getPublish_date().getYear()+1900)+"-"+(getPublish_date().getMonth()+1)+"-"+getPublish_date().getDate();
		return ("********************************************************************"+"\n"+
		"Id:"+getId()+"\n"+
		"author : "+getAuthor()+"\n"+
		"Title : "+getTitle()+"\n"+
		"Genre:"+getGenre()+"\n"+
		"Publish_Date(YYYY-MM-DD):"+d+"\n"+
		"Desc : "+getDescription()+"\n"+
		"Price : "+getPrice()+"\n"
		+"BookType:"+bookType.getBookType()+"\n"
		);
		/*return "Book [id=" + id + ", author=" + author + ", title=" + title + ", genre=" + genre + ", publish_date="
				+ publish_date + ", description=" + description + ", price=" + price + "]";*/
	}

	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Book(String author, String title, String genre, Date publish_date, String description,
			double price) {
		super();
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.publish_date = publish_date;
		this.description = description;
		this.price = price;
		
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
}
