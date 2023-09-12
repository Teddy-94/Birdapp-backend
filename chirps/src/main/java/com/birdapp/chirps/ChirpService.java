package com.birdapp.chirps;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChirpService {

  private final ChirpRepository repository;

  public Chirp create(Chirp chirp) {
    if (chirp.getChirp_text() == null) {
      throw new BadRequestException("Chirp needs to contain some text.");
    }
    if (chirp.getUser_id() == null) {
      throw new BadRequestException("Chirp needs to have an author.");
    }
    if (chirp.getLikes() == null) {
      chirp.setLikes(0);
    }
    if (chirp.getRechirps() == null) {
      chirp.setRechirps(0);
    }
    try {
      return repository.create(chirp);
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Chirp> findAll(Integer limit, Integer page) {
    if (limit == null || limit < 1) {
      limit = 10;
    }
    if (page == null || page < 1) {
      page = 1;
    }
    try {
      return repository.findAll(limit, page);
    } catch (Exception e) {
      throw e;
    }
  }

  public Chirp findById(Integer chirp_id) {
    try {
      return repository.findById(chirp_id);
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Chirp> findAllByUser(Integer user_id, Integer limit, Integer page) {
    if (user_id == null ) {
      throw new BadRequestException("user cannot be null");
    }
    if (limit == null || limit < 1) {
      limit = 10;
    }
    if (page == null || page < 1) {
      page = 1;
    }
    try {
      return repository.findAllByUser(user_id, limit, page);
    } catch (Exception e) {
      throw e;
    }
  }

  public Chirp update(Chirp chirp) {
    // TODO
    return null;
  }

  public Integer delete(Chirp chirp) {
    // TODO
    return null;
  }
}
