package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;


@Log4j2
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

//  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinTable(name = "author_book",
//          joinColumns = @JoinColumn(name = "book_id"),
//          inverseJoinColumns = @JoinColumn(name = "author_id"))
//  private List<Author> authors = new ArrayList<>();

  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(name = "author_book",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "author_id"))
  private List<Author> authors = new ArrayList<>();

  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(name = "book_genre",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private List<Genre> genres = new ArrayList<>();

  @OnDelete(action = OnDeleteAction.CASCADE)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
  private List<BookCopy> bookCopies = new ArrayList<>();


  @PreRemove
  private void preRemove() {
    log.info("Removing book {}", id);
    for (Author author : new ArrayList<>(authors)) {
      authors.remove(author);
      author.getBooks().remove(this);
    }

    for (Genre genre : new ArrayList<>(genres)) {
      genres.remove(genre);
      genre.getBooks().remove(this);
    }
  }

  @Override
  public String toString() {
    return title;
  }

}