package ru.vzhigalov;

import ru.vzhigalov.dao.Dao;
import ru.vzhigalov.mail.Mail;
import ru.vzhigalov.servise.Config;

import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException, javax.mail.MessagingException {
        Mail mail = new Mail();
        Dao dao = new Dao();
        Config config = new Config();

        config.init();

        mail.getUnReadMessagesSubjects();
    }
}
