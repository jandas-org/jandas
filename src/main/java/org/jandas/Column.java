package org.jandas;

import java.util.List;

public interface Column<T> {

  String name();

  DataType dataType();

  int length();

  Column<T> fromList(List<T> list);

  List<T> toList();
}
