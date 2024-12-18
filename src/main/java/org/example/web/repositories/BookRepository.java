package org.example.web.repositories;

import org.example.web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List<Book> findByTitle(String title);
}
