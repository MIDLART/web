package org.example.web.services;

import org.example.web.models.Book;
import org.example.web.models.User;
import org.example.web.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.repositories.UserRepository;
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
    //book.setUser(getUserByPrincipal(principal)); TODO Author

    log.info("Saving new Book. Title: {}", book.getTitle());
    //bookRepository.save(book);

    try {
      bookRepository.save(book);
    } catch (Exception e) {
      log.error("Error saving book", e);
      throw e;
    }
  }

  public User getUserByPrincipal(Principal principal) {
    if (principal == null) {
      return new User();
    }

    return userRepository.findByName(principal.getName());
  }

  public void deleteBook(Integer id) {
    //bookRepository.deleteById(id);
    Book book = bookRepository.findById(id).orElse(null);
    if (book != null) {
      bookRepository.deleteById(id);
    }
  }

  public Book getBookById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }
}