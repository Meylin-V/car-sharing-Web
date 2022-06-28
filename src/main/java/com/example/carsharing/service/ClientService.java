package com.example.carsharing.service;

import com.example.carsharing.db.DBConnection;
import com.example.carsharing.domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {

  public void saveToDB(String firstName, String lastName, String phone) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "INSERT INTO clients (first_name, last_name, phone) VALUES (?, ?, ?)")) {
      pS.setString(1, firstName);
      pS.setString(2, lastName);
      pS.setString(3, phone);
      pS.executeUpdate();
    }
  }

  public Optional<Client> findById(int id) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "SELECT * FROM clients WHERE id = " + id);
        ResultSet resultSet = pS.executeQuery()) {
      if (resultSet.next()) {
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phone = resultSet.getString(4);
        return Optional.of(new Client(id, firstName, lastName, phone));
      }
      return Optional.empty();
    }
  }

  public Optional<Object> getIndex() throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "SELECT id FROM clients ORDER BY id DESC LIMIT 1");
        ResultSet resultSet = pS.executeQuery()) {
      if (resultSet.next()) {
        return Optional.of(resultSet.getInt(1));
      }
    }
    return Optional.empty();
  }

  public List<Client> findAll() throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement("SELECT * FROM clients");
        ResultSet resultSet = pS.executeQuery()) {

      List<Client> result = new ArrayList<>();
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String phone = resultSet.getString(4);
        result.add(new Client(id, firstName, lastName, phone));
      }
      return result;
    }
  }

  public void deleteById(int index) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement("DELETE FROM clients WHERE id = ?")) {
      pS.setInt(1, index);
      pS.executeUpdate();
    }
  }
}
