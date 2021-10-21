package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

     List<Book> findBookByAuthor(String author);
}
