package com.example.carsharing.tests.carTest;

import com.example.carsharing.tests.PropertyCreator;
import java.io.FileInputStream;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class CarTestNotAv extends DBTestCase {

  public CarTestNotAv() {
    super();
    PropertyCreator.setProperties();
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSet(new FileInputStream("src/test/resources/carsNotAv.xml"));
  }

  public void testAvailable() throws Exception {
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable dbTable = dbDataSet.getTable("cars");

    IDataSet xmlDataSet = new FlatXmlDataSet(
        new FileInputStream("src/test/resources/carsNotAv.xml"));
    ITable xmlTable = xmlDataSet.getTable("cars");

    SortedTable dbSortedTable = new SortedTable(dbTable, new String[]{"id"});
    dbSortedTable.setUseComparable(true);

    SortedTable xmlSortedTable = new SortedTable(xmlTable, new String[]{"id"});
    xmlSortedTable.setUseComparable(true);

    Assertion.assertEquals(xmlSortedTable, dbSortedTable);
  }
}
