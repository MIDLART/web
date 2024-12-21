package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import org.example.web.models.Library;
import org.example.web.services.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LibraryController {
  private final LibraryService libraryService;

  @GetMapping("/library")
  public String librarys(@RequestParam(name = "searchWord", required = false) String address, Model model) {
    model.addAttribute("libraries", libraryService.listLibraries(address));
    model.addAttribute("searchWord", address);
    return "libraries";
  }

//  @GetMapping("/library/{id}")
//  public String libraryInfo(@PathVariable Integer id, Model model) {
//    Library library = libraryService.getLibraryById(id);
//    model.addAttribute("library", library);
//    //model.addAttribute("books", library.getBooks());
//    return "library-info";
//  }

  @PostMapping("/library/create")
  public String createLibrary(Library library) throws IOException {
    libraryService.saveLibrary(library);
    return "redirect:/library";
  }

  @PostMapping("/library/delete/{id}")
  public String deleteLibrary(@PathVariable Integer id) {
    libraryService.deleteLibrary(id);
    return "redirect:/library";
  }
}
