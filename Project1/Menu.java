package Project1;

import java.util.Scanner;

public class Menu {
    Scanner scan = new Scanner(System.in);
    int option;

    public void printManagerMenu() {

        System.out.println("1.overdue books list");
        System.out.println("2.assistant's report");
        System.out.println("3.most borrowed books list");
        System.out.println("4.add new book");

        managerOption();
    }

    public void printStudentMenu() {

        System.out.println("1.register");
        System.out.println("2.search book");
        System.out.println("3.borrow book");
        System.out.println("4.return book");

        studentOption();
    }

    public void managerOption() {
        option = scan.nextInt();

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public void studentOption() {
        option = scan.nextInt();

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }
}
