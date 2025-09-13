package ap.finalProject;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;

    public EmployeeMenuHandler(LibrarySystem librarySystem, Scanner scanner) {
        this.librarySystem = librarySystem;
        this.scanner = scanner;
    }

    public void displayEmployeeMenu() {
        while (true) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. Change Password");
            System.out.println("2. Register New Book");
            System.out.println("3. View Available Books");
            System.out.println("4. Search and Edit Books");
            System.out.println("5. View All Books");
            System.out.println("6. Manage Students");
            System.out.println("7. Manage Loan Requests");
            System.out.println("8. View Student Loan History");
            System.out.println("9. Return Book");
            System.out.println("10. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 10);

            switch (choice) {
                case 1:
                    librarySystem.changeEmployeePassword();
                    break;
                case 2:
                    librarySystem.registerNewBook();
                    break;
                case 3:
                    librarySystem.displayAvailableBooks();
                    break;
                case 4:
                    handleBookSearchAndEdit();
                    break;
                case 5:
                    librarySystem.displayAllBooks();
                    break;
                case 6:
                    handleStudentManagement();
                    break;
                case 7:
                    handleLoanManagement();
                    break;
                case 8:
                    viewStudentLoanHistory();
                    break;
                case 9:
                    handleBookReturn();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleLoanManagement() {
        while (true) {
            System.out.println("\n=== Loan Management ===");
            System.out.println("1. View Pending Loan Requests");
            System.out.println("2. Approve Loan Request");
            System.out.println("3. Back to Employee Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    viewPendingLoans();
                    break;
                case 2:
                    approveLoan();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void viewPendingLoans() {
        List<Loan> pendingLoans = librarySystem.getPendingLoansForTodayOrYesterday();
        System.out.println("\n--- Pending Loan Requests (Today/Yesterday) ---");
        if (pendingLoans.isEmpty()) {
            System.out.println("No pending loan requests for today or yesterday.");
        } else {
            for (Loan loan : pendingLoans) {
                System.out.println(loan);
            }
        }
    }

    private void approveLoan() {
        System.out.print("Enter Loan ID to approve: ");
        String loanId = scanner.nextLine();
        librarySystem.approveLoan(loanId);
    }

    private void handleStudentManagement() {
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. View All Students");
            System.out.println("2. Toggle Student Status");
            System.out.println("3. Back to Employee Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    librarySystem.displayAllStudentsWithStatus();
                    break;
                case 2:
                    toggleStudentStatus();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void toggleStudentStatus() {
        System.out.print("Enter student username to toggle status: ");
        String username = scanner.nextLine();
        librarySystem.toggleStudentStatus(username);
    }

    private void handleBookSearchAndEdit() {
        while (true) {
            System.out.println("\n=== Book Search and Edit ===");
            System.out.println("1. Search Books");
            System.out.println("2. Edit Book Information");
            System.out.println("3. Back to Employee Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    handleEmployeeBookSearch();
                    break;
                case 2:
                    handleBookEdit();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleEmployeeBookSearch() {
        System.out.println("\n=== Book Search (Employee) ===");
        System.out.println("Enter search criteria (press Enter to skip any field):");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Year (0 to skip): ");
        int yearInput = getIntInput(0, Integer.MAX_VALUE);
        Integer publicationYear = (yearInput == 0) ? null : yearInput;

        List<Book> results = librarySystem.searchBooksByCriteria(title, author, publicationYear);

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No books found matching your criteria.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
            System.out.println("Total found: " + results.size() + " books");
        }
    }

    private void handleBookEdit() {
        System.out.println("\n=== Edit Book Information ===");
        System.out.print("Enter the title of the book you want to edit: ");
        String oldTitle = scanner.nextLine();

        Book book = librarySystem.findBookByTitle(oldTitle);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Current book information:");
        System.out.println(book);

        System.out.println("\nEnter new information (press Enter to keep current value):");

        System.out.print("New Title: ");
        String newTitle = scanner.nextLine();
        if (newTitle.isEmpty()) newTitle = null;

        System.out.print("New Author: ");
        String newAuthor = scanner.nextLine();
        if (newAuthor.isEmpty()) newAuthor = null;

        System.out.print("New Publication Year (0 to keep current): ");
        int newYearInput = getIntInput(0, Integer.MAX_VALUE);
        Integer newYear = (newYearInput == 0) ? null : newYearInput;

        boolean success = librarySystem.updateBook(oldTitle, newTitle, newAuthor, newYear);
        if (success) {
            System.out.println("Book information updated successfully!");
        } else {
            System.out.println("Failed to update book information.");
        }
    }

    private void viewStudentLoanHistory() {
        System.out.print("Enter student username: ");
        String username = scanner.nextLine();
        librarySystem.displayStudentLoanHistory(username);
    }

    private void handleBookReturn() {
        System.out.print("Enter Loan ID for book return: ");
        String loanId = scanner.nextLine();
        librarySystem.returnBook(loanId);
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
