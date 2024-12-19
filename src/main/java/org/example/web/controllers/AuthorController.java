package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import org.example.web.models.Author;
import org.example.web.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping("/author")
  public String authors(@RequestParam(name = "searchWord", required = false) String surname, Model model) {
    model.addAttribute("authors", authorService.listAuthors(surname));
    model.addAttribute("searchWord", surname);
    return "authors";
  }

  @GetMapping("/author/{id}")
  public String authorInfo(@PathVariable Integer id, Model model) {
    Author author = authorService.getAuthorById(id);
    model.addAttribute("author", author);
    //model.addAttribute("books", author.getBooks());
    return "author-info";
  }

  @PostMapping("/author/create")
  public String createAuthor(Author author) throws IOException {
    authorService.saveAuthor(author);
    return "redirect:/author";
  }

  @PostMapping("/author/delete/{id}")
  public String deleteAuthor(@PathVariable Integer id) {
    authorService.deleteAuthor(id);
    return "redirect:/author";
  }
}
