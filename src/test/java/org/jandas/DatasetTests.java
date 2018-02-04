package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import org.junit.Test;

public class DatasetTests {

  @Test
  public void testConstructorWithEmptyColumn() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    Dataset ds = new Dataset("df1", col1, col2, col3);

    assertEquals("df1", ds.name());
    assertEquals(3, ds.columnCount());
    assertEquals(3, ds.columns().size());

    assertEquals("id", ds.get(0).name());
    assertEquals("name", ds.get(1).name());
    assertEquals("height", ds.get(2).name());

    assertEquals("id", ds.columns().get(0).name());
    assertEquals("name", ds.columns().get(1).name());
    assertEquals("height", ds.columns().get(2).name());

    assertEquals(2, ds.columns("name", "id").size());
    assertEquals("name", ds.columns("name", "id").get(0).name());
    assertEquals("id", ds.columns("name", "id").get(1).name());

    Column col4 = ColumnFactory.getInstance().createColumn("role", DataType.String);
    ds.add(1, col4);

    assertEquals(4, ds.columnCount());
    assertEquals("id", ds.get(0).name());
    assertEquals("role", ds.get(1).name());
    assertEquals("name", ds.get(2).name());
    assertEquals("height", ds.get(3).name());
  }

  @Test
  public void testDatasetIterator() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    Dataset ds = new Dataset("df1", col1, col2, col3);

    Iterator<Column> columnIterator = ds.iterator();
    assertTrue(columnIterator.hasNext());
    assertEquals("id", columnIterator.next().name());
    assertEquals("name", columnIterator.next().name());
    assertEquals("height", columnIterator.next().name());
    assertFalse(columnIterator.hasNext());
  }

  @Test
  public void testDatasetRowSelection() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    col1.fromList(Arrays.asList(11, 22, 33, 55));
    col2.fromList(Arrays.asList("john", "peter", "tom", "jack"));
    col3.fromList(Arrays.asList(150.0, 170.0, 165.0, 158.0));

    Dataset ds = new Dataset("df1", col1, col2, col3);

    assertEquals(4, ds.rowCount());

    assertEquals(3, ds.rows(0, 2).size());

    assertNull(ds.rows(3, 1));
  }

  @Test
  public void testShow() {
    Column col1 = ColumnFactory.getInstance().createColumn("id", DataType.Integer);
    Column col2 = ColumnFactory.getInstance().createColumn("name", DataType.String);
    Column col3 = ColumnFactory.getInstance().createColumn("height", DataType.Double);

    col1.fromList(Arrays.asList(11, 22, 33, 55));
    col2.fromList(Arrays.asList("john", "peter", "tom", "jack"));
    col3.fromList(Arrays.asList(150.0, 170.0, 165.0, 158.0));

    Dataset ds = new Dataset("df1", col1, col2, col3);

    ds.show();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testWrite() {
    Dataset df = new Dataset("df");

    df.write().option("compression", "snappy").parquet("/tmp/test.parquet");

    // TODO: verify output
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRead() {
    Dataset df = Dataset.read().option("header", "false").csv("/s3/data.csv");

    // TODO: verify df
  }

}