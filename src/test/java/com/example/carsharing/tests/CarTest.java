package com.example.carsharing.tests;

import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class CarTest extends DBTestCase {

  public CarTest() {
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
    return new FlatXmlDataSet(new FileInputStream("src/test/resources/cars.xml"));
  }

  //column "clients" ignored because of for some rows its null
  public void testIgnoreClients() throws Exception {
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable dbTable = dbDataSet.getTable("cars");

    IDataSet xmlDataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/cars.xml"));
    ITable xmlTable = xmlDataSet.getTable("cars");

    SortedTable dbSortedTable = new SortedTable(dbTable, new String[]{"id"});
    dbSortedTable.setUseComparable(true);

    SortedTable xmlSortedTable = new SortedTable(xmlTable, new String[]{"id"});
    xmlSortedTable.setUseComparable(true);

    Assertion.assertEqualsIgnoreCols(xmlSortedTable, dbSortedTable, new String[]{"clients"});
  }
}
