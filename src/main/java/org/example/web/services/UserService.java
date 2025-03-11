package org.example.web.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.web.models.User;
import org.example.web.models.enums.Role;
import org.example.web.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public boolean createUser(User user) {
    String userName = user.getName();
    if (userRepository.findByName(userName) != null) return false;
    user.setActive(true);
    user.getRoles().add(Role.ROLE_USER);
    //user.getRoles().add(Role.ROLE_ADMIN);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
//    String encodedPassword = passwordEncoder.encode(user.getPassword());
//    user.setPassword(encodedPassword);

    log.info("Saving new User: {}", userName);
    userRepository.save(user);
    return true;
  }

  public List<User> list() {
    return userRepository.findAll();
  }

  public void banUser(Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      if (user.isActive()) {
        user.setActive(false);
        log.info("Ban user with id = {}; name: {}", user.getId(), user.getName());
      } else {
        user.setActive(true);
        log.info("Unban user with id = {}; name: {}", user.getId(), user.getName());
      }
    }
    userRepository.save(user);
  }

  public void changeUserRoles(User user, Map<String, String> form) {
    Set<String> roles = Arrays.stream(Role.values())
            .map(Role::name)
            .collect(Collectors.toSet());
    user.getRoles().clear();
    for (String key : form.keySet()) {
      if (roles.contains(key)) {
        user.getRoles().add(Role.valueOf(key));
      }
    }
    userRepository.save(user);
  }
}
