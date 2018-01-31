package org.jandas.arrow;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.pojo.Schema;

public class ArrowDataFrame implements Iterable<ValueVector> {

  private List<ValueVector> vectors = Lists.newArrayList();
  private BufferAllocator allocator;
  private Schema schema;

  public ArrowDataFrame() {
    this.allocator = ArrowConfig.getInstance().getBufferAllocator();
  }

  public Schema getSchema() {
    return schema;
  }

  public void clearVectors() {
    vectors.forEach(ValueVector::clear);
  }


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
