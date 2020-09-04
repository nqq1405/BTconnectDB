package SearchLaptopFromReques.models;

import java.util.List;

public class LaptopEntity {
    private String name;
    private String url;
    private String maker;
    private String Type;
    private String ram;
    private String cpu;
    private String ssd;
    private String hdd;
    private float price;
    private String card;
    private String ScreenResolution;
    private float ScreenSize;
    private int sold;

    public LaptopEntity(String name, String url, String maker, String type, String ram, String cpu, String ssd, String hdd, float price, String card, String screenResolution, float screenSize, int sold) {
        this.name = name;
        this.url = url;
        this.maker = maker;
        Type = type;
        this.ram = ram;
        this.cpu = cpu;
        this.ssd = ssd;
        this.hdd = hdd;
        this.price = price;
        this.card = card;
        ScreenResolution = screenResolution;
        ScreenSize = screenSize;
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getScreenResolution() {
        return ScreenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        ScreenResolution = screenResolution;
    }

    public float getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(float screenSize) {
        ScreenSize = screenSize;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
