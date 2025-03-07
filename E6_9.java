import java.util.Scanner;

public class E6_9 {
    public static void main(String[] args) {
        System.out.println("enter a name:");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        scan.close();
        for (int i = name.length()-1 ; i >= 0; i--) {
            System.out.println(name.charAt(i));
        }
    }
}
