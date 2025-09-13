package ap.finalProject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String studentFile = "students.txt";
    private String employeeFile = "employees.txt";
    private String bookFile = "books.txt";
    private String loanFile = "loans.txt";

    public void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(studentFile))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getStudentId() + "," + student.getUsername() + "," + student.getPassword() + "," + student.isActive());
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
                    student.setActive(Boolean.parseBoolean(parts[4]));
                    students.add(student);
                }
            }
        } catch (IOException e) {
        }
        return students;
    }

    public void saveEmployees(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(employeeFile))) {
            for (Employee employee : employees) {
                writer.println(employee.getUsername() + "," + employee.getPassword() + "," + employee.getName());
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    employees.add(new Employee(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
        }
        return employees;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(bookFile))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.getPublicationYear() + "," + book.isAvailable() + "," + book.getRegisteredBy());
            }
        } catch (IOException e) {
            System.out.println("Error saving book data: " + e.getMessage());
        }
    }

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(bookFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), parts[4]);
                    book.setAvailable(Boolean.parseBoolean(parts[3]));
                    books.add(book);
                }
            }
        } catch (IOException e) {
        }
        return books;
    }

    public void saveLoans(List<Loan> loans) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(loanFile))) {
            for (Loan loan : loans) {
                writer.println(loan.getLoanId() + "," + loan.getStudentUsername() + "," + loan.getBookTitle() + "," + loan.getStartDate() + "," + loan.getEndDate() + "," + loan.getReturnDate() + "," + loan.getStatus() + "," + loan.getApprovedBy());
            }
        } catch (IOException e) {
            System.out.println("Error saving loan data: " + e.getMessage());
        }
    }

    public List<Loan> loadLoans() {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(loanFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    Loan loan = new Loan(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]), parts[7]);
                    if (!parts[5].equals("null")) {
                        loan.setReturnDate(LocalDate.parse(parts[5]));
                    }
                    loan.setStatus(parts[6]);
                    loans.add(loan);
                }
            }
        } catch (IOException e) {
        }
        return loans;
    }
}