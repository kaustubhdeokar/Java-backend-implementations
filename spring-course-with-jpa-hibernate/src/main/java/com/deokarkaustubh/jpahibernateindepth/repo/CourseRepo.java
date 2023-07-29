package com.deokarkaustubh.jpahibernateindepth.repo;

import com.deokarkaustubh.jpahibernateindepth.entity.Course;
import com.deokarkaustubh.jpahibernateindepth.entity.Review;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseRepo {

    @Autowired
    EntityManager entityManager;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void insertByIdName(long id, String courseName) {
        Course newCourse = new Course(id, courseName);
        entityManager.persist(newCourse);
    }

    public Course findByName(String name) {
        Course course = entityManager.find(Course.class, name);
        return course;
    }

    public void insert(Course course4) {
        entityManager.merge(course4);
    }

    public void update(int id, String name) {
        Course course = findById(id);
        course.setName(name);
        entityManager.merge(course);
    }

    public void save(Course course) {
        entityManager.persist(course);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {

        Course course = findById(courseId);
        logger.info("###{}", course);

        logger.info("####{}", course.getReviews());

        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
            //as there is nothing to be manipulated in the course table - we don't have to persist it.
        }


    }


//    public void save()

}
