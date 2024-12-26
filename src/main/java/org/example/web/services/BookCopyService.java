package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Book;
import org.example.web.models.BookCopy;
import org.example.web.models.Library;
import org.example.web.repositories.BookCopyRepository;
import org.example.web.repositories.BookRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
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
  public void addBookCopy(Integer bookId, Library library) throws IOException {
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    BookCopy bookCopy = new BookCopy();
    bookCopy.setBook(book);
    bookCopy.setLibrary(library);

    bookCopyRepository.save(bookCopy);
  }

  @Transactional
  public void deleteBookCopy(Integer bookId, Library library) {
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
    List<BookCopy> curBookCopies = new ArrayList<>();

    log.info("Lib id {}", library.getId());

    for (BookCopy bookCopy : copies) {
      //log.info("* {}", );
      if (bookCopy.getLibrary().getId().equals(library.getId())) {
        curBookCopies.add(bookCopy);
      }
    }

    if (!curBookCopies.isEmpty()) {
      Integer id = curBookCopies.get(curBookCopies.size() - 1).getId();
      //curBookCopies.remove(curBookCopies.size() - 1);
      //bookCopyRepository.deleteById(id);

      log.info("Delete book copy. Copies: {}, id = {}", curBookCopies.size(), curBookCopies.get(curBookCopies.size() - 1).getId());

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

  public Integer getBookCopyCountByBookId(Integer bookId, Integer libraryId) {
    List<BookCopy> bookCopies = bookCopyRepository.findByBookId(bookId);
    int count = 0;

    for (BookCopy bookCopy : bookCopies) {
      if (bookCopy.getLibrary().getId().equals(libraryId)) {
        count++;
      }
    }

    return count;
  }

  public Book getBookByCopyId(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }
}