package com.task.spring.book;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.task.spring.entity.Book;
import com.task.spring.entity.BookType;
@Transactional
public class HibernateDao implements DAO {
	private HibernateTransactionManager transactionManager;
	public HibernateDao(HibernateTransactionManager transactionManager) {
		super();
		this.transactionManager = transactionManager;
	}
	public HibernateDao() {}
	@Override
	public String addBookDetails(Book book, String bookType) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			BookType bookT=new BookType(bookType);
			book.setBookType(bookT);
			session.merge(book);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}finally {
			
		}
		return "Successfully Book Added";
	}

	@Override
	public String editBookDetails(Book book, String bookType) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			Book bookprev=session.get(Book.class, book.getId());
			bookprev.setAuthor(book.getAuthor());
			bookprev.setDescription(book.getDescription());
			bookprev.setGenre(book.getGenre());
			bookprev.setPrice(book.getPrice());
			bookprev.setPublish_date(book.getPublish_date());
			bookprev.setTitle(book.getTitle());
			if(bookType!=null)
			{
				BookType bookT=new BookType(bookType);
				bookprev.setBookType(bookT);
				session.merge(bookprev);
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return "No Such Record Found";
		}finally {
			
		}
		return "Successfully Updated";
	}

	@Override
	public String deleteBookDetails(String BookId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		try {
			Book book=session.get(Book.class, BookId);
			session.delete(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "No Such Record Found";
		}
		return "Successfully Deleted";
	}

	@Override
	public Book listBookDetails(String BookId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		Book book=null;
		try {
			
			book=session.get(Book.class, BookId);
		
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return null;
		}finally {
			
		}
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> displayAll() {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		List<Book> bList=null;
		//System.out.println(session.isConnected());
		try {		
			bList=(List<Book>)session.createQuery("from Book").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return bList;
	}

}
