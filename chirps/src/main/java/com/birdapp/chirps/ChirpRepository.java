package com.birdapp.chirps;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChirpRepository extends JpaRepository<Chirp, Integer> {
  
}
