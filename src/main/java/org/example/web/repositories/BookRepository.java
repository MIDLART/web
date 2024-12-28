package org.example.web.repositories;

import org.example.web.models.Author;
import org.example.web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List<Book> findByTitle(String title);

  boolean existsByTitleAndEditionAndLanguageAndAuthors(
          String title, Integer edition, String language, List<Integer> authorsId);
//  @Query(value = "SELECT * FROM vw_random_books", nativeQuery = true)
//  List<Book> findRandomBooks();
}
