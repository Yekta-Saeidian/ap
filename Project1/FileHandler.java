package Project1;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileHandler {

    private final String BOOKS_FILE = "books.txt";
    private final String STUDENTS_FILE = "students.txt";
    private final String ASSISTANTS_FILE = "assistants.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void saveBooks(ArrayList<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getYearOfPublication() + "," +
                        book.getPages());
            }
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }

    public void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.println(student.getFirstName() + "," +
                        student.getLastName() + "," +
                        student.getId() + "," +
                        student.getField() + "," +
                        student.getDateOfMembership());
            }
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
        }
    }

    public void saveAssistants(ArrayList<LibraryAssistant> assistants) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ASSISTANTS_FILE))) {
            for (LibraryAssistant assistant : assistants) {
                writer.println(assistant.getFirstName() + "," +
                        assistant.getLastName() + "," +
                        assistant.getId());
            }
        } catch (IOException e) {
            System.err.println("Error saving assistants to file: " + e.getMessage());
        }
    }

    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    books.add(new Book(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            Integer.parseInt(parts[3].trim())
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading books from file: " + e.getMessage());
        }
        return books;
    }

    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    students.add(new Student(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim(),
                            LocalDate.parse(parts[4].trim(), DATE_FORMATTER)
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students from file: " + e.getMessage());
        }
        return students;
    }

    public ArrayList<LibraryAssistant> loadAssistants() {
        ArrayList<LibraryAssistant> assistants = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ASSISTANTS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    assistants.add(new LibraryAssistant(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()))
                    );
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading assistants from file: " + e.getMessage());
        }
        return assistants;
    }
}
