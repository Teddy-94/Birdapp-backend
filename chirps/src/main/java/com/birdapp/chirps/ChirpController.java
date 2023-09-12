package com.birdapp.chirps;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chirps")
@RequiredArgsConstructor
public class ChirpController {
  
  private final ChirpService service;

  @PostMapping
  public void save(
    @RequestBody Chirp chirp
  ) {
    service.saveChirp(chirp);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Chirp>> findAllChirps() {
    return ResponseEntity.ok(service.findAllChirps());
  }
}
