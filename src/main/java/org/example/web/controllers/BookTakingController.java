package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.web.models.Reader;
import org.example.web.services.BookCopyService;
import org.example.web.services.BookTakingService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BookTakingController {
  private final BookTakingService bookTakingService;
  private final BookCopyService bookCopyService;

  @PostMapping("/book_taking/save/{id}")
  public String saveBookTaking(@PathVariable Integer id, Reader reader) throws IOException {
    try {
      bookTakingService.saveBookTaking(bookCopyService.getBookCopyById(id), reader);
      return "redirect:/book/{id}";
    } catch (Exception e) {
      log.error("Error creating bookCopy", e);
      return "redirect:/book/{id}";
    }
  }

  @Transactional
  @PostMapping("/book_taking/delete/{id}")
  public String deleteBookCopy(@PathVariable Integer id, Reader reader) {
    try {
      bookTakingService.endBookTaking(bookCopyService.getBookCopyById(id), reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "redirect:/book/{id}";
  }
}