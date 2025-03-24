package exercises;

import java.util.Scanner;

public class EX2_PM_1_1 {
    public static void main(String[] args) {
        System.out.println("enter a number: ");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        scan.close();
        for (int i = 0; i < k + 2; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < k; i++) {
            System.out.print("*");
            for (int j = 0; j < k - 2; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
        for (int i = 0; i < k + 2; i++) {
            System.out.print("*");
        }
    }
}