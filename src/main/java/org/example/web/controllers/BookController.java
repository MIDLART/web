package org.example.web.controllers;

import lombok.extern.log4j.Log4j2;
import org.example.web.models.*;
import org.example.web.services.AuthorService;
import org.example.web.services.BookCopyService;
import org.example.web.services.BookService;
import lombok.RequiredArgsConstructor;
import org.example.web.services.GenreService;
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
public class BookController {
  private final BookService bookService;

  private final AuthorService authorService;
  private final GenreService genreService;
  private final BookCopyService bookCopyService;

  @GetMapping("/")
  public String books(@RequestParam(name = "searchWord", required = false) String title, Model model, Principal principal) {
    model.addAttribute("books", bookService.listBooks(title));

    // Получить список авторов из базы данных
    List<Author> authors = authorService.findAll();

    // Передать список авторов в шаблон
    model.addAttribute("authors", authors);

    List<Genre> genres = genreService.findAll();
    model.addAttribute("genres", genres);

    model.addAttribute("user", bookService.getUserByPrincipal(principal));
    model.addAttribute("searchWord", title);
    return "books";
  }

  @GetMapping("/book/{id}")
  public String bookInfo(@PathVariable Integer id, Model model, @AuthenticationPrincipal User user) {
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    model.addAttribute("authors", book.getAuthors());
    model.addAttribute("genres", book.getGenres());

    model.addAttribute("copy_count", bookCopyService.getBookCopyCountByBookId(id, user.getLibrary().getId()));
    //model.addAttribute("genres", book.getGenres());
    return "book-info";
  }

  @PostMapping("/book/create")
  public String createBook(Book book,
                           @RequestParam(name = "authors", required = false) List<Integer> authorIds,
                           @RequestParam(name = "genres", required = false) List<Integer> genreIds) throws IOException {
    try {
      bookService.saveBook(book);
      return "redirect:/";
    } catch (Exception e) {
      log.error("Error creating book", e);
      return "redirect:/";
    }
  }

  @PostMapping("/book/delete/{id}")
  public String deleteBook(@PathVariable Integer id) {
    bookService.deleteBook(id);
    return "redirect:/";
  }
}