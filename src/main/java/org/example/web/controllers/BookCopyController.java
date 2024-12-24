package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.web.services.BookCopyService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BookCopyController {
  private final BookCopyService bookCopyService;
  
  @PostMapping("/book_сopy/add/{id}")
  public String addBookCopy(@PathVariable Integer id) throws IOException {
    try {
      bookCopyService.addBookCopy(id);
      return "redirect:/book/{id}";
    } catch (Exception e) {
      log.error("Error creating bookCopy", e);
      return "redirect:/book/{id}";
    }
  }

  @Transactional
  @PostMapping("/book_сopy/delete/{id}")
  public String deleteBookCopy(@PathVariable Integer id) {
    bookCopyService.deleteBookCopy(id);
    return "redirect:/book/{id}";
  }

//  @GetMapping("/book_сopy/count/{id}")
//  public String getBookCopyCount(@PathVariable Integer id) {
//    bookCopyService.getBookCopyCountByBookId(id);
//    return "redirect:/book/{id}";
//  }
}