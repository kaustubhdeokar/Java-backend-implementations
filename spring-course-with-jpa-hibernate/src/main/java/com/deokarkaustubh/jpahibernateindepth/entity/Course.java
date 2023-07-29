package com.deokarkaustubh.jpahibernateindepth.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // to make this an entity - having a db table.
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "course")//by default fetch type is LAZY//to make it eager -> fetch = FetchType.EAGER
    private List<Review> reviews = new ArrayList<>();
    private String name;

    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;


    //jpa needs to have a no-args constructor.
    protected Course() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + '}';
    }
}
