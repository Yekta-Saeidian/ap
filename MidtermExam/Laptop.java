package MidtermExam;

public class Laptop {
    private int price;
    private String color;
    private String model;
    private double weight;

    public Laptop(int price, String color, String model, double weight) {
        this.price = price;
        this.color = color;
        this.model = model;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }
}
