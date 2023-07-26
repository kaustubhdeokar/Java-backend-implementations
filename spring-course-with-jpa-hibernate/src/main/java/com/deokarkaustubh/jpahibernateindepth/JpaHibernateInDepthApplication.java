package com.deokarkaustubh.jpahibernateindepth;

import com.deokarkaustubh.jpahibernateindepth.entity.Course;
import com.deokarkaustubh.jpahibernateindepth.repo.CourseRepo;
import com.deokarkaustubh.jpahibernateindepth.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    @Override
    public void run(String... args) throws Exception {

        try {
//            repo.deleteById(1002);

            Course byId = repo.findById(1001);
            logger.info("{}", byId);

            repo.insert(new Course("course4"));

            studentRepo.addStudentWithPassport();

        } catch (Exception e) {
            logger.error("course not found." + e.getMessage());
        }


    }
}
