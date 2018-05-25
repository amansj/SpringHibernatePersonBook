package com.task.spring.personbook;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.task.spring.entity.Book;
import com.task.spring.entity.Person;

@Transactional
public class HibernateDao implements DAO {
	private HibernateTransactionManager transactionManager;
	public HibernateDao(HibernateTransactionManager transactionManager) {
		super();
		this.transactionManager = transactionManager;
	}
	public HibernateDao() {}
	@Override
	public String assignBook(Person person, Book book) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			session.merge(person);
			//session.save(person);
			
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return "Book Assigned Successfully";
	}

	@Override
	public Person viewPerson(String bookId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		Person person=null;
		try {
			Query<Book> query=session.createQuery("select i from Book i "+"JOIN FETCH i.person "+"where i.id=:thebookid", Book.class);
			query.setParameter("thebookid", bookId);
			Book book=query.getSingleResult();
			if(book!=null)
			{
				person= book.getPerson();
			}
			else
			{
				System.out.println("No Such Record Found");
				person= null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			person= null;
		}
		return person;
	}

	@Override
	public List<Book> viewBook(String personId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			Query<Person> query=session.createQuery("select i from Person i "+"JOIN FETCH i.books "+"where i.personid=:thepersonid", Person.class);
			query.setParameter("thepersonid", personId);
			Person person=query.getSingleResult();
			//Person person=session.get(Person.class,personId);
			if(person!=null)
			{
				return person.getBooks();
			}
			else
			{
				System.out.println("No Such Record Found");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public String unassignBook(Person person, Book book) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			session.merge(person);
			session.merge(book);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return "Book unassigned Successfully";
	}

	@Override
	public String addPerson(Person person) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			session.save(person);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return "Person Successfully Added";
	}

	@Override
	public List<Person> displayAll() {
		// TODO Auto-generated method stub
		List<Person> persons=null;
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			Query<Person> query=session.createQuery("select i from Person i "+"JOIN FETCH i.books", Person.class);
			persons=query.getResultList();
			
		}catch(Exception e)
		{
			return null;
		}
		//List<Person> persons=
		return persons;
	}

	@Override
	public Book getBook(String bookId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		Book book=null;
		try {
			book=session.get(Book.class, bookId);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return book;
	}

	@Override
	public Person getPerson(String personId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		Person person=null;
		try {
			
			person=session.get(Person.class, personId);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return person;
	}

}
