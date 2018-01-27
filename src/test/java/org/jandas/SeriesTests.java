package org.jandas;

import org.junit.Test;

import static org.junit.Assert.*;

public class SeriesTests {

    @Test
    public void testGetName() {
        Series series = new Series("seriesX");

        assertEquals("seriesX", series.getName());
    }

}