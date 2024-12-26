package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_copy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

//  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private Book book;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private Library library;

  @OnDelete(action = OnDeleteAction.CASCADE)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bookCopy")
  private List<BookTaking> bookTakings = new ArrayList<>();
}
