package SearchLaptopFromReques.models;

public class Statistic {
    private String maker;
    private String sold;
    private double totalmony;

    public Statistic(String maker, String sold, double totalmony) {
        this.maker = maker;
        this.sold = sold;
        this.totalmony = totalmony;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "maker='" + maker + '\'' +
                ", sold='" + sold + '\'' +
                ", totalmony=" + totalmony +
                '}';
    }
}
