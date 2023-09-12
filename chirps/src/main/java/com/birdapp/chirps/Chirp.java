package com.birdapp.chirps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chirp {
  
  @Id
  private Integer chirp_id;
  private Integer user_id;
  private Integer likes;
  private Integer rechirps;
  private String chirp_text;
}
