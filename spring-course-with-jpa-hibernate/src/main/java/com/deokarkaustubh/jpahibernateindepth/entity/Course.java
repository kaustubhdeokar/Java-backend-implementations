package com.deokarkaustubh.jpahibernateindepth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // to make this an entity - having a db table.
@Table(name = "course")
public class Course {
    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + '}';
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;



    //jpa needs to have a no-args constructor.
    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
