package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
          mappedBy = "book")
  private List<Image> images = new ArrayList<>();

  private Integer previewImageId;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn
  private User user;

  private LocalDateTime dateOfCreated;

  @PrePersist
  private void init() {
    dateOfCreated = LocalDateTime.now();
  }

  public void addImageToBook(Image image) {
    image.setBook(this);
    images.add(image);
  }
}