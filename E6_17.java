import java.util.Scanner;

public class E6_17 {
    public static void main(String[] args) {
        System.out.println("enter the side length:");
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == length - 1) {
                for (int j = 0; j < length; j++) {
                    System.out.print("*");
                }
                System.out.print(" ");
                for (int j = 0; j < length; j++) {
                    System.out.print("*");
                }
                System.out.println();
            } else if (i > 0 && i < length - 1) {
                for (int j = 0; j < length; j++) {
                    System.out.print("*");
                }
                System.out.print(" ");
                System.out.print("*");
                for (int j = 0; j < length - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println("*");
            }
        }
    }
}
