package org.example.web.controllers;

import org.example.web.models.Book;
import org.example.web.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/")
  public String books(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
    model.addAttribute("books", bookService.listBooks(title));
    model.addAttribute("user", bookService.getUserByPrincipal(principal));
    return "books";
  }

  @GetMapping("/book/{id}")
  public String bookInfo(@PathVariable Integer id, Model model) {
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    model.addAttribute("images", book.getImages());
    return "book-info";
  }

  @PostMapping("/book/create")
  public String createBook(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2,
                               Book book, Principal principal) throws IOException {
    bookService.saveBook(principal, book, file, file2);
    return "redirect:/";
  }

  @PostMapping("/book/delete/{id}")
  public String deleteBook(@PathVariable Integer id) {
    bookService.deleteBook(id);
    return "redirect:/";
  }
}