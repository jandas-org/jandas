package org.jandas;

import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.ArrowType.ArrowTypeID;
import org.apache.arrow.vector.types.pojo.FieldType;

public class BaseColumn implements Column {

  String name;
  FieldType fieldType;
  FieldVector fieldVector;

  public BaseColumn(String name, FieldType fieldType) {
    this.name = name;
    this.fieldType = fieldType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType getDataType() {
    final ArrowTypeID typeID = fieldType.getType().getTypeID();

    if (typeID == ArrowTypeID.Int) {
      return DataType.Integer;
    } else if (typeID == ArrowTypeID.FloatingPoint) {
        return DataType.Double;
    } else if (typeID == ArrowTypeID.Utf8) {
      return DataType.String;
    }

    return null;
  }

  @Override
  public boolean isNullable() {
    return fieldType.isNullable();
  }
}
