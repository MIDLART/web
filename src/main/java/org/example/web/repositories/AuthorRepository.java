package org.example.web.repositories;

import org.example.web.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
  List<Author> findBySurname(String surname);

  boolean existsBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);
}
