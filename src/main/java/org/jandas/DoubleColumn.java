package org.jandas;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.types.pojo.FieldType;

public class DoubleColumn extends BaseColumn {

  public DoubleColumn(String name, FieldType fieldType, BufferAllocator bufferAllocator) {
    super(name, fieldType);
    fieldVector = new Float8Vector(name, bufferAllocator);
  }

}
