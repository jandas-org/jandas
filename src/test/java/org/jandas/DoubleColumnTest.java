package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoubleColumnTest extends BaseColumnTest {

  @Test
  public void testColumn() {
    Column doubleColumn = ColumnFactory.getInstance().createColumn("id", DataType.Double);

    assertEquals("id", doubleColumn.getName());
    assertTrue(doubleColumn.isNullable());
    assertTrue(doubleColumn instanceof DoubleColumn);
    assertEquals(DataType.Double, doubleColumn.getDataType());

    doubleColumn = ColumnFactory.getInstance().createColumn("id", DataType.Double, false);
    assertFalse(doubleColumn.isNullable());
  }

}