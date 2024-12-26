package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Genre;
import org.example.web.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {
  private final GenreRepository genreRepository;

  public List<Genre> listGenres(String name) {
    if (name != null && !name.isEmpty()) {
      return genreRepository.findByName(name);
    }

    return genreRepository.findAll();
  }

  @Transactional
  public void saveGenre(Genre genre) throws IOException {
    log.info("Saving new Genre: {}", genre.getName());
    genreRepository.save(genre);
  }

  public void deleteGenre(Integer id) {
    genreRepository.deleteById(id);
  }

  public Genre getGenreById(Integer id) {
    return genreRepository.findById(id).orElse(null);
  }

  public List<Genre> findAll() {
    return genreRepository.findAll();
  }
}
