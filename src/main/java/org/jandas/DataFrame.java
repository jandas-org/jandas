package org.jandas;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.jandas.io.DataFrameReader;
import org.jandas.io.DataFrameWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFrame {

  private String name;
  private List<Column> data = new ArrayList<>();

  public DataFrame(String name) {
    this.name = name;
  }

  public DataFrame(String name, Column... columns) {
    this(name);
    Collections.addAll(data, columns);
  }

  public String getName() {
    return name;
  }

  public int getColumnCount() {
    return data.size();
  }

  public List<Column> getColumns() {
    return data;
  }

  public List<Column> getColumns(String... names) {
    Map<String, Column> idToColumnMap = data.stream().collect(
        Collectors.toMap(Column::getName, c -> c));

    return Arrays.stream(names)
        .filter(idToColumnMap::containsKey)
        .map(idToColumnMap::get)
        .collect(Collectors.toList());
  }

  public DataFrameWriter write() {
    return new DataFrameWriter(this);
  }

  public static DataFrameReader read() {
    return new DataFrameReader();
  }
}
