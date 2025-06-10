package MidtermExam;

import java.util.ArrayList;

public class Store {

    public void printLaptopInfo(ArrayList<Laptop> laptops) {

        for (Laptop laptop : laptops) {
            System.out.println("laptop price: " + laptop.getPrice() + " tooman");
            System.out.println("laptop color: " + laptop.getColor());
            System.out.println("laptop model: " + laptop.getModel());
            System.out.println("laptop weight: " + laptop.getWeight() + " kilograms");
            System.out.println();
        }
    }

    public void printCaseInfo(ArrayList<Case> cases) {

        for (Case c : cases) {
            System.out.println("case price: " + c.getPrice() + " tooman");
            System.out.println("case color: " + c.getColor());

            String isStoke;
            if(c.isStock()==true)
                isStoke = "stock";
            else
                isStoke = "new";

            System.out.println("case is: " + isStoke);
            System.out.println("case size: " + c.getSize());
            System.out.println();
        }
    }
}
