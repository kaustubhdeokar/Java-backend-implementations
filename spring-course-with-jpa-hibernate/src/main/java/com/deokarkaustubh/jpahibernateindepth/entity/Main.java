package com.deokarkaustubh.jpahibernateindepth.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    private SessionFactory sessionFactory;

    private void instantiateHibernate() {
        // configures settings from hibernate.cfg.xml
        initSessionFactory();
        performSessionOperations();
        performUpdateOperations();
//        performDeleteOperations();
        closeSessionFactory();
    }

    private void initSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    private void performUpdateOperations() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Student> studentList = session.createQuery("select s from Student s", Student.class).list();
        List<Course> courseList = session.createQuery("select c from Course c", Course.class).list();

        for (Student s : studentList) {
            for (Course c : courseList) {
                s.getCourseList().add(c);
            }
            session.merge(s);
        }

        session.getTransaction().commit();
        session.close();

    }

    private void performDeleteOperations() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createQuery("delete from Student s").executeUpdate();
        session.createQuery("delete from Course c").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private void performSessionOperations() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Passport passport1 = new Passport();
        passport1.setNumber("p101");
        Passport passport2 = new Passport();
        passport2.setNumber("p101");

        Student student1 = new Student();
        student1.setPassport(passport1);
        student1.setName("stu1");
        Student student2 = new Student();
        student2.setPassport(passport2);
        student2.setName("stu2");


        Course course1 = new Course();
        course1.setName("c1");
        Course course2 = new Course();
        course2.setName("c2");

//        student1.getCourseList().add(course1);
//        student2.getCourseList().add(course2);

        session.persist(student1);
        session.persist(student2);
        session.persist(course1);
        session.persist(course2);
        session.persist(passport1);
        session.persist(passport2);

        session.getTransaction().commit();
        session.close();
    }

    private void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }


    public static void main(String[] args) {

        Main main = new Main();
        main.instantiateHibernate();


    }
}
