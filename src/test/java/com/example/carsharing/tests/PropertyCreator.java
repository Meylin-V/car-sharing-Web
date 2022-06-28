package com.example.carsharing.tests;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;

public class PropertyCreator {

  public static void setProperties() {
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
        "jdbc:postgresql://localhost/car_sharing");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "password");
  }
}
