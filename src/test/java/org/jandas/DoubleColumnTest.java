package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoubleColumnTest extends BaseTest {

  @Test
  public void testColumn() {
    DoubleColumn doubleColumn = new DoubleColumn("id");

    assertEquals("id", doubleColumn.name());
    assertEquals(DataType.Double, doubleColumn.dataType());
  }

}