package com.task.spring.person;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.task.spring.entity.Person;

public class Main {
	private static Scanner sc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// load the spring configuration file
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("/applicationContextPerson.xml");
		//Instantiate Service
		Service service=context.getBean("service",Service.class);
		Person person;
		boolean status=true;
		String choice;
		String personId;
		sc=new Scanner(System.in);
		while(status)
		{
			System.out.println("**************************************");
			System.out.println("Person Record Operations:");
			System.out.println("1.Add Person to List of Persons");
			System.out.println("2.Edit Person Details");
			System.out.println("3.Delete Person Details");
			System.out.println("4.List a particular Person");
			System.out.println("5.Display All Person");
			System.out.println("6.Exit");
			System.out.println("Enter Your Choice:-");
			//sc.nextLine();
			choice=sc.nextLine();
			switch(choice)
			{
				case "1":
					person=context.getBean("person",Person.class);
					addPersonDetails(person);
					System.out.println(service.add(person));
					break;
				case "2":
					System.out.print("Enter PersonId:");
					personId=sc.nextLine();
					person=service.list(personId);
					if(person!=null)
					{
						System.out.println(person);
						modifyDetailsInput(person);
						System.out.println(service.edit(person));
					}
					else
					{
						System.out.println("No Such Record Found");
					}
					break;
				case "3":
					System.out.print("Enter PersonId:");
					personId=sc.nextLine();
					System.out.println(service.delete(personId));
					break;
				case "4":
					System.out.print("Enter PersonId:");
					personId=sc.nextLine();
					person=service.list(personId);
					if(person!=null)
					{
						System.out.println(person);
						
					}
					else
					{
						System.out.println("No Such Record Found");
					}
					break;
				case "5":
					List<Person> personList=service.display();
					if(personList!=null)
					{
						for(Person b:personList)
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
	private static void  modifyDetailsInput(Person person)
	{
		char ch='Y';
		String choice;
		while(ch=='Y'||ch=='y')
		{
			System.out.println("1.Update Person Name");
			System.out.println("2.Update Person Address");
			System.out.println("Enter Your Choice:-");
			choice=sc.nextLine();
			switch(choice)
			{
				case "1":
					System.out.print("Enter Updated Person Name:");
					//sc.nextLine();
					person.setPersonName(sc.nextLine());
					break;
				case "2":
					System.out.print("Enter Updated Person Address:");
					//sc.nextLine();
					person.setAddress(sc.nextLine());
					break;
				default:
					System.out.println("Wrong Choice");
			}
			System.out.println("Are you want to modify more Details?Y/N:");
			ch=sc.nextLine().charAt(0);
		}
		
	}
}
