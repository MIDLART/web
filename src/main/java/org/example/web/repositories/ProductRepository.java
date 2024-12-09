package org.example.web.repositories;

import org.example.web.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findByName(String name);
}
