package Project1;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private final String BOOKS_FILE = "books.txt";
    private final String STUDENTS_FILE = "students.txt";
    private final String ASSISTANTS_FILE = "assistants.txt";

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
}
