package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class IntegerColumnTest extends BaseTest {

  @Test
  public void testColumn() {
    IntegerColumn integerColumn = new IntegerColumn("id");

    assertEquals("id", integerColumn.name());
    assertEquals(DataType.Integer, integerColumn.dataType());
  }

  @Test
  public void testColumnAndList() {
    IntegerColumn integerColumn = new IntegerColumn("id");
    List<Integer> list = Arrays.asList(11, 12, 13, 14);

    assertEquals(0, integerColumn.length());

    integerColumn.fromList(list);

    assertEquals(4, integerColumn.length());

    List<Integer> result = integerColumn.toList();

    assertThat(result, CoreMatchers.hasItems(11,12,13,14));
  }



}