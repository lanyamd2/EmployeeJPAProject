package com.bootswana.employeejpaproject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CSVReader {

    public static Map<Integer, String[]> readCSV() {
        Map<Integer, String[]> dataMap = new HashMap<>();

        try {
            ClassPathResource resource = new ClassPathResource("data.csv");
            InputStream inputStream = resource.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int key = Integer.parseInt(data[0]);

                dataMap.put(key, data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }
}