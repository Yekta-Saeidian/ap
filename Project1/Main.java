package Project1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Library library = new Library("The Eternal Bookshelf");
        Menu menu = new Menu();

        System.out.println("User Type:\n");
        System.out.println("1.Manager");
        System.out.println("2.Student");

        int option = 0;

        while (option!=1 || option!=2) {

            option = scan.nextInt();
            switch (option) {
                case 1:
                    menu.printManagerMenu();
                    break;
                case 2:
                    menu.printStudentMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
