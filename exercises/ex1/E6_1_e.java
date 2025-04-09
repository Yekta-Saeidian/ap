package exercises.ex1;

import java.util.Scanner;

public class E6_1_e {
    public static void main(String[] args) {
        System.out.println("enter a number:");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int residual = 0;
        int sum = 0;
        while (number > 0) {
            residual = number % 10;
            if (residual % 2 != 0)
                sum += residual;
            number /= 10;
        }
        System.out.println(sum);
    }
}
