package org.jandas.arrow;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;

public class ArrowConfig {

  private static ArrowConfig instance = new ArrowConfig();
  private BufferAllocator bufferAllocator;

  private ArrowConfig() {
    bufferAllocator = new RootAllocator(Long.MAX_VALUE);
  }

  public static ArrowConfig getInstance() {
    return instance;
  }

  public BufferAllocator getBufferAllocator() {
    return bufferAllocator;
  }
}
