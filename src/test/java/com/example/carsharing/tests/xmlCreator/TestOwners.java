package com.example.carsharing.tests.xmlCreator;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class TestOwners {
  public static void main(String[] args) throws Exception{
    Class.forName("org.postgresql.Driver");

    Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost/car_sharing", "postgres", "password");

    IDatabaseConnection connection = new DatabaseConnection(conn);
    QueryDataSet dataSet = new QueryDataSet(connection);

    dataSet.addTable("owners");

    FlatXmlDataSet.write(dataSet,new FileOutputStream("src/test/resources/owners.xml"));
  }
}
