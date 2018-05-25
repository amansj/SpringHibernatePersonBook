package com.task.spring.personbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.task.spring.entity.Book;
import com.task.spring.entity.Person;

public class Main {
	private static Scanner sc;
	private static Service service;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// load the spring configuration file
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/applicationContextPersonBook.xml");
		//Instantiate Service
		service=context.getBean("service",Service.class);
		Person person;
		boolean status=true;
		String choice;
		String bookId,personId;
		sc=new Scanner(System.in);
		while(status)
		{
			System.out.println("**************************************");
			System.out.println("Person Book Record Operations:");
			System.out.println("1.Assign Book to Person");
			System.out.println("2.View Person for a Particular Assigned Book");
			System.out.println("3.View Assigned Books for a Particular Person");
			System.out.println("4.Unassign Book from a Person");
			System.out.println("5.Display All Assigned Record");
			System.out.println("6.Complete Person's Profile");
			System.out.println("7.Exit");
			System.out.println("Enter Your Choice:-");
			//sc.nextLine();
			choice=sc.nextLine();
			switch(choice)
			{
				case "1":
					System.out.println("Enter Book Id:");
					bookId=sc.nextLine();
					System.out.println("Enter Person Id:");
					personId=sc.nextLine();
					System.out.println(service.assign(bookId, personId));
					break;
				case "2":
					System.out.print("Enter BookId:");
					bookId=sc.nextLine();
					person=service.viewPerson(bookId);
					if(person!=null)
					{
						System.out.println(person);
					}
					else
					{
						System.out.println("Book Not Assigned");
					}
					break;
				case "3":
					System.out.print("Enter Person Id:");
					personId=sc.nextLine();
					List<Book> books=service.viewBooks(personId);
					if(books!=null)
					{
						for(Book b:books)
						{
							System.out.println(b);
						}
					}
					else
					{
						System.out.println("No Book Assigned");
					}
					break;
				case "4":
					System.out.println("Enter Book Id:");
					bookId=sc.nextLine();
					System.out.println("Enter Person Id:");
					personId=sc.nextLine();
					System.out.println(service.unassign(personId,bookId));
					break;
				case "5":
					List<Person> personList=service.displayAll();
					if(personList!=null)
					{
						for(Person p:personList)
						{
							System.out.println(p);
							System.out.println(p.getBooks());
						}
					}
					else
					{
						System.out.println("No Record Found");
					}
					break;
				case "6":
					person=context.getBean("person",Person.class);
					addPersonDetails(person);
					List<Book> books2=booksAssigned();
					person.setBooks(books2);
					System.out.println(service.addPerson(person));
					break;
				case "7":
					status=false;
					break;
				default:
					System.out.println("Wrong Choice");
			}
		}
		sc.close();
		//close application configuration
		context.close();
	}
	private static void addPersonDetails(Person p)
	{
		System.out.println("******************************************************************************");
		System.out.print("Enter Person Id:");
		//sc.nextLine();
        p.setPersonid(sc.nextLine());
        System.out.print("Enter Person Name:");
        p.setPersonName(sc.nextLine());
        System.out.print("Enter Person Address:");
        p.setAddress(sc.nextLine()); 
	}
	private static List<Book> booksAssigned()
	{
		List<Book> books=null;
		char ch='Y';
		Book book;
		String bookId;
		while(ch=='Y'||ch=='y')
		{
			System.out.println("Enter Book Id:");
			bookId=sc.nextLine();
			book=service.list(bookId);
			if(book!=null)
			{
				if(books==null)
				{
					books=new ArrayList<>();
				}
				books.add(book);
			}
			else
			{
				System.out.println("No Such Book Found");
			}
			System.out.println("Are you want to assign more books?Y/N:");
			ch=sc.nextLine().charAt(0);
		}
		return books;
	}
}
