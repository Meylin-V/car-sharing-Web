package com.example.carsharing.tests.clientTest;

import com.example.carsharing.tests.PropertyCreator;
import java.io.FileInputStream;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class ClientTest extends DBTestCase {

  public ClientTest() {
    super();
    PropertyCreator.setProperties();
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
