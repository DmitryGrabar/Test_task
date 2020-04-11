package com.github.grabar.bus_task.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    public static List<String> readFile(String filePath) throws IOException {
        File file = new File(filePath);
        List<String> data = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            sc.useDelimiter("\\n");
            while (sc.hasNextLine()) {
                data.add(sc.next().trim());
            }
        }
        return data;
    }
}
