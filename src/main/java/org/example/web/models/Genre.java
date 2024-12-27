package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", unique = true)
  private String name;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(name = "book_genre",
          joinColumns = @JoinColumn(name = "genre_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id"))
  private List<Book> books = new ArrayList<>();
}
