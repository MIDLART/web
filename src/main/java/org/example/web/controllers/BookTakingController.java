package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.web.models.*;
import org.example.web.services.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//  @GetMapping("/reader/{id}/book_taking/save/{bookId}")
//  public String saveBookTaking(@PathVariable Integer id, @PathVariable Integer bookId) throws IOException {
//    try {
//      bookTakingService.saveBookTaking(bookCopyService.getBookCopyById(bookId), readerService.getReaderById(id));
//      return "redirect:/reader/" + id;
//    } catch (Exception e) {
//      log.error("Error creating bookCopy", e);
//      return "redirect:/reader/" + id;
//    }
//  }

  @PostMapping("/reader/{id}/book_taking")
  public String take(@PathVariable Integer id, Book book, @AuthenticationPrincipal User user) throws IOException {
    if (book.getTitle() != null && !book.getTitle().isEmpty()) {
      List<Book> books = bookService.findBooks(book.getTitle(), book.getEdition(), book.getLanguage());

      if (!books.isEmpty()) {
        BookCopy copy = bookCopyService.getBookCopyByBookId(books.get(0).getId(), user.getLibrary().getId());

        if (copy != null) {
          try {
            bookTakingService.saveBookTaking(copy, readerService.getReaderById(id));
            return "redirect:/reader/" + id;
          } catch (Exception e) {
            log.error("Error creating bookCopy", e);
            return "redirect:/reader/" + id;
          }
        }
      }
    }

    return "redirect:/reader/" + id;
  }

  @Transactional
  @GetMapping("/reader/{id}/book_taking_delete/{bookId}")
  public String deleteBookCopy(@PathVariable Integer id, @PathVariable Integer bookId) {
    try {
      bookTakingService.endBookTaking(bookCopyService.getBookCopyById(bookId), readerService.getReaderById(id));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "redirect:/reader/{id}";
  }
}