package org.jandas.io;

import org.jandas.DataFrame;
import org.jandas.Series;
import org.jandas.io.csv.CsvReader;
import org.jandas.io.parquet.ParquetReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrameReader {

    private Map<String, String> options;

    public DataFrameReader() {
        options = new HashMap<>();
    }

    public DataFrameReader option(String key, String value) {
        options.put(key, value);
        return this;
    }

    public DataFrameReader option(String key, boolean value) {
        options.put(key, value ? "true" : "false");
        return this;
    }

    public DataFrame csv(String path) {
        return load("csv", path);
    }

    public DataFrame parquet(String path) {
        return load("parquet", path);
    }

    private DataFrame load(String format, String path) {
        addPathToOptions(path);
        IReader reader;

        if ("csv".equals(format)) {
            reader = new CsvReader(options);
        } else {
            reader = new ParquetReader(options);
        }

        return reader.load();
    }

    private void addPathToOptions(String path) {
        option("path", path);
    }

}
