package org.example.web.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_taking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTaking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private BookCopy bookCopy;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private Reader reader;

  @Column(name = "startDate")
  private LocalDateTime startDate;

  @Column(name = "endDate")
  private LocalDateTime endDate;

  @PrePersist
  private void init() {
    startDate = LocalDateTime.now();
    endDate = null;
  }
}

