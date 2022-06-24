package com.example.carsharing.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DBConnection {

  private static DBConnection instance;
  private Connection connection;

  private DBConnection() {
  }

  public static DBConnection getInstance() {
    if (instance == null) {
      instance = new DBConnection();
    }
    return instance;
  }

  public void initConnection(DataSource dataSource) throws SQLException {
    connection = dataSource.getConnection();
  }

  public Connection getConnection() {
    return connection;
  }
}
