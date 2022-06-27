package com.example.carsharing.tests;

import java.io.FileInputStream;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class ClientTest extends DBTestCase {

  public ClientTest() {
    super();
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
    System.setProperty(
        PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://localhost/car_sharing");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres");
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "password");
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSet(new FileInputStream("src/test/resources/clients.xml"));
  }

  public void test() throws Exception {
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable dbTable = dbDataSet.getTable("clients");

    IDataSet xmlDataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/clients.xml"));
    ITable xmlTable = xmlDataSet.getTable("clients");

    Assertion.assertEquals(xmlTable, dbTable);
  }
}