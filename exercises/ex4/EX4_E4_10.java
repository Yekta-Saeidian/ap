package exercises.ex4;

import java.util.ArrayList;
import java.util.List;

public class EX4_E4_10 {
    public static void main(String[] args) {
        CashRegister register = new CashRegister();

        register.addItem(3.99);
        register.addItem(6.76);
        register.addItem(8.23);

        register.printReceipt();
    }
}

class CashRegister {
    private List<Double>purchasedItems;

    public CashRegister() {
        purchasedItems = new ArrayList<>();
    }

    public void addItem(double price) {
        purchasedItems.add(price);
    }

    public double getTotal() {
        double total = 0;
        for (double price : purchasedItems) {
            total += price;
        }
        return total;
    }

    public void printReceipt() {
        String receipt = "Receipt:\n";

        for (double price : purchasedItems) {
            receipt = receipt.concat("Item: $" + String.valueOf(price) + "\n");
        }

        receipt = receipt.concat("----------------\n");
        receipt = receipt.concat("Total: $" + String.valueOf(getTotal()));

        System.out.println(receipt);
    }
}
