package org.example.web.services;

import org.example.web.models.Image;
import org.example.web.models.Book;
import org.example.web.models.User;
import org.example.web.repositories.BookRepository;
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
public class BookService {
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  public List<Book> listBooks(String title) {
    if (title != null) return bookRepository.findByName(title);
    return bookRepository.findAll();
  }

  @Transactional
  public void saveBook(Principal principal, Book book, MultipartFile file1, MultipartFile file2) throws IOException {
    book.setUser(getUserByPrincipal(principal));

    Image image1;
    Image image2;
    if (file1.getSize() != 0) {
      image1 = toImageEntity(file1);
      image1.setPreviewImage(true);
      book.addImageToBook(image1);
    }
    if (file2.getSize() != 0) {
      image2 = toImageEntity(file2);
      book.addImageToBook(image2);
    }

    log.info("Saving new Book. Title: {}; Author email: {}", book.getName(), book.getUser().getEmail());
    Book bookFromDb = bookRepository.save(book);
    bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
    bookRepository.save(book);
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

  public void deleteBook(Integer id) {
    bookRepository.deleteById(id);
  }

  public Book getBookById(Integer id) {
    return bookRepository.findById(id).orElse(null);
  }
}