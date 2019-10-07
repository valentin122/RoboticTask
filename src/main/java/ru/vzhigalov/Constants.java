package ru.vzhigalov;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс с константами для хранения наименования столбцов
 */
public class Constants {

    public static final String NOMENCLATURE = "Номенклатура";
    public static final String BRAND = "Бренд";
    public static final String ARTICLE = "Артикул";
    public static final String DESCRIPTION = "Описание";
    public static final String VOLUME = "Вес/Объем";
    public static final String SHIPMENT = "Кратность отгрузки";
    public static final String PRICE = "Цена, руб.";
    public static final String BASEPRICE = "Базовая цена, руб";
    public static final String AVAILABILITY = "Наличие";
    public static final String DELIVERYPERIOD = "Срок поставки, дн.";
    public static final String CATALOGUENUMBER = "Каталожный номер";
    public static final String OEM_NUMBER = "OEМ Номер";
    public static final String APPLICABILITY = "Применимость";
    public static final String VENDORCODE = "Вендор-код";

    public static Map<String, String> fieldsMap;

    static {
        fieldsMap = new HashMap<>();
        fieldsMap.put(NOMENCLATURE, "Номенклатура");
        fieldsMap.put(BRAND, "Бренд");
        fieldsMap.put(ARTICLE, "Артикул");
        fieldsMap.put(DESCRIPTION, "Описание");
        fieldsMap.put(VOLUME, "Вес/Объем");
        fieldsMap.put(SHIPMENT, "Кратность отгрузки");
        fieldsMap.put(PRICE, "Цена, руб.");
        fieldsMap.put(BASEPRICE, "Базовая цена, руб");
        fieldsMap.put(AVAILABILITY, "Наличие");
        fieldsMap.put(DELIVERYPERIOD, "Срок поставки, дн.");
        fieldsMap.put(CATALOGUENUMBER, "Каталожный номер");
        fieldsMap.put(OEM_NUMBER, "OEМ Номер");
        fieldsMap.put(APPLICABILITY, "Применимость");
        fieldsMap.put(VENDORCODE, "Вендор-код");
    }
}
