package com.example.carsharing.service;

import com.example.carsharing.db.DBConnection;
import com.example.carsharing.domain.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerService {

  private int currentIndex;

  public int getCurrentIndex() {
    return currentIndex;
  }

  public void setCurrentIndex(int currentIndex) {
    this.currentIndex = currentIndex;
  }

  public void saveToDB(String firstName, String lastName, String phone) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "INSERT INTO owners (first_name, last_name, phone) VALUES (?, ?, ?)")) {
      pS.setString(1, firstName);
      pS.setString(2, lastName);
      pS.setString(3, phone);
      pS.executeUpdate();
    }
  }

  public Optional<Owner> findById(int id) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "SELECT * FROM owners WHERE id = " + id);
        ResultSet resultSet = pS.executeQuery()) {
      if (resultSet.next()) {
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phone = resultSet.getString(4);
        return Optional.of(new Owner(id, firstName, lastName, phone));
      }
      return Optional.empty();
    }
  }

  public List<Owner> findAll() throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement("SELECT * FROM owners");
        ResultSet resultSet = pS.executeQuery()) {
      List<Owner> result = new ArrayList<>();
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phone = resultSet.getString(4);
        result.add(new Owner(id, firstName, lastName, phone));
      }
      return result;
    }
  }
}
