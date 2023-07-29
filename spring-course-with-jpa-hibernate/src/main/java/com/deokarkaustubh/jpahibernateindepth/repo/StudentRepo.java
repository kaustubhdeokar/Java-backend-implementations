package com.deokarkaustubh.jpahibernateindepth.repo;

import com.deokarkaustubh.jpahibernateindepth.entity.Course;
import com.deokarkaustubh.jpahibernateindepth.entity.Passport;
import com.deokarkaustubh.jpahibernateindepth.entity.Student;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentRepo {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    public void deleteById(long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    public void insert(Student student) {
        entityManager.merge(student);
    }

    public void update(int id, String name) {
        Student student = findById(id);
        student.setName(name);
        entityManager.merge(student);
    }

    public void addStudentWithPassport() {

        Passport passport = new Passport("Z1234");
        entityManager.persist(passport);

        Student student = new Student("student5");
        student.setPassport(passport);
        entityManager.persist(student);

    }

    public void addCourseForStudent(Student student, Course course) {

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);

    }
}
