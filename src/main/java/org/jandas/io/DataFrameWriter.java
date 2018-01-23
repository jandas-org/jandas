package org.jandas.io;

import org.jandas.DataFrame;
import org.jandas.io.csv.CsvWriter;
import org.jandas.io.parquet.ParquetWriter;

import java.util.HashMap;
import java.util.Map;

public class DataFrameWriter {

    private DataFrame dataFrame;
    private Map<String, String> options;

    public DataFrameWriter(DataFrame dataFrame) {
        this.dataFrame = dataFrame;
        options = new HashMap<>();
    }

    public DataFrameWriter option(String key, String value) {
        options.put(key, value);
        return this;
    }

    public void csv(String path) {
        save("csv", path, dataFrame);
    }

    public void parquet(String path) {
        save("parquet", path, dataFrame);
    }

    private void save(String format, String path, DataFrame dataFrame) {
        addPathToOptions(path);
        IWriter writer;

        if ("csv".equals(format)) {
            writer = new CsvWriter(options, dataFrame);
        } else {
            writer = new ParquetWriter(options, dataFrame);
        }

        writer.save();
    }

    private void addPathToOptions(String path) {
        option("path", path);
    }
}
