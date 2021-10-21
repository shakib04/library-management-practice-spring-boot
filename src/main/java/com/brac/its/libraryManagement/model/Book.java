package com.brac.its.libraryManagement.model;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    @NonNull
    private String publisher;
    @Column(nullable = false)
    @NonNull
    private int copies;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private SystemUser systemUser;

    public Book(){

    }

    public Book(Integer id, String name, String author, String publisher, int copies, SystemUser systemUser) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.copies = copies;
        this.systemUser = systemUser;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public int getCopies() {
        return copies;
    }
    public SystemUser getCreated_by() {
        return this.systemUser;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setCopies(int copies) {
        this.copies = copies;
    }
    public void setCreated_by(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", copies=" + copies +
                ", systemUser=" + systemUser +
                '}';
    }
}