package org.jandas;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.jandas.arrow.ArrowTable;
import org.jandas.io.DatasetReader;
import org.jandas.io.DatasetWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dataset as a collection of columns.
 */
public class Dataset implements Iterable<Column> {

  private String name;
  private Schema schema;
  private List<Column> data;
  private ArrowTable arrowTable;

  /**
   * Constructs a Dataset object.
   */
  public Dataset() {
    schema = new Schema();
    data = new ArrayList<>();
    arrowTable = new ArrowTable();
  }

  /**
   * Constructs a Dataset object with the specified name.
   *
   * @param name the name for the Dataset object
   */
  public Dataset(String name) {
    this();
    this.name = name;
  }

  /**
   * Constructs a Dataset object with the specified name and columns.
   *
   * @param name the name for the Dataset object
   * @param columns the columns to be in the Dataset object
   */
  public Dataset(String name, Column... columns) {
    this(name);
    Collections.addAll(data, columns);
  }

  /**
   * Returns the name of the Dataset object.
   *
   * @return the name of this dataset
   */
  public String name() {
    return name;
  }

  /**
   * Returns the schema of the Dataset object.
   *
   * @return the schema of this dataset
   */
  public Schema schema() {
    return schema;
  }

  /**
   * Returns the number of columns in this dataset.
   *
   * @return the number of columns in this dataset
   */
  public int columnCount() {
    return data.size();
  }

  /**
   * Returns the number of rows in this dataset.
   * The length of the first column is returned.
   *
   * @return the number of rows in this dataset
   */
  public int rowCount() {
    return data.isEmpty() ? 0 : data.get(0).length();
  }

  /**
   * Returns the column at the specified index in this dataset.
   *
   * @param colIndex index of the column to return
   * @return the column at the specified index in this dataset
   */
  public Column get(int colIndex) {
    return data.get(colIndex);
  }

  /**
   * Inserts the specified column at the specified position in this dataset.
   *
   * @param colIndex index at which the specified column is to be inserted
   * @param column column to be inserted
   */
  public void add(int colIndex, Column column) {
    data.add(colIndex, column);
  }

  /**
   * Returns all the columns.
   *
   * @return list of columns
   */
  public List<Column> columns() {
    return data;
  }

  /**
   * Returns the columns of the specified column names.
   *
   * @param names
   * @return
   */
  public List<Column> columns(String... names) {
    Map<String, Column> idToColumnMap = data.stream().collect(
        Collectors.toMap(Column::name, c -> c));

    return Arrays.stream(names)
        .filter(idToColumnMap::containsKey)
        .map(idToColumnMap::get)
        .collect(Collectors.toList());
  }

  /**
   * Returns all the rows in the Dataset object.
   *
   * @return List of Rows
   */
  public List<Row> rows() {
    return rows(0, rowCount() - 1);
  }

  /**
   * Returns the rows from row index r1 to row index r2.
   *
   * @param r1 starting row index
   * @param r2 ending row index
   *
   * @return List of selected rows
   */
  public List<Row> rows(int r1, int r2) {
    if (r1 > r2) {
      return null;
    }

    List<Row> rows = new ArrayList<>(r2 - r1 + 1);
    for (int i = r1; i <= r2; i++) {
      rows.add(new Row());
    }

    return rows;
  }

  /**
   * Gets a DatasetWriter.
   *
   * @return a DatasetWriter
   */
  public DatasetWriter write() {
    return new DatasetWriter(this);
  }

  /**
   * Gets a DatasetReader.
   *
   * @return a DatasetReader
   */
  public static DatasetReader read() {
    return new DatasetReader();
  }

  /**
   * Returns an iterator over the columns.
   *
   * @return column iterator
   */
  @Override
  public Iterator<Column> iterator() {
    return new Iterator<Column>() {

      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < Dataset.this.data.size();
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
