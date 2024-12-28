package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.BookCopy;
import org.example.web.models.BookTaking;
import org.example.web.models.Reader;
import org.example.web.repositories.BookTakingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookTakingService {
  private final BookTakingRepository bookTakingRepository;

  @Transactional
  public void saveBookTaking(BookCopy bookCopy, Reader reader) throws IOException {
    BookTaking bookTaking = new BookTaking();
    bookTaking.setBookCopy(bookCopy);
    bookTaking.setReader(reader);

    bookTakingRepository.save(bookTaking);
  }

  public void endBookTaking(BookCopy bookCopy, Reader reader) throws IOException {
    List<BookTaking> bookTaking = bookTakingRepository.findByBookCopy(bookCopy);
    if (!bookTaking.isEmpty()) {
      for (BookTaking bookTaking1 : bookTaking) {
        if (bookTaking1.getEndDate() == null)
        {
          bookTaking1.setEndDate(LocalDateTime.now());
          return;
        }
      }
    }
  }
}
