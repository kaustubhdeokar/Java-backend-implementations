package com.deokarkaustubh.springjdbc.springdatajpa;

import com.deokarkaustubh.springjdbc.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataJpaRepository extends JpaRepository<Person, Integer> {
}
