package org.jandas.arrow;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.pojo.Schema;

/**
 * A table-like container to hold the low-level arrow vectors for backing a Dataset.
 */
public class ArrowTable implements Iterable<ValueVector> {

  private List<ValueVector> vectors = Lists.newArrayList();
  private BufferAllocator allocator;
  private Schema schema;

  /**
   * Construct an ArrowTable object.
   */
  public ArrowTable() {
    this.allocator = ArrowContext.getInstance().createRootAllocator();
  }

  /**
   * Returns the schema of the ArrowTable.
   *
   * @return the Schema object
   */
  public Schema getSchema() {
    return schema;
  }

  /**
   * Clears all vectors of the ArrowTable.
   */
  public void clearVectors() {
    vectors.forEach(ValueVector::clear);
  }

  /**
   * Returns an iterator over the ValueVector.
   *
   * @return ValueVector iterator
   */
  @Override
  public Iterator<ValueVector> iterator() {
    return new Iterator<ValueVector>() {

      private int current = 0;

      @Override
      public boolean hasNext() {
        return current < vectors.size();
      }

      @Override
      public ValueVector next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return vectors.get(current++);
      }
    };
  }

}
