package org.jandas;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.types.FloatingPointPrecision;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.jandas.arrow.ArrowConfig;

public class ColumnFactory {

  private BufferAllocator bufferAllocator = ArrowConfig.getInstance().getBufferAllocator();
  private static ColumnFactory instance = new ColumnFactory();

  private ColumnFactory() {}

  public static ColumnFactory getInstance() {
    return instance;
  }

  public Column createColumn(String name, DataType dataType) {
    return createColumn(name, dataType, true);
  }

  public Column createColumn(String name, DataType dataType, boolean isNullable) {
    if (name == null || dataType == null) {
      return null;
    }

    if (dataType == DataType.Integer) {
      return new IntegerColumn(name, new FieldType(isNullable, new ArrowType.Int(4, true), null),
          bufferAllocator);
    } else if (dataType == DataType.Double) {
      return new DoubleColumn(name, new FieldType(isNullable, new ArrowType.FloatingPoint(
          FloatingPointPrecision.DOUBLE), null), bufferAllocator);
    } else if (dataType == DataType.String) {
      return new StringColumn(name, new FieldType(isNullable, new ArrowType.Utf8(), null),
          bufferAllocator);
    }

    return null;
  }

}
