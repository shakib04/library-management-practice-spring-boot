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
    private int copies;

    @ManyToOne
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private SystemUser createdBy;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    public Book(){

    }

    public Book(Integer id, String name, String author, String publisher, int copies, SystemUser createdBy) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.copies = copies;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Book name(String name){
        this.name = name;
        return this;
    }
    public Book author(String author){
        this.author = author;
        return this;
    }

    public Book copies(int copies){
        this.copies = copies;
        return this;
    }

    public Book systemUser(SystemUser systemUser){
        this.createdBy = systemUser;
        return this;
    }

    public Book publisherDetails(Publisher publisher){
        this.publisher = publisher;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }
    public SystemUser getCreatedBy() {
        return this.createdBy;
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

    public void setCopies(int copies) {
        this.copies = copies;
    }
    public void setCreatedBy(SystemUser createdBy) {
        this.createdBy = createdBy;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisherDetails) {
        this.publisher = publisherDetails;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", copies=" + copies +
                ", createdBy=" + createdBy +
                ", publisherDetails=" + publisher +
                '}';
    }
}