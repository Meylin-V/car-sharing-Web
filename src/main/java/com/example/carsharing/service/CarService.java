package com.example.carsharing.service;

import com.example.carsharing.db.DBConnection;
import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.Client;
import com.example.carsharing.domain.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {

  private int currentIndex;

  public int getCurrentIndex() {
    return currentIndex;
  }

  public void setCurrentIndex(int currentIndex) {
    this.currentIndex = currentIndex;
  }

  public void saveToDB(String model, int price, int ownerId) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "INSERT INTO cars (model, price, owner, clients, available) VALUES (?, ?, ?, null, true)")) {
      pS.setString(1, model);
      pS.setInt(2, price);
      pS.setInt(3, ownerId);
      pS.executeUpdate();
    }
  }

  public List<Car> findAll(String mark) throws SQLException {
    String sql = "SELECT * FROM cars ORDER BY ";
    if (mark.equals("id")) {
      sql += "id";
    } else if (mark.equals("price")) {
      sql += "price";
    } else {
      sql += "available";
    }
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(sql);
        ResultSet resultSet = pS.executeQuery()) {
      return find(resultSet);
    }
  }

  public List<Car> findAvailable(String mark) throws SQLException {
    String sql = "SELECT * FROM cars WHERE available = true ORDER BY ";
    if (mark.equals("id")) {
      sql += "id";
    } else {
      sql += "price";
    }
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        sql);
        ResultSet resultSet = pS.executeQuery()) {
      return find(resultSet);
    }
  }

  public List<Car> find(ResultSet resultSet) throws SQLException {
    List<Car> result = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt(1);
      String model = resultSet.getString(2);
      int price = resultSet.getInt(3);

      OwnerService ownerService = new OwnerService();
      int ownerID = resultSet.getInt(4);
      Owner owner = ownerService.findById(ownerID)
          .orElseThrow(() -> new IllegalArgumentException("invalid owner id"));

      ClientService clientService = new ClientService();
      int clientID = resultSet.getInt(5);
      Client client = clientService.findById(clientID)
          .orElseThrow(() -> new IllegalArgumentException("invalid client id"));

      boolean available = resultSet.getBoolean(6);

      result.add(new Car(id, model, price, owner, client, available));
    }
    return result;
  }

  public void makeNotAvailable(int carIndex, int clientIndex) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    try (PreparedStatement pS = connection.prepareStatement(
        "UPDATE cars SET clients = ?, available = false WHERE id = ?")) {
      pS.setInt(1, clientIndex);
      pS.setInt(2, carIndex);
      pS.executeUpdate();
    }
  }

  public void makeAvailable(int index) throws SQLException {
    if (index >= 0) {
      Connection connection = DBConnection.getInstance().getConnection();
      try (PreparedStatement pS = connection.prepareStatement(
          "UPDATE cars SET clients = null, available = true WHERE clients = ?")) {
        pS.setInt(1, index);
        pS.executeUpdate();
      }
    }
  }
}
