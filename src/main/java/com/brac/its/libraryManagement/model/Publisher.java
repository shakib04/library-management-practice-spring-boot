package com.brac.its.libraryManagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String govtLicence;

    @OneToMany(mappedBy = "publisherDetails")
    private Set<Book> book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGovtLicence() {
        return govtLicence;
    }

    public void setGovtLicence(String govtLicence) {
        this.govtLicence = govtLicence;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", govtLicence='" + govtLicence + '\'' +
                ", book=" + book +
                '}';
    }
}
