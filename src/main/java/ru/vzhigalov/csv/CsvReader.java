package ru.vzhigalov.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.vzhigalov.Constants.*;
import static ru.vzhigalov.Main.DIRECTORY_PATH;

public class CsvReader {


    public String[] getAllFilesInDirectory(String path) {
        File folder = new File(path);

        String[] files = folder.list(
                (fold, name) -> {
                    return name.endsWith(".csv");
                }
        );

        for (String fileName : files) {
            System.out.println("File: " + fileName);
        }
        return files;
    }

    /**
     * Класс для обработки файлов .csv и подготовки к заливке в базу.
     *
     * @param fileNames
     * @return
     * @throws IOException
     */
    public List<Detail> readCsvPrices(String[] fileNames) throws IOException {
        List<Detail> details = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {
            HashMap<String, Integer> indexAndValueArray = new HashMap<>();
            String fullPath = String.format("%s%s", DIRECTORY_PATH, fileNames[i]);
            System.out.printf("%s%s", "FullPath: ", fullPath);
            System.out.println();

            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(fullPath));

            indexAndValueArray = createIndexAndValueArray(getStringOfLine(lines.get(0).trim().toCharArray()));
            final HashMap<String, Integer> parameterMap = indexAndValueArray;

            // Удаляем первую строку, т.к. мы ее уже вычитали в карту "indexAndValueArray"
            lines.remove(0);

            lines.forEach(line -> {
                Detail detail = createDetail(getStringOfLine(line.toCharArray()), parameterMap);
                details.add(detail);
            });
        }
        return details;
    }

    private HashMap<String, Integer> createIndexAndValueArray(String[] arrayOfSubstring) {
        HashMap<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < arrayOfSubstring.length; i++) {
            final int j = i;
            fieldsMap.forEach((k, v) -> {
                if (v.equalsIgnoreCase(arrayOfSubstring[j])) {
                    indexMap.put(k, j);
                }
            });
        }
        return indexMap;
    }

    private Detail createDetail(String[] arrayOfSubstring, HashMap<String, Integer> indexAndValueArray) {
        Detail detail = new Detail();
        detail.setNomenclature(arrayOfSubstring[indexAndValueArray.get(NOMENCLATURE)]);
        detail.setBrand(arrayOfSubstring[indexAndValueArray.get(BRAND)]);
        detail.setArticle(arrayOfSubstring[indexAndValueArray.get(ARTICLE)]);
        //TODO: Добавить остальное
        return detail;
    }

    /**
     * метод принимает массив символов
     * и возвращает массив строк, используя в качестве разделителя
     * сивол ';'
     * при нахождении символа '"' - считается, что все, что между кавычками -
     *  - явяляется одним значением, разделители внутри кавычек игнорируются.
     * @param charsOfLine
     * @return
     */
    public String[] getStringOfLine(char[] charsOfLine) {
        String[] cells = new String[14];
        int cellCount = 0;
        int firstCharToString = 0;
        for (int j = 0; j < charsOfLine.length; j++) {
            //если нашли кавычки - ищем следующие.
            if (charsOfLine[j] == '"') {
                for (int k = (j + 1); k < charsOfLine.length; k++) {
                    if (charsOfLine[k] == '"') {
                        cells[cellCount] = String.valueOf(charsOfLine, j + 1, ((k) - 1 - (j)));
                        cellCount++;
                        j = (k + 1); //следующий символ для чтения следующей ячейки
                        firstCharToString = j + 1;
                        break;
                    } else if (k == charsOfLine.length - 1) {
                        break;
                    }
                }
            } else if (charsOfLine[j] == ';') {
                //деление на строки с помощью стандартного разделителя.
                cells[cellCount] = String.valueOf(charsOfLine, firstCharToString, (j - firstCharToString));
                cellCount++;
                firstCharToString = j + 1;
            } else if (j == (charsOfLine.length - 1)) {
                //если в конце строки нет разделителя
                cells[cellCount] = String.valueOf(charsOfLine, firstCharToString, (j - (firstCharToString - 1)));
            }
        }

        return cells;
    }
}

