package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import org.example.web.models.Reader;
import org.example.web.services.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ReaderController {
  private final ReaderService readerService;

//  @GetMapping("/reader")
//  public String readers(@RequestParam(name = "searchWord", required = false) String phoneNumber, Model model) {
//    model.addAttribute("readers", readerService.listReaders(phoneNumber));
//    model.addAttribute("searchWord", phoneNumber);
//    return "readers";
//  }

  @GetMapping("/reader/{id}")
  public String readerInfo(@PathVariable Integer id, Model model) {
    Reader reader = readerService.getReaderById(id);
    model.addAttribute("reader", reader);
    //model.addAttribute("books", reader.getBooks());
    return "reader-info";
  }

  @PostMapping("/reader/create")
  public String createReader(Reader reader) throws IOException {
    readerService.saveReader(reader);
    return "redirect:/reader";
  }

  @PostMapping("/reader/delete/{id}")
  public String deleteReader(@PathVariable Integer id) {
    readerService.deleteReader(id);
    return "redirect:/reader";
  }
}
