package org.jandas;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.jandas.arrow.ArrowDataFrame;
import org.jandas.io.DataFrameReader;
import org.jandas.io.DataFrameWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFrame implements Iterable<Column> {

  private String name;
  private List<Column> data = new ArrayList<>();
  private ArrowDataFrame arrowDataFrame = new ArrowDataFrame();

  public DataFrame(String name) {
    this.name = name;
  }

  public DataFrame(String name, Column... columns) {
    this(name);
    Collections.addAll(data, columns);
  }

  public String name() {
    return name;
  }

  public int columnCount() {
    return data.size();
  }

  public int rowCount() {
    // TODO
    return 0;
  }

  public Column get(int colIndex) {
    return data.get(colIndex);
  }

  public void set(int colIndex, Column column) {
    data.add(colIndex, column);
  }

  public List<Column> columns() {
    return data;
  }

  public List<Column> columns(String... names) {
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

  @Override
  public Iterator<Column> iterator() {
    return new Iterator<Column>() {

      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < DataFrame.this.data.size();
      }

      @Override
      public Column next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return data.get(current++);
      }
    };
  }
}
