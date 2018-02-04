package org.jandas;

import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.vector.Float8Vector;

public class DoubleColumn extends BaseColumn<Double> {

  protected Float8Vector float8Vector;

  public DoubleColumn(String name) {
    super(name, DataType.Double);
    float8Vector = new Float8Vector(name(), allocator);
  }

  @Override
  public int length() {
    return float8Vector.getValueCount();
  }

  @Override
  public Double getValue(int index) {
    return float8Vector.get(index);
  }

  @Override
  public Column<Double> fromList(List<Double> list) {
    float8Vector.allocateNew();
    for (int i = 0; i < list.size(); i++) {
      Double value = list.get(i);
      if (value != null) {
        float8Vector.set(i, value);
      } else {
        float8Vector.setNull(i);
      }
    }

    float8Vector.setValueCount(list.size());

    return this;
  }

  @Override
  public List<Double> toList() {
    int size = length();
    List<Double> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(float8Vector.get(i));
    }
    return list;
  }

}
