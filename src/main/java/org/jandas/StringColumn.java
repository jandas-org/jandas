package org.jandas;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.types.pojo.FieldType;

public class StringColumn extends BaseColumn {

  public StringColumn(String name, FieldType fieldType, BufferAllocator bufferAllocator) {
    super(name, fieldType);
    fieldVector = new VarCharVector(name, bufferAllocator);
  }

}
