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

  public List<Book> listBooks(String title) {
    if (title != null) {
      return bookRepository.findByTitle(title);
    }

    return bookRepository.findAll();
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

    return userRepository.findByEmail(principal.getName());
  }

  public void deleteBook(Integer id) {
    bookRepository.deleteById(id);
  }

  public Book getBookById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }
}