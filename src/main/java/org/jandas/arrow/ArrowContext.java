package org.jandas.arrow;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;

public class ArrowContext {

  public static final long INIT_RESERVATION = 1024L * 1024L;
  public static final long MAX_ALLOCATION = Long.MAX_VALUE;

  private static ArrowContext instance = new ArrowContext();
  private BufferAllocator bufferAllocator;

  private ArrowContext() {
    bufferAllocator = new RootAllocator(MAX_ALLOCATION);
  }

  public static ArrowContext getInstance() {
    return instance;
  }

  public BufferAllocator getBufferAllocator() {
    return bufferAllocator;
  }

  public BufferAllocator createRootAllocator() {
    return new RootAllocator(Long.MAX_VALUE);
  }
}
