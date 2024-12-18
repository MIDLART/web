package org.example.web.repositories;

import org.example.web.models.Author;
import org.example.web.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
  List<Author> findBySurname(String surname); //TODO surname
}
