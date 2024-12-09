package org.example.web.controllers;

import org.example.web.models.Product;
import org.example.web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/")
  public String products(@RequestParam(name = "title", required = false) String title, Model model) {
    model.addAttribute("products", productService.listProducts(title));
    return "products";
  }

  @GetMapping("/product/{id}")
  public String productInfo(@PathVariable Integer id, Model model) {
    Product product = productService.getProductById(id);
    model.addAttribute("product", product);
    model.addAttribute("images", product.getImages());
    return "product-info";
  }

  @PostMapping("/product/create")
  public String createProduct(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2,
                               Product product) throws IOException {
    productService.saveProduct(product, file, file2);
    return "redirect:/";
  }

  @PostMapping("/product/delete/{id}")
  public String deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(id);
    return "redirect:/";
  }
}