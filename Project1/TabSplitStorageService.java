package Project1;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TabSplitStorageService implements StorageService {
    private final String BOOKS_FILE = "books.txt";
    private final String STUDENTS_FILE = "students.txt";
    private final String ASSISTANTS_FILE = "assistants.txt";
    private final String BORROW_REQUESTS_FILE = "borrowRequests.txt";
    private final String RETURN_REQUESTS_FILE = "returnRequests.txt";
    private final String TRANSACTIONS_HISTORY_FILE = "transactions_history.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void saveBooks(ArrayList<Book> books) {
        try {
            new PrintWriter(BOOKS_FILE).close();
            try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
                for (Book book : books) {
                    writer.println(book.getTitle() + "\t" +
                            book.getAuthor() + "\t" +
                            book.getYearOfPublication() + "\t" +
                            book.getPages() + "\t" +
                            book.isBorrowed());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }

    @Override
    public void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.println(student.getFirstName() + "\t" +
                        student.getLastName() + "\t" +
                        student.getId() + "\t" +
                        student.getField() + "\t" +
                        student.getDateOfMembership().format(DATE_FORMATTER));
            }
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
        }
    }

    @Override
    public void saveAssistants(ArrayList<LibraryAssistant> assistants) {
        try {
            new PrintWriter(ASSISTANTS_FILE).close();
            try (PrintWriter writer = new PrintWriter(new FileWriter(ASSISTANTS_FILE))) {
                for (LibraryAssistant assistant : assistants) {
                    writer.println(assistant.getFirstName() + "\t" +
                            assistant.getLastName() + "\t" +
                            assistant.getId());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving assistants to file: " + e.getMessage());
        }
    }

    @Override
    public void saveBorrowRequests(ArrayList<Borrow> borrows) {
        try {
            new PrintWriter(BORROW_REQUESTS_FILE).close();
            try (PrintWriter writer = new PrintWriter(new FileWriter(BORROW_REQUESTS_FILE))) {
                for (Borrow borrow : borrows) {
                    writer.println(borrow.getStudentId() + "\t" +
                            borrow.getBookTitle() + "\t" +
                            (borrow.isApproved() ? borrow.getAssistantId() : "null") + "\t" +
                            (borrow.getBorrowDate() != null ? borrow.getBorrowDate().format(DATE_FORMATTER) : "null") + "\t" +
                            (borrow.getDueDate() != null ? borrow.getDueDate().format(DATE_FORMATTER) : "null") + "\t" +
                            (borrow.getReturnDate() != null ? borrow.getReturnDate().format(DATE_FORMATTER) : "null") + "\t" +
                            borrow.isApproved());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    @Override
    public void saveReturnRequests(ArrayList<Borrow> returnRequests) {
        try {
            new PrintWriter(RETURN_REQUESTS_FILE).close();
            try (PrintWriter writer = new PrintWriter(new FileWriter(RETURN_REQUESTS_FILE))) {
                for (Borrow returns : returnRequests) {
                    writer.println(returns.getStudentId() + "\t" +
                            returns.getBookTitle() + "\t" +
                            (returns.isApproved() ? returns.getAssistantId() : "null") + "\t" +
                            (returns.getBorrowDate() != null ? returns.getBorrowDate().format(DATE_FORMATTER) : "null") + "\t" +
                            (returns.getDueDate() != null ? returns.getDueDate().format(DATE_FORMATTER) : "null") + "\t" +
                            (returns.getReturnDate() != null ? returns.getReturnDate().format(DATE_FORMATTER) : "null") + "\t" +
                            returns.isApproved());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving return requests: " + e.getMessage());
        }
    }

    @Override
    public void saveTransactionToHistory(Borrow transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_HISTORY_FILE, true))) {
            writer.println(transaction.getStudentId() + "\t" +
                    transaction.getBookTitle() + "\t" +
                    transaction.getAssistantId() + "\t" +
                    transaction.getBorrowDate().format(DATE_FORMATTER) + "\t" +
                    transaction.getDueDate().format(DATE_FORMATTER) + "\t" +
                    (transaction.getReturnDate() != null ? transaction.getReturnDate().format(DATE_FORMATTER) : "null") + "\t" +
                    (transaction.getReturnDate() != null ? "RETURNED" : "BORROWED"));
        } catch (IOException e) {
            System.err.println("Error saving transaction history: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
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

    @Override
    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
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
        } catch (IOException | DateTimeParseException e) {
            System.err.println("Error loading students from file: " + e.getMessage());
        }
        return students;
    }

    @Override
    public ArrayList<LibraryAssistant> loadAssistants() {
        ArrayList<LibraryAssistant> assistants = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ASSISTANTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
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

    @Override
    public ArrayList<Borrow> loadBorrowRequests() {
        ArrayList<Borrow> borrows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BORROW_REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    Borrow borrow = new Borrow(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim());

                    if (parts.length >= 6) {
                        if (!parts[2].equals("null")) {
                            borrow.approveBorrowRequest(Integer.parseInt(parts[2].trim()));
                        }

                        try {
                            if (!parts[3].equals("null")) {
                                borrow.setBorrowDate(LocalDate.parse(parts[3].trim(), DATE_FORMATTER));

                            }
                            if (!parts[4].equals("null")) {
                                borrow.setDueDate(LocalDate.parse(parts[4].trim(), DATE_FORMATTER));
                            }
                            if (!parts[5].equals("null")) {
                                borrow.setReturnDate(LocalDate.parse(parts[5].trim(), DATE_FORMATTER));
                            }
                        } catch (DateTimeParseException e) {
                            System.err.println("Error parsing date: " + e.getMessage());
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

    @Override
    public ArrayList<Borrow> loadReturnRequests() {
        ArrayList<Borrow> returns = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RETURN_REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    Borrow returnRequest = new Borrow(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim());

                    if (parts.length > 2 && !parts[2].equals("null")) {
                        returnRequest.approveReturnRequest(Integer.parseInt(parts[2].trim()));
                    }

                    if (parts.length > 3 && !parts[3].equals("null")) {
                        returnRequest.setBorrowDate(LocalDate.parse(parts[3], DATE_FORMATTER));
                    }
                    if (parts.length > 4 && !parts[4].equals("null")) {
                        returnRequest.setDueDate(LocalDate.parse(parts[4], DATE_FORMATTER));
                    }
                    if (parts.length > 5 && !parts[5].equals("null")) {
                        returnRequest.setReturnDate(LocalDate.parse(parts[5], DATE_FORMATTER));
                    }

                    returns.add(returnRequest);
                }
            }
        } catch (IOException | DateTimeParseException e) {
            System.err.println("Error loading return requests: " + e.getMessage());
        }
        return returns;
    }

    @Override
    public ArrayList<Borrow> loadTransactionsHistory() {
        ArrayList<Borrow> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 7) {
                    Borrow transaction = new Borrow(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim()
                    );

                    if (!parts[2].trim().equals("null")) {
                        transaction.approveBorrowRequest(Integer.parseInt(parts[2].trim()));
                    }

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
