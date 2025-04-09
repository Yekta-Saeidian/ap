package exercises.ex1;

import java.util.Scanner;

public class E6_18 {
    public static void main(String[] args) {
        System.out.println("enter the side length:");
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();
        int space = length - 1;
        int star = length - space;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < star; j++) {
                System.out.print("*");
            }
            System.out.println();
            space -= 1;
            star += 2;
        }
        space += 2;
        star -= 4;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < star; j++) {
                System.out.print("*");
            }
            System.out.println();
            space += 1;
            star -= 2;
        }
    }
}
