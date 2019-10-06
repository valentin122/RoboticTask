package ru.vzhigalov.csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    public String[] getAllFilesInDirectory (String path){
        File folder = new File(path);

        String[] files = folder.list(new FilenameFilter() {

            @Override
            public boolean accept(File folder, String name) {
                return name.endsWith(".csv");
            }
        });

        for (String fileName : files) {
            System.out.println("File: " + fileName);
        }
        return files;
    }

    public List<PriceList> readCsvPrices(String[] fileNames) throws IOException {
        List<PriceList> details = new ArrayList<>();
        for(int i = 0; i < fileNames.length; i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNames[i]));
            Scanner scanner = new Scanner(bufferedReader);
            String[] multiCol = bufferedReader.readLine().split("\"");
            String[] cols = bufferedReader.readLine().split(";");
            if (multiCol.length != 0) {

            }
            while (scanner.hasNext()) {
                scanner.
                scanner.nextLine().split();
            }
            /*CSVReader reader = new CSVReader(new FileReader(("src/main/resources/" + fileNames[i])), ';', '@', 1);
            //List<String[]> allRows = reader.readAll();
            String[] row;
            while ((row = reader.readNext()) != null) {
                if(row != null) {
                    System.out.println(Arrays.toString(row));
                }
            }*/
            /*int j = 1;
            for(String[] row : allRows){
                System.out.println(Arrays.toString(row));
                System.out.println("Строка в файле №: " + j);
                j++;
                System.out.println(row.length);
            }*/
        }
        return details;
    }

}
