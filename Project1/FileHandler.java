package Project1;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FileHandler {

    private final String BOOKS_FILE = "books.txt";
    private final String STUDENTS_FILE = "students.txt";
    private final String ASSISTANTS_FILE = "assistants.txt";
    private final String BORROW_REQUESTS_FILE = "borrowRequests.txt";
    private final String RETURN_REQUESTS_FILE = "returnRequests.txt";
    private final String TRANSACTIONS_HISTORY_FILE = "transactions_history.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void saveBooks(ArrayList<Book> books) {
        try {
            new PrintWriter(BOOKS_FILE).close();

            try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
                for (Book book : books) {
                    writer.println(book.getTitle() + "," +
                            book.getAuthor() + "," +
                            book.getYearOfPublication() + "," +
                            book.getPages() + "," +
                            book.isBorrowed());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }

    public void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE, true))) {
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
        try {
            new PrintWriter(ASSISTANTS_FILE).close();

            try (PrintWriter writer = new PrintWriter(new FileWriter(ASSISTANTS_FILE))) {
                for (LibraryAssistant assistant : assistants) {
                    writer.println(assistant.getFirstName() + "," +
                            assistant.getLastName() + "," +
                            assistant.getId());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving assistants to file: " + e.getMessage());
        }
    }

    public void saveBorrowRequests(ArrayList<Borrow> borrows) {
        try {
            new PrintWriter(BORROW_REQUESTS_FILE).close();

            try (PrintWriter writer = new PrintWriter(new FileWriter(BORROW_REQUESTS_FILE))) {
                for (Borrow borrow : borrows) {
                    writer.println(borrow.getStudentId() + "," +
                            borrow.getBookTitle() + "," +
                            (borrow.isApproved() ? borrow.getAssistantId() : "null") + "," +
                            (borrow.getBorrowDate() != null ? borrow.getBorrowDate() : "null") + "," +
                            (borrow.getDueDate() != null ? borrow.getDueDate() : "null") + "," +
                            (borrow.getReturnDate() != null ? borrow.getReturnDate() : "null") + "," +
                            borrow.isApproved());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    public void saveReturnRequests(ArrayList<Borrow> returnRequests) {
        try {
            new PrintWriter(RETURN_REQUESTS_FILE).close();

            try (PrintWriter writer = new PrintWriter(new FileWriter(RETURN_REQUESTS_FILE))) {
                for (Borrow returns : returnRequests) {
                    writer.println(returns.getStudentId() + "," +
                            returns.getBookTitle() + "," +
                            (returns.isApproved() ? returns.getAssistantId() : "null") + "," +
                            (returns.getBorrowDate() != null ? returns.getBorrowDate() : "null") + "," +
                            (returns.getDueDate() != null ? returns.getDueDate() : "null") + "," +
                            (returns.getReturnDate() != null ? returns.getReturnDate() : "null") + "," +
                            returns.isApproved());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    public void saveTransactionToHistory(Borrow transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_HISTORY_FILE, true))) {
            writer.println(transaction.getStudentId() + "," +
                    transaction.getBookTitle() + "," +
                    transaction.getAssistantId() + "," +
                    transaction.getBorrowDate() + "," +
                    transaction.getDueDate() + "," +
                    transaction.getReturnDate() + "," +
                    (transaction.getReturnDate() != null ? "RETURNED" : "BORROWED"));
        } catch (IOException e) {
            System.err.println("Error saving transaction history: " + e.getMessage());
        }
    }

    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    books.add(new Book(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            Integer.parseInt(parts[3].trim()),
                            Boolean.parseBoolean(parts[4].trim())));
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

    public ArrayList<Borrow> loadBorrowRequests() {
        ArrayList<Borrow> borrows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(BORROW_REQUESTS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    Borrow borrow = new Borrow(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim());

                    if (parts.length >= 6) {
                        if (!parts[2].equals("null")) {
                            borrow.approveBorrowRequest(Integer.parseInt(parts[2].trim())); // assistantId
                        }

                        try {
                            if (!parts[3].equals("null")) {
                                borrow.setBorrowDate(LocalDate.parse(parts[3].trim()));
                            }
                            if (!parts[4].equals("null")) {
                                borrow.setDueDate(LocalDate.parse(parts[4].trim()));
                            }
                            if (!parts[5].equals("null")) {
                                borrow.setReturnDate(LocalDate.parse(parts[5].trim()));
                            }
                        } catch (DateTimeParseException e) {
                            System.err.println("Error parsing date" + e.getMessage());
                            continue;
                        }
                    }
                    borrows.add(borrow);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrow requests: " + e.getMessage());
        }
        return borrows;
    }

    public ArrayList<Borrow> loadReturnRequests() {
        ArrayList<Borrow> returnss = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RETURN_REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    Borrow returns = new Borrow(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim());

                    if (parts.length > 2 && !parts[2].equals("null")) {
                        returns.approveReturnRequest(Integer.parseInt(parts[2].trim()));
                    }

                    if (parts.length > 3 && !parts[3].equals("null")) {
                        returns.setBorrowDate(LocalDate.parse(parts[3]));
                    }
                    if (parts.length > 4 && !parts[4].equals("null")) {
                        returns.setDueDate(LocalDate.parse(parts[4]));
                    }
                    if (parts.length > 5 && !parts[5].equals("null")) {
                        returns.setReturnDate(LocalDate.parse(parts[5]));
                    }

                    returnss.add(returns);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrow requests: " + e.getMessage());
        }
        return returnss;
    }

    public ArrayList<Borrow> loadTransactionsHistory() {
        ArrayList<Borrow> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_HISTORY_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    Borrow transaction = new Borrow(
                            Integer.parseInt(parts[0].trim()), // studentId
                            parts[1].trim()                   // bookTitle
                    );

                    // Set assistant ID
                    if (!parts[2].trim().equals("null")) {
                        transaction.approveBorrowRequest(Integer.parseInt(parts[2].trim()));
                    }

                    // Parse dates
                    try {
                        if (!parts[3].trim().equals("null")) {
                            transaction.setBorrowDate(LocalDate.parse(parts[3].trim(), DATE_FORMATTER));
                        }
                        if (!parts[4].trim().equals("null")) {
                            transaction.setDueDate(LocalDate.parse(parts[4].trim(), DATE_FORMATTER));
                        }
                        if (!parts[5].trim().equals("null")) {
                            transaction.setReturnDate(LocalDate.parse(parts[5].trim(), DATE_FORMATTER));
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Error parsing date in transaction history: " + e.getMessage());
                        continue;
                    }

                    history.add(transaction);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions history: " + e.getMessage());
        }
        return history;
    }
}
