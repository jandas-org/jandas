package org.jandas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataFrameTests {

    @Test
    public void testConstructor() {
        Series s1 = new Series();
        Series s2 = new Series();

        DataFrame df = new DataFrame(s1, s2);

        assertEquals(2, df.getData().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testWrite() {
        DataFrame df = new DataFrame();

        df.write().option("compression", "snappy").parquet("/tmp/test.parquet");

        // TODO: verify output
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRead() {
        DataFrame df = DataFrame.read().option("header", "false").csv("/s3/data.csv");

        // TODO: verify df
    }

}