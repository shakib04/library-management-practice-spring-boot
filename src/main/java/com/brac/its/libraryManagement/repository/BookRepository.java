package com.brac.its.libraryManagement.repository;

import com.brac.its.libraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookRepository extends JpaRepository<Book, Integer> {


     CompletableFuture<Book> findBookById(int id);

     List<Book> findBookByAuthor(String author);

     List<Book> findBookByName(String name);

     @Query(value = "select model.copies from Book model where model.id = 1")
     int getBookCopies();
}
