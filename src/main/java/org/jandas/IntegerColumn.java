package org.jandas;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.types.pojo.FieldType;

public class IntegerColumn extends BaseColumn {

  public IntegerColumn(String name, FieldType fieldType, BufferAllocator bufferAllocator) {
    super(name, fieldType);
    fieldVector = new IntVector(name, bufferAllocator);
  }

}
