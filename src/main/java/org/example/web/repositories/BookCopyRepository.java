package org.example.web.repositories;

import org.example.web.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
//  BookCopy findFirstByBookId(Integer bookId);
  List<BookCopy> findByBookId(Integer bookId);

  //Integer getBookCopyCountByBookId(Integer bookId);
}
