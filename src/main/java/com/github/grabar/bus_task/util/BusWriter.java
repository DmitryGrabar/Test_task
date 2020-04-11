package com.github.grabar.bus_task.util;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.entity.CompanyName;
import com.github.grabar.bus_task.service.impl.BusServiceImpl;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BusWriter {
    private static final Logger LOG = Logger.getLogger(BusWriter.class);
    private static final String OUTPUT_FILE_PATH = ".\\src\\main\\resources\\output.txt";

    public static void writeToTxtFile(List<Bus> buses) {
        int firstGrottyOccurrence = new BusServiceImpl().findFirstCompanyNameOccurrence(buses, CompanyName.GROTTY);

        try (FileWriter writer = new FileWriter(OUTPUT_FILE_PATH)) {
            for (int i = 0; i < buses.size(); i++) {
                if (i == firstGrottyOccurrence) {
                    writer.write("\r");
                }
                writer.write(buses.get(i).toString() + '\n');
            }
        } catch (IOException e) {
            LOG.error("Troubles while writing data to output.txt");
        }
    }
}
