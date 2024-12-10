package org.example.web.services;

import org.example.web.models.Image;
import org.example.web.models.Product;
import org.example.web.models.User;
import org.example.web.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  public List<Product> listProducts(String title) {
    if (title != null) return productRepository.findByName(title);
    return productRepository.findAll();
  }

  @Transactional
  public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2) throws IOException {
    product.setUser(getUserByPrincipal(principal));

    Image image1;
    Image image2;
    if (file1.getSize() != 0) {
      image1 = toImageEntity(file1);
      image1.setPreviewImage(true);
      product.addImageToProduct(image1);
    }
    if (file2.getSize() != 0) {
      image2 = toImageEntity(file2);
      product.addImageToProduct(image2);
    }

    log.info("Saving new Product. Title: {}; Author email: {}", product.getName(), product.getUser().getEmail());
    Product productFromDb = productRepository.save(product);
    productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
    productRepository.save(product);
  }

  public User getUserByPrincipal(Principal principal) {
    if (principal == null) return new User();
    return userRepository.findByEmail(principal.getName());
  }

  private Image toImageEntity(MultipartFile file) throws IOException {
    Image image = new Image();
    image.setName(file.getName());
    image.setOriginalFileName(file.getOriginalFilename());
    image.setContentType(file.getContentType());
    image.setSize(file.getSize());
    image.setBytes(file.getBytes());
    return image;
  }

  public void deleteProduct(Integer id) {
    productRepository.deleteById(id);
  }

  public Product getProductById(Integer id) {
    return productRepository.findById(id).orElse(null);
  }
}