package com.birdapp.chirps;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chirps")
@RequiredArgsConstructor
public class ChirpController {
  private final ChirpService chirpService;

  // TODO: the return types need to be improved to enable good error handling.
  @PostMapping("/")
  public ResponseEntity<Chirp> create(
      @RequestBody Chirp chirp) {
    try {
      Chirp res = chirpService.create(chirp);
      return ResponseEntity.ok(res);
    } catch (BadRequestException e) {
      return null;
      // return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Chirp>> findAll(
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer page) {
    List<Chirp> res = chirpService.findAll(limit, page);
    try {
      return ResponseEntity.ok(res);
    } catch (Exception e) {
      return null;
      // return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping("/chirp/{chirp_id}")
  public ResponseEntity<Chirp> findById(
      @PathVariable Integer chirp_id) {
    Chirp res = chirpService.findById(chirp_id);
    return ResponseEntity.ok(res);
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<List<Chirp>> findAllByUser(
      @PathVariable Integer user_id,
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer page) {
    List<Chirp> res = chirpService.findAllByUser(user_id, limit, page);
    try {
      return ResponseEntity.ok(res);
    } catch (Exception e) {
      return null;
      // return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}
