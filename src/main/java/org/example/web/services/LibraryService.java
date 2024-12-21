package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Library;
import org.example.web.repositories.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryService {
  private final LibraryRepository libraryRepository;

  public List<Library> listLibraries(String address) {
    if (address != null) {
      return libraryRepository.findByAddress(address);
    }

    return libraryRepository.findAll();
  }

  @Transactional
  public void saveLibrary(Library library) throws IOException {
    log.info("Saving new Library: {}", library.getAddress());
    libraryRepository.save(library);
  }

  public void deleteLibrary(Integer id) {
    libraryRepository.deleteById(id);
  }

  public Library getLibraryById(Integer id) {
    return libraryRepository.findById(id).orElse(null);
  }

  public List<Library> findAll() {
    return libraryRepository.findAll();
  }
}
