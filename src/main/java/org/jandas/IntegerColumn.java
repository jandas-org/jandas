package org.jandas;

import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.vector.IntVector;

public class IntegerColumn extends BaseColumn<Integer> {

  protected IntVector intVector;

  public IntegerColumn(String name) {
    super(name, DataType.Integer);
    intVector = new IntVector(name(), allocator);
  }

  @Override
  public int length() {
    return intVector.getValueCount();
  }

  @Override
  public Integer getValue(int index) {
    return intVector.get(index);
  }

  @Override
  public Column<Integer> fromList(List<Integer> list) {
    intVector.allocateNew();
    for (int i = 0; i < list.size(); i++) {
      Integer value = list.get(i);
      if (value != null) {
        intVector.setSafe(i, value);
      } else {
        intVector.setNull(i);
      }
    }

    intVector.setValueCount(list.size());

    return this;
  }

  @Override
  public List<Integer> toList() {
    int size = length();
    List<Integer> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(intVector.get(i));
    }
    return list;
  }

}
