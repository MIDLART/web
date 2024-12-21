package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import org.example.web.models.Genre;
import org.example.web.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping("/genre")
  public String genres(@RequestParam(name = "searchWord", required = false) String name, Model model) {
    model.addAttribute("genres", genreService.listGenres(name));
    model.addAttribute("searchWord", name);
    return "genres";
  }

  @GetMapping("/genre/{id}")
  public String genreInfo(@PathVariable Integer id, Model model) {
    Genre genre = genreService.getGenreById(id);
    model.addAttribute("genre", genre);
    model.addAttribute("books", genre.getBooks());
    return "genre-info";
  }

  @PostMapping("/genre/create")
  public String createGenre(Genre genre) throws IOException {
    genreService.saveGenre(genre);
    return "redirect:/genre";
  }

  @PostMapping("/genre/delete/{id}")
  public String deleteGenre(@PathVariable Integer id) {
    genreService.deleteGenre(id);
    return "redirect:/genre";
  }
}
