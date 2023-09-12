package com.birdapp.chirps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import jakarta.ws.rs.NotFoundException;

@Repository
public class ChirpRepository {

  private final DataSource dataSource;

  public ChirpRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Chirp create(Chirp chirp) {
    String sql = "INSERT INTO chirps (user_id, chirp_text) VALUES(?, ?)";
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, chirp.getUser_id());
      preparedStatement.setString(2, chirp.getChirp_text());
      System.out.println("squelaing: " + preparedStatement);
      Integer res = preparedStatement.executeUpdate();
      if (res == 1) {
       // probably worked 
      }
      else {
        throw new Exception("something might have gone wrong");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return chirp;
  }

  public List<Chirp> findAll(Integer limit, Integer page) {
    Integer offset = (page - 1) * limit;
    String sql = "SELECT * FROM chirps LIMIT ? OFFSET ?";

    List<Chirp> chirps = new ArrayList<>();
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, offset);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Integer chirps_id = resultSet.getInt("chirp_id");
        Integer userid = resultSet.getInt("user_id");
        Integer likes = resultSet.getInt("likes");
        Integer rechirps = resultSet.getInt("rechirps");
        String chirp_text = resultSet.getString("chirp_text");

        Chirp chirp = new Chirp(chirps_id, userid, likes, rechirps, chirp_text);
        chirps.add(chirp);
      }
      connection.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return chirps;
  }

  public Chirp findById(Integer chirp_id) {
    String sql = "SELECT * FROM chirps WHERE chirp_id = ?";
    Chirp chirp = null;
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, chirp_id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Integer chirps_id = resultSet.getInt("chirp_id");
        Integer userid = resultSet.getInt("user_id");
        Integer likes = resultSet.getInt("likes");
        Integer rechirps = resultSet.getInt("rechirps");
        String chirp_text = resultSet.getString("chirp_text");

        chirp = new Chirp(chirps_id, userid, likes, rechirps, chirp_text);
      } else {
        throw new NotFoundException("Could not find chirp of id: " + chirp_id);
      }
      connection.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return chirp;
  }

  public List<Chirp> findAllByUser(Integer user_id, Integer limit, Integer page) {
    Integer offset = (page - 1) * limit;
    String sql = "SELECT * FROM chirps WHERE user_id = ? LIMIT ? OFFSET ?";
    List<Chirp> chirps = new ArrayList<>();
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, user_id);
      preparedStatement.setInt(2, limit);
      preparedStatement.setInt(3, offset);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Integer chirps_id = resultSet.getInt("chirp_id");
        Integer userid = resultSet.getInt("user_id");
        Integer likes = resultSet.getInt("likes");
        Integer rechirps = resultSet.getInt("rechirps");
        String chirp_text = resultSet.getString("chirp_text");

        Chirp chirp = new Chirp(chirps_id, userid, likes, rechirps, chirp_text);
        chirps.add(chirp);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return chirps;
  }

  public void update() {
    return;
  }

  public void delete() {
    return;
  }
}
