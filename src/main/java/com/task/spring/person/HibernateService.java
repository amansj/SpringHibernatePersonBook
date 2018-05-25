package com.task.spring.person;

import java.util.List;

import com.task.spring.entity.Person;

public class HibernateService implements Service {
	DAO dao;
	public HibernateService() {
		// TODO Auto-generated constructor stub
	}
	HibernateService(DAO dao)
	{
		this.dao=dao;
	}
	@Override
	public String add(Person person) {
		// TODO Auto-generated method stub
		return dao.addPersonDetails(person);
	}

	@Override
	public String edit(Person person) {
		// TODO Auto-generated method stub
		return dao.editPersonDetails(person);
	}

	@Override
	public String delete(String personId) {
		// TODO Auto-generated method stub
		return dao.deletePersonDetails(personId);
	}

	@Override
	public Person list(String personId) {
		// TODO Auto-generated method stub
		return dao.list(personId);
	}

	@Override
	public List<Person> display() {
		// TODO Auto-generated method stub
		return dao.displayAll();
	}

}
