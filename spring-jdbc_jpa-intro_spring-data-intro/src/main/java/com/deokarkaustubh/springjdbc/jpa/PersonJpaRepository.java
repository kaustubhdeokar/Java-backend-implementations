package com.deokarkaustubh.springjdbc.jpa;

import com.deokarkaustubh.springjdbc.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Table(name = "person")
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public void insert(Person person2) {
        entityManager.merge(person2);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person byId = findById(id);
        entityManager.remove(byId);
    }


}
