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

  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> create(
    @RequestBody User user
  ) {
    try {
    User res = userService.create(user);
    return ResponseEntity.ok(res);
    } catch(Exception e) {
      return null;
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> findAll(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer page) {
    return ResponseEntity.ok(userService.findAll(limit, page));
  }
}
