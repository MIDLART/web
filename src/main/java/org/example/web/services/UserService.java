package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.User;
import org.example.web.models.enums.Role;
import org.example.web.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public boolean createUser(User user) {
    String userEmail = user.getEmail();
    if (userRepository.findByEmail(userEmail) != null) return false;
    user.setActive(true);
    user.getRoles().add(Role.ROLE_USER);

    //user.setPassword(passwordEncoder.encode(user.getPassword()));
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    log.info("Saving new User with email: {}", userEmail);
    userRepository.save(user);
    return true;
  }
}
