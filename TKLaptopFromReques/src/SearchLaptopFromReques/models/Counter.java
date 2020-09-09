package SearchLaptopFromReques.models;

public class Counter {
    private String maker;
    private String quantity;

    public Counter(String maker, String quantity) {
        this.maker = maker;
        this.quantity = quantity;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "maker='" + maker + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
