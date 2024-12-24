package org.example.web.repositories;

import org.example.web.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
  List<Reader> findByPhoneNumber(String phoneNumber);
}
