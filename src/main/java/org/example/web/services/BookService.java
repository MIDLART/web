package org.example.web.services;

import org.example.web.controllers.BookController;
import org.example.web.models.*;
import org.example.web.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  private final BookCopyService bookCopyService;

  private final JdbcTemplate jdbc;

  @Transactional
  public List<Book> listBooks(String title) {
    if (title != null && !title.isEmpty()) {
      return bookRepository.findByTitle(title);
    }

    return bookRepository.findAll();
  }

  public List<Book> findBooks(String title, Integer edition, String language) {
    if (title == null || title.isEmpty()) {
      return null;
    }

    List<Book> books = bookRepository.findByTitle(title);

    if (edition != null) {
      books = books.stream()
              .filter(book -> book.getEdition().equals(edition))
              .toList();
    }

    if (language != null && !language.isEmpty()) {
      books = books.stream()
              .filter(book -> book.getLanguage().equals(language))
              .toList();
    }

    return books;
  }

  @Transactional
  public void saveBook(Book book) throws IOException {
//    boolean exists = bookRepository.existsByTitleAndEditionAndLanguageAndAuthors(
//            book.getTitle(), book.getEdition(), book.getLanguage(), book.getAuthors());

//    boolean exists = bookRepository.existsByTitleAndEditionAndLanguageAndAuthors(
//            book.getTitle(), book.getEdition(), book.getLanguage(),
//            book.getAuthors().stream().map(Author::getId).toList());
    boolean exists = false;

    log.info("Saving new Book. Title: {}", book.getTitle());

    if (!exists)
    {
      try {
        bookRepository.save(book);
      } catch (Exception e) {
        log.error("Error saving book", e);
        throw e;
      }
    }
  }

  public User getUserByPrincipal(Principal principal) {
    if (principal == null) {
      return new User();
    }

    return userRepository.findByName(principal.getName());
  }

  @Transactional
  public void deleteBook(Integer id) {
    //bookCopyService.deleteAllBookCopy(id, library);

    //bookRepository.deleteById(id);
//    Book book = bookRepository.findById(id).orElse(null);
//    if (book != null) {
//      bookRepository.deleteById(id);
//    }
    log.info("Deleting book with id: {}", id);

      String sql = String.format("""
        DELETE FROM books
          WHERE id = %s
        """, id);

      jdbc.update(sql);
  }

  public Book getBookById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }

  public List<Book> view() {
    String sql = String.format("""
        SELECT * FROM vw_random_books
        """);

    RowMapper<Book> rowMapper = (r, i) ->
            new Book(r.getInt("id"),
                    r.getString("title"),
                    null,
                    null,
                    null,
                    null,
                    null);

    return jdbc.query(sql, rowMapper);
  }
}