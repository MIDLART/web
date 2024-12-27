package org.example.web.configurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.web.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  private final CustomUserDetailsService userDetailService;

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/registration", "/static/**").permitAll()
                .requestMatchers("/book/**", "/author/**")
                .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .anyRequest().authenticated()
        )
        .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
        )
        .logout((logout) -> logout.permitAll());

    return http.build();
  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService)
            .passwordEncoder(passwordEncoder());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

//  @Bean
//  public OncePerRequestFilter oncePerRequestFilter() {
//    return new OncePerRequestFilter() {
//      @Override
//      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//          String username = authentication.getName();
//          String roles = authentication.getAuthorities().toString();
//          System.out.println(username + ' ' + roles);
//        } else {
//          System.out.println("mregwrg");
//        }
//
//        try {
//          filterChain.doFilter(request, response);
//        } catch (IOException e) {
//          throw new RuntimeException(e);
//        } catch (ServletException e) {
//          throw new RuntimeException(e);
//        }
//      }
//    };
//  }
}
