package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerColumnTest extends BaseColumnTest {

  @Test
  public void testColumn() {
    Column integerColumn = ColumnFactory.getInstance().createColumn("id", DataType.Integer);

    assertEquals("id", integerColumn.getName());
    assertTrue(integerColumn.isNullable());
    assertTrue(integerColumn instanceof IntegerColumn);
    assertEquals(DataType.Integer, integerColumn.getDataType());

    integerColumn = ColumnFactory.getInstance().createColumn("id", DataType.Integer, false);
    assertFalse(integerColumn.isNullable());
  }

}