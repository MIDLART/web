package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.Reader;
import org.example.web.repositories.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReaderService {
  private final ReaderRepository readerRepository;
  
  public List<Reader> listReaders(String phoneNumber) {
    if (phoneNumber != null) {
      return readerRepository.findByPhoneNumber(phoneNumber);
    }

    return readerRepository.findAll();
  }

  @Transactional
  public void saveReader(Reader reader) throws IOException {
    log.info("Saving new Reader: {}", reader.getPhoneNumber());
    readerRepository.save(reader);
  }

  public void deleteReader(Integer id) {
    readerRepository.deleteById(id);
  }

  public Reader getReaderById(Integer id) {
    return readerRepository.findById(id).orElse(null);
  }

  public List<Reader> findAll() {
    return readerRepository.findAll();
  }
}
