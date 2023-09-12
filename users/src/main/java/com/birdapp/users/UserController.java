package com.birdapp.users;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService service;

  @PostMapping
  public void save(
    @RequestBody User user
  ) {
    service.saveUser(user);
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> findAllUsers(
    @RequestParam(required=false) Integer limit,
    @RequestParam(required=false) Integer page
  ) {
    return ResponseEntity.ok(service.findAllUsers(limit, page));
  }
}
