package com.jdbcjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.jdbcjpa.model.Person;

@Repository
@Transactional
public class PersonRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Person findById(int id) {
		return entityManager.find(Person.class,id);
	}
	
	public List<Person> findAll() {
		return entityManager.createNamedQuery("find_all_person", Person.class).getResultList();
	}
	
	public void deleteById(int id) {
			entityManager.remove(findById(id));
	}
	
	public Person insert(Person person) {
		return 
			entityManager.merge(person);
							
	}
	
	public Person update(Person person) {
		return 
			entityManager.merge(person);
	}
}
