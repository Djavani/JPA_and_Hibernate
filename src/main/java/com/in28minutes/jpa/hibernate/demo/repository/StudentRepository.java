package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if(student.getId()== null) {
			em.persist(student);			
		}else {
			em.merge(student);
		}
		return student;
	}
	
	public void deletedById(Long id) {
		Student student= findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Mike Murdock");
		student.setPassport(passport);
		em.persist(student);
		
	}
	
	public void someOperationToUndestandPersistenceContext() {
		// Database Operation 1 - Retrieve Student
		Student student = em.find(Student.class, 20001L);
		
		// Database Operation 2 - Retrieve Passport
		Passport passport = student.getPassport();
		
		// Database Operation 3 - update Passport
		passport.setNumber("E123457");
		
		// Database Operation 4 - update Student
		student.setName("Ranga - UPDATED");
	}

}
