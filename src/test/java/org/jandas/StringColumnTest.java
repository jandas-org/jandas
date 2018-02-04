package org.jandas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class StringColumnTest extends BaseTest {

  @Test
  public void testColumn() {
    StringColumn stringColumn = new StringColumn("name");

    assertEquals("name", stringColumn.name());
    assertEquals(DataType.String, stringColumn.dataType());
  }

  @Test
  public void testColumnAndList() {
    StringColumn stringColumn = new StringColumn("name");
    List<String> list = Arrays.asList("john", "peter", "tom", "jack");

    assertEquals(0, stringColumn.length());

    stringColumn.fromList(list);

    assertEquals(4, stringColumn.length());

    List<String> result = stringColumn.toList();

    assertThat(result, CoreMatchers.hasItems("john", "peter", "tom", "jack"));

  }

}