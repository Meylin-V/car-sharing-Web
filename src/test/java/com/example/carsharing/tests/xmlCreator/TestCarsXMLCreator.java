package com.example.carsharing.tests.xmlCreator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class TestCarsXMLCreator {

  public static void main(String[] args) throws Exception {
    Class.forName("org.postgresql.Driver");

    Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost/car_sharing", "postgres", "password");

    IDatabaseConnection connection = new DatabaseConnection(conn);

    createAllCars(connection);
    crateAvailableCars(connection);
    crateNotAvailableCars(connection);
  }

  private static void createAllCars(IDatabaseConnection connection) {
    QueryDataSet dataSet = new QueryDataSet(connection);
    try {
      dataSet.addTable("cars");
      FlatXmlDataSet.write(dataSet, new FileOutputStream("src/test/resources/carsAll.xml"));
    } catch (DataSetException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void crateAvailableCars(IDatabaseConnection connection) {
    QueryDataSet dataSet = new QueryDataSet(connection);
    try {
      dataSet.addTable("cars", "select * from cars where available = true");
      FlatXmlDataSet.write(dataSet, new FileOutputStream("src/test/resources/carsAv.xml"));
    } catch (DataSetException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void crateNotAvailableCars(IDatabaseConnection connection) {
    QueryDataSet dataSet = new QueryDataSet(connection);
    try {
      dataSet.addTable("cars", "select * from cars where available = false");
      FlatXmlDataSet.write(dataSet, new FileOutputStream("src/test/resources/carsNotAv.xml"));
    } catch (DataSetException | IOException e) {
      e.printStackTrace();
    }
  }
}
