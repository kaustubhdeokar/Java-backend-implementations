package com.deokarkaustubh.jpahibernateindepth.repo;

import com.deokarkaustubh.jpahibernateindepth.entity.Course;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseRepo {

    @Autowired
    EntityManager entityManager;

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void insert(Course course4) {
        entityManager.merge(course4);
    }

    public void update(int id, String name) {
        Course course = findById(id);
        course.setName(name);
        entityManager.merge(course);
    }


//    public void save()

}
