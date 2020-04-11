package com.github.grabar.bus_task;

import com.github.grabar.bus_task.entity.Bus;
import com.github.grabar.bus_task.service.impl.BusServiceImpl;
import com.github.grabar.bus_task.util.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //String filePath = readConsoleLine();
        String filePath = ".\\src\\main\\resources\\input.txt";
        List<String> data = DataReader.readFile(filePath);
        List<Bus> buses = new BusParser().parse(data);
        List<Bus> effectiveBuses = new BusServiceImpl().getEffectiveBuses(buses);
        effectiveBuses.sort(new BusComparator());
        //System.out.println(effectiveBuses);
        BusWriter.writeToTxtFile(effectiveBuses);
    }

    private static String readConsoleLine() {
        String line = null;
        try (Scanner sc = new Scanner(System.in)) {
            if (sc.hasNextLine()) {
                line = sc.nextLine();
            }
        }
        return line;
    }
}
