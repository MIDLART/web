package org.example.web.repositories;

import org.example.web.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
  List<Library> findByAddress(String address);
}
