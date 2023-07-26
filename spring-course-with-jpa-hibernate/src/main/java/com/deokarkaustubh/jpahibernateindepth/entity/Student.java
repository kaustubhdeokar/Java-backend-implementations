package com.deokarkaustubh.jpahibernateindepth.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
