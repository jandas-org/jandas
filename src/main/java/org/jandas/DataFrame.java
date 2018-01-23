package org.jandas;

import org.jandas.io.DataFrameReader;
import org.jandas.io.DataFrameWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFrame {

    List<Series> data;

    /** Constructors */

    public DataFrame() {
        data = new ArrayList<>();
    }

    public DataFrame(Series... series) {
        this();
        Collections.addAll(data, series);
    }

    /** Serialization / IO */

    public DataFrameWriter write() {
        return new DataFrameWriter(this);
    }

    public static DataFrameReader read() {
        return new DataFrameReader();
    }

    public List<Series> getData() {
        return data;
    }
}
