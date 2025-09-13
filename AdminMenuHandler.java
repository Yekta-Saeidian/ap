package ap.finalProject;

import java.util.Scanner;

public class AdminMenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;

    public AdminMenuHandler(LibrarySystem librarySystem, Scanner scanner) {
        this.librarySystem = librarySystem;
        this.scanner = scanner;
    }

    public void displayAdminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Register New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee Performance");
            System.out.println("4. View Admin Statistics");
            System.out.println("5. View Top Students with Delays");
            System.out.println("6. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    registerNewEmployee();
                    break;
                case 2:
                    librarySystem.displayAllEmployees();
                    break;
                case 3:
                    viewEmployeePerformance();
                    break;
                case 4:
                    librarySystem.displayAdminStatistics();
                    break;
                case 5:
                    librarySystem.displayTopStudentsWithDelays();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void registerNewEmployee() {
        System.out.print("Employee name: ");
        String name = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerEmployee(username, password, name);
    }

    private void viewEmployeePerformance() {
        System.out.print("Enter employee username: ");
        String username = scanner.nextLine();
        librarySystem.displayEmployeePerformance(username);
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}