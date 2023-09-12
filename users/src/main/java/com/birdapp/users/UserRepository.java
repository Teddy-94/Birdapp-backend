package com.birdapp.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final DataSource dataSource;

  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public User create(User user) {
    String sql = "insert into users ()"; // TODO:
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(2, user.getUsername());
      System.out.println("squelaing: " + preparedStatement);
      Integer res = preparedStatement.executeUpdate();
      if (res == 1) {
       // probably worked 
      }
      else {
        throw new Exception("something might have gone wrong");
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return user;
  }

  public List<User> findAll(Integer limit, Integer page) {
    Integer offset = (page - 1) * limit;
    String sql = "SELECT * FROM users LIMIT ? OFFSET ?";

    List<User> users = new ArrayList<>();
    try {
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, offset);

      System.out.println("squealing: " + preparedStatement);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Integer user_id = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String full_name = resultSet.getString("full_name");
        String bio = resultSet.getString("bio");

        User user = new User(user_id, username, email, full_name, bio);
        users.add(user);
      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }
}
