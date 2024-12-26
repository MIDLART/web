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

@Service
@Slf4j
@RequiredArgsConstructor
public class BookTakingService {
  private final BookTakingRepository bookTakingRepository;

//  public List<BookTaking> listBookTakings(String surname) {
//    if (surname != null) {
//      return bookTakingRepository.findBySurname(surname);
//    }
//
//    return bookTakingRepository.findAll();
//  }

  @Transactional
  public void saveBookTaking(BookCopy bookCopy, Reader reader) throws IOException {
    BookTaking bookTaking = new BookTaking();
    bookTaking.setBookCopy(bookCopy);
    bookTaking.setReader(reader);

    bookTakingRepository.save(bookTaking);
  }

  public void endBookTaking(BookCopy bookCopy, Reader reader) throws IOException {
    BookTaking bookTaking = bookTakingRepository.findByBookCopy(bookCopy);
    if (bookTaking != null) {
      bookTaking.setEndDate(LocalDateTime.now());
    }
  }

//  public void deleteBookTaking(Integer id) {
//    bookTakingRepository.deleteById(id);
//  }
//
//  public BookTaking getBookTakingById(Integer id) {
//    return bookTakingRepository.findById(id).orElse(null);
//  }
//
//  public List<BookTaking> findAll() {
//    return bookTakingRepository.findAll();
//  }
}
