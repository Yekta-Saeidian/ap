import java.util.Scanner;

public class E5_2 {
    public static void main(String[] args) {
        System.out.println("enter a number:");
        Scanner number = new Scanner(System.in);
        double num = number.nextDouble();
        if (num == 0)
            System.out.println("zero");
        else if (num > 0)
            System.out.println("positive");
        else if (num < 0)
            System.out.println("negative");
        if (Math.abs(num) < 1)
            System.out.println("small");
        else if (Math.abs(num) > 1000000)
            System.out.println("large");
    }
}
