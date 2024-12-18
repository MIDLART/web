package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "edition")
  private Integer edition;

  @Column(name = "language")
  private String language;

  @Column(name = "authors")
  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private List<Author> authors = new ArrayList<>();
}