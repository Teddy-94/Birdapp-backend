package com.birdapp.chirps;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChirpService {
  
  private final ChirpRepository repository;

  public void saveChirp(Chirp chirp) {
    repository.save(chirp);
  }

  public List<Chirp> findAllChirps() {
    return repository.findAll();
  }
}
