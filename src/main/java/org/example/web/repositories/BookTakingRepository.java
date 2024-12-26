package org.example.web.repositories;

import org.example.web.models.BookCopy;
import org.example.web.models.BookTaking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTakingRepository extends JpaRepository<BookTaking, Integer> {
  BookTaking findByBookCopy(BookCopy bookCopy);
}
