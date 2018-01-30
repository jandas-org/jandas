package org.jandas;

public interface Column {

  String getName();

  DataType getDataType();

  boolean isNullable();
}
