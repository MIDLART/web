package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Author;
import org.example.web.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;
  
  public List<Author> listAuthors(String surname) {
    if (surname != null) {
      return authorRepository.findBySurname(surname);
    }

    return authorRepository.findAll();
  }

  @Transactional
  public void saveAuthor(Author author) throws IOException {
    log.info("Saving new Author: {} {} {}", author.getSurname(), author.getName(), author.getPatronymic());
    authorRepository.save(author);
  }

  public void deleteAuthor(Integer id) {
    authorRepository.deleteById(id);
  }

  public Author getAuthorById(Integer id) {
    return authorRepository.findById(id).orElse(null);
  }

  public List<Author> findAll() {
    return authorRepository.findAll();
  }
}
