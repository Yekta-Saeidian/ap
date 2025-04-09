package exercises.ex1;

import java.util.Scanner;

public class E6_8 {
    public static void main(String[] args) {
        System.out.println("enter a name:");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        scan.close();
        for (int i = 0; i < name.length(); i++) {
            System.out.println(name.charAt(i));
        }
    }
}
