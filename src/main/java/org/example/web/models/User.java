package org.example.web.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.web.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

//  @Column(name = "email", unique = true)
//  private String email;

  @Column(name = "phoneNumber", unique = true)
  private String phoneNumber;

  @Column(name = "name")
  private String name;

  @Column(name = "active")
  private boolean active;

//  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinColumn(name = "image_id")
//  private Image avatar;

  @Column(name = "password", length = 1000)
  private String password;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles = new HashSet<>();

//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//  private List<Book> Books = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Library library;

  private LocalDateTime dateOfCreated;

  @PrePersist
  private void init() {
    dateOfCreated = LocalDateTime.now();
  }

  // security

  public boolean isAdmin() {
    return roles.contains(Role.ROLE_ADMIN);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}
