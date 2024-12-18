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

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/")
  public String books(@RequestParam(name = "searchWord", required = false) String title, Model model, Principal principal) {
    model.addAttribute("books", bookService.listBooks(title));
    model.addAttribute("authors", new ArrayList<>());
    model.addAttribute("user", bookService.getUserByPrincipal(principal)); //TODO
    model.addAttribute("searchWord", title);
    return "books";
  }

  @GetMapping("/book/{id}")
  public String bookInfo(@PathVariable Integer id, Model model) {
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    model.addAttribute("authors", book.getAuthors());
    return "book-info";
  }

  @PostMapping("/book/create")
  public String createBook(Book book) throws IOException {
    bookService.saveBook(book);
    return "redirect:/";
  }

  @PostMapping("/book/delete/{id}")
  public String deleteBook(@PathVariable Integer id) {
    bookService.deleteBook(id);
    return "redirect:/";
  }
}