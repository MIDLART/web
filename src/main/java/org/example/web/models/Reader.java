package org.example.web.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "readers")
@Data
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "patronymic")
  private String patronymic;

  @Column(name = "phoneNumber", unique = true)
  private String phoneNumber;
}
