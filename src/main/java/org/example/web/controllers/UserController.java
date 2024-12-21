package org.example.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.web.models.Library;
import org.example.web.models.User;
import org.example.web.services.LibraryService;
import org.example.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LibraryService libraryService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("libraries", libraryService.findAll());
    return "registration";
  }

  @PostMapping("/registration")
  public String createUser(User user,
                           @RequestParam(name = "library", required = false) Integer libraryId) throws IOException {
    try {
      userService.createUser(user);
      return "redirect:/login";
    } catch (Exception e) {
      log.error("Error registering user", e);
      return "redirect:/registration";
    }
  }

//  @PostMapping("/registration")
//  public String createUser(User user,
//                           @RequestParam(name = "library", required = false) Integer libraryId) throws IOException {
//    try {
//      if (libraryId != null) {
//        Library library = libraryService.getLibraryById(libraryId);
//        user.setLibrary(library);
//      }
//      userService.createUser(user);
//      return "redirect:/login";
//    } catch (Exception e) {
//      log.error("Error registering user", e);
//      return "redirect:/registration";
//    }
//  }

//  @PostMapping("/registration")
//  public String createUser(User user) {
//    userService.createUser(user);
//    return "redirect:/login";
//  }

  @GetMapping("/user/{user}")
  public String userInfo(@PathVariable("user") User user, Model model) {
    model.addAttribute("user", user);
    //model.addAttribute("books", user.getBooks());
    return "user-info";
  }
}