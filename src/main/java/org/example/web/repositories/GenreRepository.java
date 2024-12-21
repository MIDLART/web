package org.example.web.repositories;

import org.example.web.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
  List<Genre> findByName(String name);
}
