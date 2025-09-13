package ap.finalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private EmployeeManager employeeManager;
    private BookManager bookManager;
    private LoanManager loanManager;
    private FileManager fileManager;
    private Scanner scanner;
    private Student currentStudent;
    private Employee currentEmployee;

    public LibrarySystem() {
        this.fileManager = new FileManager();
        this.studentManager = new StudentManager(fileManager);
        this.employeeManager = new EmployeeManager(fileManager);
        this.bookManager = new BookManager(fileManager);
        this.loanManager = new LoanManager(fileManager);
        this.scanner = new Scanner(System.in);
    }

    public boolean adminLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public void registerEmployee(String username, String password, String name) {
        employeeManager.registerEmployee(username, password, name);
    }

    public void displayAllEmployees() {
        employeeManager.displayEmployees();
    }

    public boolean authenticateEmployee(String username, String password) {
        currentEmployee = employeeManager.authenticateEmployee(username, password);
        return currentEmployee != null;
    }

    public void changeEmployeePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        employeeManager.changeEmployeePassword(currentEmployee, newPassword);
    }

    public void registerNewBook() {
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Author name: : ");
        String author = scanner.nextLine();
        System.out.print("Publication year: ");
        int year = Integer.parseInt(scanner.nextLine());
        bookManager.registerBook(title, author, year, currentEmployee.getUsername());
    }

    public void displayAvailableBooks() {
        bookManager.displayAvailableBooks();
    }

    public void displayAllBooks() {
        bookManager.displayAllBooks();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public boolean authenticateStudent(String username, String password) {
        currentStudent = studentManager.authenticateStudent(username, password);
        return currentStudent != null;
    }

    public void displayStudentInfo() {
        System.out.println(currentStudent);
    }

    public void changeStudentPassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentStudent.setPassword(newPassword);
        fileManager.saveStudents(studentManager.getStudents());
        System.out.println("Password changed successfully.");
    }

    public void displayStatistics() {
        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total Students: " + studentManager.getStudentCount());
        System.out.println("Total Books: " + bookManager.getTotalBooks());
        System.out.println("Total Employees: " + employeeManager.getEmployeeCount());
        System.out.println("Available Books: " + bookManager.getAvailableBooks().size());
        System.out.println("Borrowed Books: " + bookManager.getBorrowedBooksCount());
        System.out.println("Total Loans: " + loanManager.getTotalLoans());
        System.out.println("Active Loans: " + loanManager.getActiveLoansCount());
        System.out.println("Average Loan Duration: " + loanManager.getAverageLoanDuration() + " days");
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public List<Book> searchBooks(String title, String author, Integer publicationYear) {
        return bookManager.searchBooks(title, author, publicationYear);
    }

    public void displaySearchResults(List<Book> results) {
        bookManager.displaySearchResults(results);
    }

    public Book findBookByTitle(String title) {
        return bookManager.findBookByTitle(title);
    }

    public List<Book> searchBooksByCriteria(String title, String author, Integer year) {
        return bookManager.searchBooksByCriteria(title, author, year);
    }

    public boolean updateBook(String oldTitle, String newTitle, String newAuthor, Integer newYear) {
        return bookManager.updateBook(oldTitle, newTitle, newAuthor, newYear);
    }

    public void toggleStudentStatus(String username) {
        studentManager.toggleStudentStatus(username);
    }

    public void displayAllStudentsWithStatus() {
        studentManager.displayAllStudentsWithStatus();
    }

    public void requestLoan(String bookTitle, LocalDate startDate, LocalDate endDate) {
        if (currentStudent == null) {
            System.out.println("No student logged in!");
            return;
        }
        loanManager.requestLoan(currentStudent.getUsername(), bookTitle, startDate, endDate);
    }

    public List<Loan> getStudentLoans() {
        if (currentStudent == null) {
            System.out.println("No student logged in!");
            return new ArrayList<>();
        }
        return loanManager.getStudentLoans(currentStudent.getUsername());
    }

    public List<Loan> getPendingLoans() {
        return loanManager.getPendingLoans();
    }

    public List<Loan> getPendingLoansForTodayOrYesterday() {
        return loanManager.getPendingLoansForTodayOrYesterday();
    }

    public void approveLoan(String loanId) {
        loanManager.approveLoan(loanId, currentEmployee.getUsername());
    }

    public void approveLoan(String loanId, LocalDate approvalDate) {
        loanManager.approveLoan(loanId, currentEmployee.getUsername());
    }

    public void returnBook(String loanId) {
        loanManager.returnBook(loanId);
    }

    public Loan getLoanById(String loanId) {
        return loanManager.getLoanById(loanId);
    }

    public void displayEmployeePerformance(String employeeUsername) {
        int booksRegistered = bookManager.getRegisteredBooksCount(employeeUsername);
        int booksLent = loanManager.getBooksLentByEmployee(employeeUsername);
        int booksReturned = loanManager.getBooksReturnedByEmployee(employeeUsername);

        System.out.println("\n--- Employee Performance: " + employeeUsername + " ---");
        System.out.println("Books Registered: " + booksRegistered);
        System.out.println("Books Lent: " + booksLent);
        System.out.println("Books Returned: " + booksReturned);
    }

    public void displayStudentLoanHistory(String studentUsername) {
        List<Loan> studentLoans = loanManager.getStudentLoans(studentUsername);
        int totalLoans = studentLoans.size();
        int notReturnedCount = 0;
        int delayedReturnCount = 0;

        for (Loan loan : studentLoans) {
            if (loan.getStatus().equals("APPROVED") && loan.getReturnDate() == null) {
                notReturnedCount++;
            } else if (loan.getReturnDate() != null && loan.getReturnDate().isAfter(loan.getEndDate())) {
                delayedReturnCount++;
            }
        }

        System.out.println("\n--- Loan History for Student: " + studentUsername + " ---");
        System.out.println("Total Loans: " + totalLoans);
        System.out.println("Not Returned: " + notReturnedCount);
        System.out.println("Delayed Returns: " + delayedReturnCount);

        for (Loan loan : studentLoans) {
            System.out.println(loan);
        }
    }

    public void displayAdminStatistics() {
        System.out.println("\n=== Admin Statistics ===");
        System.out.println("Total Loan Requests: " + loanManager.getTotalLoans());
        System.out.println("Total Approved Loans: " + loanManager.getActiveLoansCount());
        System.out.println("Average Loan Duration: " + loanManager.getAverageLoanDuration() + " days");
    }

    public void displayTopStudentsWithDelays() {
        List<String> topStudents = loanManager.getTopStudentsWithDelays(10);
        System.out.println("\n--- Top 10 Students with Most Delays ---");
        for (int i = 0; i < topStudents.size(); i++) {
            String[] parts = topStudents.get(i).split(":");
            System.out.println((i + 1) + ". " + parts[0] + " - " + parts[1] + " delays");
        }
    }

    public void start() {
        MenuHandler menuHandler = new MenuHandler(this);
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}