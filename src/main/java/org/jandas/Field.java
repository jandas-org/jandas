package org.jandas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Field specifies the name and data type of a column
 */
@Setter
@Getter
@AllArgsConstructor
public class Field {
  private String name;
  private DataType type;
}
