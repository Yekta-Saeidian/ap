import java.util.Scanner;

public class MGH {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the mass (kilogram) of the object:");
        double mass = scan.nextDouble();
        System.out.println("enter the height (meter) of the object from the ground:");
        double height = scan.nextDouble();
        final double g = 9.8d;
        double u = mass * height * g;
        System.out.printf("gravitational potential energy = %.2f %n ", u);
    }
}
