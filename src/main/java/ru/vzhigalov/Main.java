package ru.vzhigalov;

import ru.vzhigalov.csv.CsvReader;
import ru.vzhigalov.dao.Dao;
import ru.vzhigalov.mail.Mail;
import ru.vzhigalov.servise.Config;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws Exception {
        /*Mail mail = new Mail();
        Dao dao = new Dao();
        Config config = new Config();

        config.init();

        try {
            mail.getUnReadMessagesSubjects();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        CsvReader csvReader = new CsvReader();
        String[] filenames = csvReader.getAllFilesInDirectory("src/main/resources/");
        csvReader.readCsvPrices(filenames);
    }
}
