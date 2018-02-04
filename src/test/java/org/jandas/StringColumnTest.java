package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class StringColumnTest extends BaseColumnTest {

  @Test
  public void testColumn() {
    Column stringColumn = ColumnFactory.getInstance().createColumn("name", DataType.String);

    assertEquals("name", stringColumn.name());
    assertTrue(stringColumn instanceof StringColumn);
    assertEquals(DataType.String, stringColumn.dataType());
  }

  @Test
  public void testColumnAndList() {
    Column stringColumn = ColumnFactory.getInstance().createColumn("name", DataType.String);
    List<String> list = Arrays.asList("john", "peter", "tom", "jack");

    assertEquals(0, stringColumn.length());

    stringColumn.fromList(list);

    assertEquals(4, stringColumn.length());

    List<String> result = stringColumn.toList();

    assertThat(result, CoreMatchers.hasItems("john", "peter", "tom", "jack"));

  }

}