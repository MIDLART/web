package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Book;
import org.example.web.models.BookCopy;
import org.example.web.repositories.AuthorRepository;
import org.example.web.repositories.BookCopyRepository;
import org.example.web.repositories.BookRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCopyService {
  private final BookCopyRepository bookCopyRepository;
  private final BookRepository bookRepository;

  //
  private final JdbcTemplate jdbc;

  @Transactional
  public void addBookCopy(Integer bookId) throws IOException {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    BookCopy bookCopy = new BookCopy();
    bookCopy.setBook(book);

    bookCopyRepository.save(bookCopy);
  }

  @Transactional
  public void deleteBookCopy(Integer bookId) {
//    BookCopy bookCopy = bookCopyRepository.findFirstByBookId(bookId);
//    bookCopyRepository.delete(bookCopy);

//    List<BookCopy> bookCopies = bookCopyRepository.findByBookId(bookId);
//    log.info("Deleting book copies");
//    if (!bookCopies.isEmpty()) {
//      log.info("{}, id = {}", bookCopies.size(), bookCopies.get(0).getId());
//      Integer id = bookCopies.get(0).getId();
//      bookCopyRepository.deleteById(id);
//    }
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

    List<BookCopy> copies = book.getBookCopies();

    if (!copies.isEmpty()) {
      Integer id = copies.get(copies.size() - 1).getId();
      //copies.remove(copies.size() - 1);
      //bookCopyRepository.deleteById(id);

      log.info("{}, id = {}", copies.size(), copies.get(copies.size() - 1).getId());

      String sql = String.format("""
        DELETE FROM book_copy
          WHERE id = %s
        """, id);

      jdbc.update(sql);
    }
  }

  public BookCopy getBookCopyById(Integer id) {
    return bookCopyRepository.findById(id).orElse(null);
  }

  public Integer getBookCopyCountByBookId(Integer bookId) {
    //return Objects.requireNonNullElse(bookCopyRepository.getBookCopyCountByBookId(bookId), 0);
    return bookCopyRepository.findByBookId(bookId).size();
  }
}