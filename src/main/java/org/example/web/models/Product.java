package org.example.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
          mappedBy = "product")
  private List<Image> images = new ArrayList<>();
  private Integer previewImageId;
  private LocalDateTime dateOfCreated;

  @PrePersist
  private void init() {
    dateOfCreated = LocalDateTime.now();
  }


  public void addImageToProduct(Image image) {
    image.setProduct(this);
    images.add(image);
  }
}