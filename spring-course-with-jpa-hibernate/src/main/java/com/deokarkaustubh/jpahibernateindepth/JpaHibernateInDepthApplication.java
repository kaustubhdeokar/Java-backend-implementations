package com.deokarkaustubh.jpahibernateindepth;

import com.deokarkaustubh.jpahibernateindepth.entity.Course;
import com.deokarkaustubh.jpahibernateindepth.entity.Review;
import com.deokarkaustubh.jpahibernateindepth.repo.CourseRepo;
import com.deokarkaustubh.jpahibernateindepth.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaHibernateInDepthApplication implements CommandLineRunner {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

        SpringApplication.run(JpaHibernateInDepthApplication.class, args);

    }

    @Autowired
    CourseRepo repo;
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    @Override
    public void run(String... args) {

        try {

            /*
            createCourse();
            findCourse();
            addReviews();
            */

        } catch (Exception e) {
            logger.error("course not found." + e.getMessage());
        }


    }

    private void createCourse() {
        Course course = new Course("rest service in spring");
        courseRepo.save(course);
    }

    private void findCourse() {
        Course byId = repo.findById(10001L);
        logger.info("### -> {}", byId);
    }

    private void addReviews() {
        Review review1 = new Review("3", "good");
        Review review2 = new Review("3", "nice");
        courseRepo.addReviewsForCourse(10002L, List.of(review1, review2));
    }
}
