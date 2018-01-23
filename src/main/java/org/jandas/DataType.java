package org.jandas;

public enum DataType {
  Integer(Integer.class),
  Double(Double.class),
  String(String.class);

  Class cls;

  DataType(Class cls) {
    this.cls = cls;
  }

  String getName() {
    return this.name();
  }

  Class getJavaClass() {
    return cls;
  }

}
