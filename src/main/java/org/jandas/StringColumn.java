package org.jandas;

import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.complex.reader.FieldReader;

public class StringColumn extends BaseColumn<String> {

  protected VarCharVector varCharVector;

  public StringColumn(String name) {
    super(name, DataType.String);
    varCharVector = new VarCharVector(name(), allocator);
  }

  @Override
  public int length() {
    return varCharVector.getValueCount();
  }

  @Override
  public String getValue(int index) {
    return varCharVector.getObject(index).toString();
  }

  @Override
  public Column<String> fromList(List<String> list) {
    varCharVector.allocateNew();
    for (int i = 0; i < list.size(); i++) {
      String value = list.get(i);
      if (value != null) {
        varCharVector.set(i, value.getBytes());
      } else {
        varCharVector.setNull(i);
      }
    }

    varCharVector.setValueCount(list.size());

    return this;
  }

  @Override
  public List<String> toList() {
    int size = length();
    FieldReader fieldReader = varCharVector.getReader();
    List<String> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      fieldReader.setPosition(i);
      list.add(fieldReader.readText().toString());
    }
    return list;
  }

}
