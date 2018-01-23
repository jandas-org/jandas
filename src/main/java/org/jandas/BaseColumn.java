package org.jandas;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.jandas.arrow.ArrowContext;

public class BaseColumn<T> implements Column<T> {

  protected BufferAllocator allocator;
  protected Field field;

  // arrow
  protected FieldType fieldType;
  protected FieldVector fieldVector;

  public BaseColumn(String name, DataType dataType) {
    this(new Field(name, dataType));
  }

  public BaseColumn(Field field) {
    this(field, ArrowContext.getInstance().createRootAllocator());
  }

  public BaseColumn(Field field, BufferAllocator rootAllocator) {
    this.field = field;
    allocator = rootAllocator;
  }

  @Override
  public String name() {
    return field.getName();
  }

  @Override
  public DataType dataType() {
    return this.field.getType();
  }

  @Override
  public int length() {
    return 0;
  }

  @Override
  public Column<T> fromList(List<T> list) {
    return null;
  }

  @Override
  public List<T> toList() {
    return null;
  }
}
