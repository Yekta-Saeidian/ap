package MidtermExam;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        ArrayList<Laptop> laptops = new ArrayList<>();
        ArrayList<Case> cases = new ArrayList<>();

        laptops.add(new Laptop(60000000 , "black" , "Asus" , 3));
        laptops.add(new Laptop(80000000 , "red" , "Asus" , 4.5));

        cases.add(new Case(10000000 , "red" , true , "small"));
        cases.add(new Case(25000000 , "black" , false , "medium"));

        System.out.println("\nproducts:\n");
        store.printLaptopInfo(laptops);
        store.printCaseInfo(cases);
    }
}
