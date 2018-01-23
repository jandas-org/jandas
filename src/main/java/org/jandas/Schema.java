package org.jandas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Schema as a collection of Fields
 */
public class Schema implements Iterable<Field> {

  List<Field> fields = new ArrayList<>();

  /**
   * Constructs a Schema object.
   */
  public Schema() {
  }

  /**
   * Returns an iterator over the fields.
   *
   * @return field iterator
   */
  @Override
  public Iterator<Field> iterator() {
    return new Iterator<Field>() {

      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < fields.size();
      }

      @Override
      public Field next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return fields.get(current++);
      }
    };
  }

}
