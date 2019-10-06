package ru.vzhigalov.csv;

public class PriceList {
    private String nomenclature;
    private String brand;
    private String article;
    private String description;
    private double volume;
    private int shipment;
    private double price;
    private double basePrice;
    private int availability;
    private int deliveryPeriod;
    private String catalogueNumber;
    private String OemNumber;
    private String applicability;
    private String vendorCode;

    public PriceList(){
    }

    public PriceList(String nomenclature,
                     String brand,
                     String article,
                     String description,
                     double volume,
                     int shipment,
                     double price,
                     double basePrice,
                     int availability,
                     int deliveryPeriod,
                     String catalogueNumber,
                     String oemNumber,
                     String applicability,
                     String vendorCode) {
        this.nomenclature = nomenclature;
        this.brand = brand;
        this.article = article;
        this.description = description;
        this.volume = volume;
        this.shipment = shipment;
        this.price = price;
        this.basePrice = basePrice;
        this.availability = availability;
        this.deliveryPeriod = deliveryPeriod;
        this.catalogueNumber = catalogueNumber;
        this.OemNumber = oemNumber;
        this.applicability = applicability;
        this.vendorCode = vendorCode;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getShipment() {
        return shipment;
    }

    public void setShipment(int shipment) {
        this.shipment = shipment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(int deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public String getCatalogueNumber() {
        return catalogueNumber;
    }

    public void setCatalogueNumber(String catalogueNumber) {
        this.catalogueNumber = catalogueNumber;
    }

    public String getOemNumber() {
        return OemNumber;
    }

    public void setOemNumber(String oemNumber) {
        OemNumber = oemNumber;
    }

    public String getApplicability() {
        return applicability;
    }

    public void setApplicability(String applicability) {
        this.applicability = applicability;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
}
