package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.web.models.Author;
import org.example.web.models.Genre;
import org.example.web.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BookTakingController {
  private final BookTakingService bookTakingService;
  private final BookCopyService bookCopyService;
  private final ReaderService readerService;
  private final BookService bookService;

//  @GetMapping("/search")
//  public String readerSearch(@RequestParam(name = "searchWord", required = false) String title, Model model) {
//    if (title == null || title.isEmpty()) {
//      model.addAttribute("rbooks", "не найдено");
//    }
//    model.addAttribute("rbooks", bookService.listBooks(title));
//    model.addAttribute("searchWord", title);
//    return "rbooks";
//  }

  @PostMapping("/reader/{id}/book_taking/save/{book_id}")
  public String saveBookTaking(@PathVariable Integer id, @PathVariable Integer bookId) throws IOException {
    try {
      bookTakingService.saveBookTaking(bookCopyService.getBookCopyById(bookId), readerService.getReaderById(id));
      return "redirect:/";
    } catch (Exception e) {
      log.error("Error creating bookCopy", e);
      return "redirect:/";
    }
  }

  @Transactional
  @PostMapping("/reader/{id}/book_taking/delete/{book_id}")
  public String deleteBookCopy(@PathVariable Integer id, @PathVariable Integer bookId) {
    try {
      bookTakingService.endBookTaking(bookCopyService.getBookCopyById(bookId), readerService.getReaderById(id));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "redirect:/reader/{id}";
  }
}