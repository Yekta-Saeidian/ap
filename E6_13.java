import java.util.Scanner;

public class E6_13 {
    public static void main(String[] args) {
        System.out.println("enter a number:");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        while (number > 0) {
            System.out.println(number % 2);
            number = number / 2;
        }
    }
}
