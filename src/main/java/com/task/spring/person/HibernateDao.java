package com.task.spring.person;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.Transactional;

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
	public String addPersonDetails(Person person) {
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
	public String editPersonDetails(Person person) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		String msg="";
		Person p=null;
		try {
			p=session.get(Person.class, person.getPersonid());
			if(p!=null)
			{
				p.setAddress(person.getAddress());
				p.setPersonName(person.getPersonName());
				msg="Successfully Updated";
			}
			else
			{
				msg="No Such Record Found";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return "No Such Record Found";
		}
		return msg;
	}

	@Override
	public String deletePersonDetails(String personId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		String msg="";
		Person p=null;
		try {
			p=session.get(Person.class, personId);
			if(p!=null)
			{
				session.delete(p);
				msg="Successfully Deleted";
			}
			else
			{
				msg="No Such Record Found";
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "No Such Record Found";
		}
		return msg;
	}

	@Override
	public Person list(String personId) {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		Person p=null;
		try {
			p=session.get(Person.class, personId);
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> displayAll() {
		// TODO Auto-generated method stub
		Session session=transactionManager.getSessionFactory().getCurrentSession();
		List<Person> persons=null;
		try {
			persons=(List<Person>)session.createQuery("from Person").list();
			if(persons.isEmpty())
			{
				return null;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return persons;
	}

}
