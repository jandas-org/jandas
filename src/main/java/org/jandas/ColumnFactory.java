package org.jandas;

public class ColumnFactory {

  private static ColumnFactory instance = new ColumnFactory();

  private ColumnFactory() {
  }

  public static ColumnFactory getInstance() {
    return instance;
  }

  public Column createColumn(String name, DataType dataType) {
    if (name == null || dataType == null) {
      return null;
    }

    if (dataType == DataType.Integer) {
      return new IntegerColumn(name);
    } else if (dataType == DataType.Double) {
      return new DoubleColumn(name);
    } else if (dataType == DataType.String) {
      return new StringColumn(name);
    }

    return null;
  }

}
