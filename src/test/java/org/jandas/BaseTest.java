package org.jandas;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

  BufferAllocator bufferAllocator;

  @Before
  public void setup() {
    bufferAllocator = new RootAllocator(Long.MAX_VALUE);
  }

  @After
  public void teardown() {
    bufferAllocator.close();
  }


}
