package com.task.spring.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.task.spring.entity.Book;

public class Main {
	private static Scanner sc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// load the spring configuration file
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		//Instantiate Service
		Service service=context.getBean("service",Service.class);
		Book book;
		boolean status=true;
		String choice;
		String bookId;
		String bookType;
		//BookType bType;
		sc=new Scanner(System.in);
		while(status)
		{
			System.out.println("**************************************");
			System.out.println("Book Record Operations:");
			System.out.println("1.Add Book to List of Books");
			System.out.println("2.Edit Book Details");
			System.out.println("3.Delete Book Details");
			System.out.println("4.List a particular Book");
			System.out.println("5.Display All Book");
			System.out.println("6.Exit");
			System.out.println("Enter Your Choice:-");
			//sc.nextLine();
			choice=sc.nextLine();
			switch(choice)
			{
				case "1":
					book=context.getBean("book",Book.class);
					bookType=addBookDetails(book);
			//		bType=new BookType(bookType);
					System.out.println(service.add(book,bookType));
					sc.nextLine();
					break;
				case "2":
					System.out.print("Enter BookId:");
					bookId=sc.nextLine();
					book=service.list(bookId);
					if(book!=null)
					{
						System.out.println(book);
						bookType=modifyDetailsInput(book);
						System.out.println(service.edit(book,bookType));
					}
					else
					{
						System.out.println("No Such Record Found");
					}
					break;
				case "3":
					System.out.print("Enter BookId:");
					bookId=sc.nextLine();
					System.out.println(service.delete(bookId));
					break;
				case "4":
					System.out.print("Enter BookId:");
					bookId=sc.nextLine();
					book=service.list(bookId);
					if(book!=null)
					{
						System.out.println(book);
						
					}
					else
					{
						System.out.println("No Such Record Found");
					}
					break;
				case "5":
					List<Book> bookList=service.display();
					if(bookList!=null)
					{
						for(Book b:bookList)
						{
							System.out.println(b);
						}
					}
					else
					{
						System.out.println("No Record Found");
					}
					break;
				case "6":
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
	private static String addBookDetails(Book b)
	{
		String bookType;
		System.out.println("******************************************************************************");
		System.out.print("Enter Book Id:");
		//sc.nextLine();
        b.setId(sc.nextLine());
        System.out.print("Enter Author Name:");
        b.setAuthor(sc.nextLine());
        System.out.print("Enter Title:");
        b.setTitle(sc.nextLine());
        System.out.print("Enter Genre:");
        b.setGenre(sc.nextLine());
        System.out.println("Enter BookType:");
        bookType=sc.nextLine();
        System.out.print("Enter Publish_Date(YYYY-MM-DD):");
        String date=sc.nextLine();
		Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			b.setPublish_date(d);
		} catch (ParseException e) {
		// TODO Auto-generated catch block
			System.out.println("Invalid Date Format");
		}
        System.out.print("Enter Description of Book:");
        b.setDescription(sc.nextLine());
        System.out.print("Enter Price:");
        b.setPrice(sc.nextDouble());
        return bookType;
	}
	private static String  modifyDetailsInput(Book book)
	{
		char ch='Y';
		String bookType=null;
		String choice;
		while(ch=='Y'||ch=='y')
		{
			System.out.println("1.Update Book Author");
			System.out.println("2.Update Book Title");
			System.out.println("3.Update Book Genre");
			System.out.println("4.Update Book Type");
			System.out.println("5.Update Book Price");
			System.out.println("6.Update Book Publish_date");
			System.out.println("7.Update Book Description");
			System.out.println("Enter Your Choice:-");
			choice=sc.nextLine();
			switch(choice)
			{
				case "1":
					System.out.print("Enter Updated Author Details:");
					//sc.nextLine();
					book.setAuthor(sc.nextLine());
					break;
				case "2":
					System.out.print("Enter Updated Title Details:");
					//sc.nextLine();
					book.setTitle(sc.nextLine());
					break;
				case "3":
					System.out.print("Enter Updated Genre Details:");
					//sc.nextLine();
					book.setGenre(sc.nextLine());
					break;
				case "4":
					System.out.print("Enter Updated Book Type:");
					bookType=sc.nextLine();
					//book.setBooktype(sc.nextLine());
					break;
				case "5":
					System.out.print("Enter Updated Price:");
					book.setPrice(sc.nextDouble());
					sc.nextLine();
					break;
				case "6":
					System.out.print("Enter Updated Publish_Date Details:");
					//sc.nextLine();
					String date=sc.nextLine();
					Date d;
					try {
						d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
						book.setPublish_date(d);
					} catch (ParseException e) {
					// TODO Auto-generated catch block
						System.out.println("Invalid Date Format");
					}
					break;
				case "7":
					System.out.print("Enter Updated Description:");
					//sc.nextLine();
					book.setDescription(sc.nextLine());
					break;
				default:
					System.out.println("Wrong Choice");
			}
			System.out.println("Are you want to modify more Details?Y/N:");
			ch=sc.nextLine().charAt(0);
		}
		return bookType;
	}
}
