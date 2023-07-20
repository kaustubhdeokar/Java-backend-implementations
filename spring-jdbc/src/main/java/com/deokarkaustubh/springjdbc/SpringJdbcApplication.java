package com.deokarkaustubh.springjdbc;

import com.deokarkaustubh.springjdbc.entity.Person;
import com.deokarkaustubh.springjdbc.jdbc.PersonJDBCDao;
import com.deokarkaustubh.springjdbc.jpa.PersonJpaRepository;
import com.deokarkaustubh.springjdbc.springdatajpa.SpringDataJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonJDBCDao dao;

    @Autowired
    PersonJpaRepository repo;

    @Autowired
    SpringDataJpaRepository daoRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }


    @Override
    public void run(String... args) {
        logger.info("{}", dao.findAll());
        logger.info("User id 10001 -> {}", dao.findById(11));
        logger.info("User id 10001 -> {}", dao.deleteById(11));
        logger.info("{}", dao.findAll());

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Person newPerson = new Person(41, "four", "pune", date);
        logger.info("{}", dao.insert(newPerson));
        dao.updatePerson(11, newPerson);
        logger.info("{}", dao.findAll());

        Person person = new Person("five", "pune", date);
        repo.insert(person);

        logger.info("{}", dao.findAll());

        repo.deleteById(1);


        Optional<Person> byId = daoRepo.findById(11);
        System.out.println(byId);

        daoRepo.save(new Person("name5", "pune", date));

        List<Person> all = daoRepo.findAll();
        System.out.println(all);

    }
}
