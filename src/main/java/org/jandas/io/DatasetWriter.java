package org.jandas.io;

import org.jandas.Dataset;
import org.jandas.io.csv.CsvWriter;
import org.jandas.io.parquet.ParquetWriter;

import java.util.HashMap;
import java.util.Map;

public class DatasetWriter {

    private Dataset dataset;
    private Map<String, String> options;

    public DatasetWriter(Dataset dataset) {
        this.dataset = dataset;
        options = new HashMap<>();
    }

    public DatasetWriter option(String key, String value) {
        options.put(key, value);
        return this;
    }

    public void csv(String path) {
        save("csv", path, dataset);
    }

    public void parquet(String path) {
        save("parquet", path, dataset);
    }

    private void save(String format, String path, Dataset dataset) {
        addPathToOptions(path);
        Writer writer;

        if ("csv".equals(format)) {
            writer = new CsvWriter(options, dataset);
        } else {
            writer = new ParquetWriter(options, dataset);
        }

        writer.save();
    }

    private void addPathToOptions(String path) {
        option("path", path);
    }
}
