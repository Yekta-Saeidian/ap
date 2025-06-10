package MidtermExam;

public class Case {
    private int price;
    private String color;
    private boolean stock;
    private String size;

    public Case(int price, String color, boolean stock, String size) {
        this.price = price;
        this.color = color;
        this.stock = stock;
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public boolean isStock() {
        return stock;
    }

    public String getSize() {
        return size;
    }
}
