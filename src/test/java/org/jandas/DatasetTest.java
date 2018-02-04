package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import org.junit.Test;

public class DatasetTest extends BaseTest {

  @Test
  public void testConstructorWithEmptyColumn() {
    IntegerColumn col1 = new IntegerColumn("id");
    StringColumn  col2 = new StringColumn("name");
    DoubleColumn  col3 = new DoubleColumn("height");

    Dataset dataset = new Dataset("df1", col1, col2, col3);

    assertEquals("df1", dataset.name());
    assertEquals(3, dataset.columnCount());
    assertEquals(3, dataset.columns().size());

    assertEquals("id", dataset.get(0).name());
    assertEquals("name", dataset.get(1).name());
    assertEquals("height", dataset.get(2).name());

    assertEquals("id", dataset.columns().get(0).name());
    assertEquals("name", dataset.columns().get(1).name());
    assertEquals("height", dataset.columns().get(2).name());

    assertEquals(2, dataset.columns("name", "id").size());
    assertEquals("name", dataset.columns("name", "id").get(0).name());
    assertEquals("id", dataset.columns("name", "id").get(1).name());

    StringColumn col4 = new StringColumn("role");
    dataset.add(1, col4);

    assertEquals(4, dataset.columnCount());
    assertEquals("id", dataset.get(0).name());
    assertEquals("role", dataset.get(1).name());
    assertEquals("name", dataset.get(2).name());
    assertEquals("height", dataset.get(3).name());
  }

  @Test
  public void testDatasetIterator() {
    IntegerColumn col1 = new IntegerColumn("id");
    StringColumn  col2 = new StringColumn("name");
    DoubleColumn  col3 = new DoubleColumn("height");

    Dataset dataset = new Dataset("df1", col1, col2, col3);

    Iterator<Column> columnIterator = dataset.iterator();
    assertTrue(columnIterator.hasNext());
    assertEquals("id", columnIterator.next().name());
    assertEquals("name", columnIterator.next().name());
    assertEquals("height", columnIterator.next().name());
    assertFalse(columnIterator.hasNext());
  }

  @Test
  public void testDatasetRowSelection() {
    IntegerColumn col1 = new IntegerColumn("id");
    StringColumn  col2 = new StringColumn("name");
    DoubleColumn  col3 = new DoubleColumn("height");

    col1.fromList(Arrays.asList(11, 22, 33, 55));
    col2.fromList(Arrays.asList("john", "peter", "tom", "jack"));
    col3.fromList(Arrays.asList(150.0, 170.0, 165.0, 158.0));

    Dataset dataset = new Dataset("df1", col1, col2, col3);

    assertEquals(4, dataset.rowCount());

    assertEquals(3, dataset.rows(0, 2).size());

    assertNull(dataset.rows(3, 1));
  }

  @Test
  public void testShow() {
    IntegerColumn col1 = new IntegerColumn("id");
    StringColumn  col2 = new StringColumn("name");
    DoubleColumn  col3 = new DoubleColumn("height");

    col1.fromList(Arrays.asList(11, 22, 33, 55));
    col2.fromList(Arrays.asList("john", "peter", "tom", "jack"));
    col3.fromList(Arrays.asList(150.0, 170.0, 165.0, 158.0));

    Dataset dataset = new Dataset("df1", col1, col2, col3);

    dataset.show();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testWrite() {
    Dataset dataset = new Dataset("ds");

    dataset.write().option("compression", "snappy").parquet("/tmp/test.parquet");

    // TODO: verify output
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRead() {
    Dataset dataset = Dataset.read().option("header", "false").csv("/s3/data.csv");

    // TODO: verify dataset
  }

}