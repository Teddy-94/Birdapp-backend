package com.birdapp.users;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public User create(User user) {
    if (true ) {
      // TODO check that user is ok;
    }
    try {
      return repository.create(user);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public List<User> findAll(Integer limit, Integer page) {
    if (limit == null || limit < 1) {
      limit = 10;
    }
    if (page == null || page < 1) {
      page = 1;
    }
    return repository.findAll(limit, page);
  }
}
