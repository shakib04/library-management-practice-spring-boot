package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

     List<Book> findBookByAuthor(String author);

     List<Book> findBookByName(String name);

     @Query(value = "select model.copies from Book model where model.id = 1")
     int getBookCopies();
}
