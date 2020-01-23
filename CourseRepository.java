package com.jdbcjpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jdbcjpa.model.Course;

@Repository
@Transactional
public class CourseRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}
	
	public Course save(Course course) {
		
		if(course.getId() == null)
			entityManager.persist(course);
		else
		    entityManager.merge(course);
		
		return course;
	}
	
	public void deleteById(long id) {
		entityManager.remove(findById(id));
	}
	
	public Course entityManagerPlay() {
		Course course = new Course("Mongo DB");
		entityManager.persist(course);
		entityManager.flush();
		
		course.setName("Mongo Without DB");
		
		
		// entityManager.clear() detach all object from current transaction 
		// entityManager.detach(course) detach only course object
		
		entityManager.refresh(course); // will persist database value
		return course;
	}
}
