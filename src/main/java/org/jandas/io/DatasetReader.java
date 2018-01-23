package org.jandas.io;

import org.jandas.Dataset;
import org.jandas.io.csv.CsvReader;
import org.jandas.io.parquet.ParquetReader;

import java.util.HashMap;
import java.util.Map;

public class DatasetReader {

    private Map<String, String> options;

    public DatasetReader() {
        options = new HashMap<>();
    }

    public DatasetReader option(String key, String value) {
        options.put(key, value);
        return this;
    }

    public DatasetReader option(String key, boolean value) {
        options.put(key, value ? "true" : "false");
        return this;
    }

    public Dataset csv(String path) {
        return load("csv", path);
    }

    public Dataset parquet(String path) {
        return load("parquet", path);
    }

    private Dataset load(String format, String path) {
        addPathToOptions(path);
        Reader reader;

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
