package org.example.web.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "patronymic")
  @Nullable
  private String patronymic;

//  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinTable(name = "author_book",
//          joinColumns = @JoinColumn(name = "author_id"),
//          inverseJoinColumns = @JoinColumn(name = "book_id"))
//  private List<Book> books = new ArrayList<>();

  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(name = "author_book",
          joinColumns = @JoinColumn(name = "author_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id"))
  private List<Book> books = new ArrayList<>();

  @Override
  public String toString() {
    return surname + " " + name + " " + patronymic;
  }

}
