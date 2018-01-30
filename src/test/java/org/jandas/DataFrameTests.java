package org.jandas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataFrameTests {

  @Test
  public void testConstructor() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    DataFrame df = new DataFrame("df1", col1, col2, col3);

    assertEquals("df1", df.getName());
    assertEquals(3, df.getColumnCount());

    assertEquals("id", df.getColumns().get(0).getName());
    assertEquals("name", df.getColumns().get(1).getName());
    assertEquals("height", df.getColumns().get(2).getName());

    assertEquals("name", df.getColumns("name", "id").get(0).getName());
    assertEquals("id", df.getColumns("name", "id").get(1).getName());
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