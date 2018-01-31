package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import org.junit.Test;

public class DataFrameTests {

  @Test
  public void testConstructor() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    DataFrame df = new DataFrame("df1", col1, col2, col3);

    assertEquals("df1", df.name());
    assertEquals(3, df.columnCount());

    assertEquals("id", df.get(0).getName());
    assertEquals("name", df.get(1).getName());
    assertEquals("height", df.get(2).getName());

    assertEquals("id", df.columns().get(0).getName());
    assertEquals("name", df.columns().get(1).getName());
    assertEquals("height", df.columns().get(2).getName());

    assertEquals("name", df.columns("name", "id").get(0).getName());
    assertEquals("id", df.columns("name", "id").get(1).getName());

    Column col4 = ColumnFactory.getInstance().createColumn("role", DataType.String);
    df.set(1, col4);

    assertEquals(4, df.columnCount());
    assertEquals("id", df.get(0).getName());
    assertEquals("role", df.get(1).getName());
    assertEquals("name", df.get(2).getName());
    assertEquals("height", df.get(3).getName());
  }

  @Test
  public void testDataFrameIterator() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    DataFrame df = new DataFrame("df1", col1, col2, col3);

    Iterator<Column> columnIterator = df.iterator();
    assertTrue(columnIterator.hasNext());
    assertEquals("id", columnIterator.next().getName());
    assertEquals("name", columnIterator.next().getName());
    assertEquals("height", columnIterator.next().getName());
    assertFalse(columnIterator.hasNext());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testWrite() {
    DataFrame df = new DataFrame("df");

    df.write().option("compression", "snappy").parquet("/tmp/test.parquet");

    // TODO: verify output
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRead() {
    DataFrame df = DataFrame.read().option("header", "false").csv("/s3/data.csv");

    // TODO: verify df
  }

}