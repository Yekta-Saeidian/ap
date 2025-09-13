package ap.finalProject;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Student Registration");
            System.out.println("4. Student Login");
            System.out.println("5. Guest Access");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleEmployeeLogin();
                    break;
                case 3:
                    handleStudentRegistration();
                    break;
                case 4:
                    handleStudentLogin();
                    break;
                case 5:
                    displayGuestMenu();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleAdminLogin() {
        System.out.print("Admin username: ");
        String username = scanner.nextLine();
        System.out.print("Admin password: ");
        String password = scanner.nextLine();

        if (librarySystem.adminLogin(username, password)) {
            AdminMenuHandler adminMenuHandler = new AdminMenuHandler(librarySystem, scanner);
            adminMenuHandler.displayAdminMenu();
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    private void handleEmployeeLogin() {
        System.out.print("Employee username: ");
        String username = scanner.nextLine();
        System.out.print("Employee password: ");
        String password = scanner.nextLine();

        if (librarySystem.authenticateEmployee(username, password)) {
            EmployeeMenuHandler employeeMenuHandler = new EmployeeMenuHandler(librarySystem, scanner);
            employeeMenuHandler.displayEmployeeMenu();
        } else {
            System.out.println("Invalid employee credentials!");
        }
    }

    private void handleStudentRegistration() {
        System.out.print("Student name: ");
        String name = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.print("Student username: ");
        String username = scanner.nextLine();
        System.out.print("Student password: ");
        String password = scanner.nextLine();

        if (librarySystem.authenticateStudent(username, password)) {
            studentMenu();
        } else {
            System.out.println("Invalid student credentials or account is inactive!");
        }
    }

    private void studentMenu() {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View My Information");
            System.out.println("2. Change Password");
            System.out.println("3. View Available Books");
            System.out.println("4. Search Books");
            System.out.println("5. Request Book Loan");
            System.out.println("6. View My Loans");
            System.out.println("7. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 7);

            switch (choice) {
                case 1:
                    librarySystem.displayStudentInfo();
                    break;
                case 2:
                    librarySystem.changeStudentPassword();
                    break;
                case 3:
                    librarySystem.displayAvailableBooks();
                    break;
                case 4:
                    handleBookSearch();
                    break;
                case 5:
                    handleLoanRequest();
                    break;
                case 6:
                    viewStudentLoans();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleLoanRequest() {
        System.out.println("\n=== Request Book Loan ===");
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        librarySystem.requestLoan(bookTitle, startDate, endDate);
    }

    private void viewStudentLoans() {
        List<Loan> studentLoans = librarySystem.getStudentLoans();
        System.out.println("\n--- My Loan Requests ---");
        if (studentLoans.isEmpty()) {
            System.out.println("No loan requests found.");
        } else {
            for (Loan loan : studentLoans) {
                System.out.println(loan);
            }
        }
    }

    private void handleBookSearch() {
        System.out.println("\n=== Book Search ===");
        System.out.println("Enter search criteria (press Enter to skip any field):");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Year (0 to skip): ");
        int yearInput = getIntInput(0, Integer.MAX_VALUE);
        Integer publicationYear = (yearInput == 0) ? null : yearInput;

        List<Book> results = librarySystem.searchBooks(title, author, publicationYear);
        librarySystem.displaySearchResults(results);
    }

    private void displayGuestMenu() {
        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("1. View Registered Students Count");
            System.out.println("2. Search Books by Title");
            System.out.println("3. View Statistics");
            System.out.println("4. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2:
                    handleGuestBookSearch();
                    break;
                case 3:
                    librarySystem.displayStatistics();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void displayStudentCount() {
        System.out.println("\nTotal Registered Students: " + librarySystem.getStudentManager().getStudentCount());
    }

    private void handleGuestBookSearch() {
        System.out.println("\n=== Book Search (Guest) ===");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        List<Book> results = librarySystem.searchBooks(title, null, null);

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No books found matching your criteria.");
        } else {
            for (Book book : results) {
                System.out.println("Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Year: " + book.getPublicationYear());
            }
            System.out.println("Total found: " + results.size() + " books");
        }
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