package com.example.carsharing.tests.ownerTest;

import com.example.carsharing.tests.PropertyCreator;
import java.io.FileInputStream;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class OwnerTest extends DBTestCase {

  public OwnerTest() {
    super();
    PropertyCreator.setProperties();
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSet(new FileInputStream("src/test/resources/owners.xml"));
  }

  public void test() throws Exception {
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable dbTable = dbDataSet.getTable("owners");

    IDataSet xmlDataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/owners.xml"));
    ITable xmlTable = xmlDataSet.getTable("owners");

    Assertion.assertEquals(xmlTable, dbTable);
  }
}
