package Project1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SqliteStorageService implements StorageService{
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public SqliteStorageService() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Create tables if they don't exist
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "title TEXT PRIMARY KEY, " +
                    "author TEXT, " +
                    "yearOfPublication INTEGER, " +
                    "pages INTEGER, " +
                    "borrowed BOOLEAN)");

            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "firstName TEXT, " +
                    "lastName TEXT, " +
                    "id INTEGER PRIMARY KEY, " +
                    "field TEXT, " +
                    "dateOfMembership TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS assistants (" +
                    "firstName TEXT, " +
                    "lastName TEXT, " +
                    "id INTEGER PRIMARY KEY)");

            stmt.execute("CREATE TABLE IF NOT EXISTS borrow_requests (" +
                    "studentId INTEGER, " +
                    "bookTitle TEXT, " +
                    "assistantId INTEGER, " +
                    "borrowDate TEXT, " +
                    "dueDate TEXT, " +
                    "returnDate TEXT, " +
                    "approved BOOLEAN)");

            stmt.execute("CREATE TABLE IF NOT EXISTS return_requests (" +
                    "studentId INTEGER, " +
                    "bookTitle TEXT, " +
                    "assistantId INTEGER, " +
                    "borrowDate TEXT, " +
                    "dueDate TEXT, " +
                    "returnDate TEXT, " +
                    "approved BOOLEAN)");

            stmt.execute("CREATE TABLE IF NOT EXISTS transactions_history (" +
                    "studentId INTEGER, " +
                    "bookTitle TEXT, " +
                    "assistantId INTEGER, " +
                    "borrowDate TEXT, " +
                    "dueDate TEXT, " +
                    "returnDate TEXT, " +
                    "status TEXT)");

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    @Override
    public void saveBooks(ArrayList<Book> books) {
        String sql = "REPLACE INTO books(title, author, yearOfPublication, pages, borrowed) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Book book : books) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getYearOfPublication());
                pstmt.setInt(4, book.getPages());
                pstmt.setBoolean(5, book.isBorrowed());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving books to database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT title, author, yearOfPublication, pages, borrowed FROM books";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("yearOfPublication"),
                        rs.getInt("pages"),
                        rs.getBoolean("borrowed")));
            }
        } catch (SQLException e) {
            System.err.println("Error loading books from database: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void saveStudents(ArrayList<Student> students) {
        String sql = "REPLACE INTO students(firstName, lastName, id, field, dateOfMembership) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Student student : students) {
                pstmt.setString(1, student.getFirstName());
                pstmt.setString(2, student.getLastName());
                pstmt.setInt(3, student.getId());
                pstmt.setString(4, student.getField());
                pstmt.setString(5, student.getDateOfMembership().toString());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving students to database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT firstName, lastName, id, field, dateOfMembership FROM students";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("id"),
                        rs.getString("field"),
                        LocalDate.parse(rs.getString("dateOfMembership"))));
            }
        } catch (SQLException e) {
            System.err.println("Error loading students from database: " + e.getMessage());
        }
        return students;
    }

    @Override
    public void saveAssistants(ArrayList<LibraryAssistant> assistants) {
        String sql = "REPLACE INTO assistants(firstName, lastName, id) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (LibraryAssistant assistant : assistants) {
                pstmt.setString(1, assistant.getFirstName());
                pstmt.setString(2, assistant.getLastName());
                pstmt.setInt(3, assistant.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving assistants to database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<LibraryAssistant> loadAssistants() {
        ArrayList<LibraryAssistant> assistants = new ArrayList<>();
        String sql = "SELECT firstName, lastName, id FROM assistants";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                assistants.add(new LibraryAssistant(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.err.println("Error loading assistants from database: " + e.getMessage());
        }
        return assistants;
    }

    @Override
    public void saveBorrowRequests(ArrayList<Borrow> borrows) {
        String sql = "REPLACE INTO borrow_requests(studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, approved) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Borrow borrow : borrows) {
                pstmt.setInt(1, borrow.getStudentId());
                pstmt.setString(2, borrow.getBookTitle());
                pstmt.setObject(3, borrow.getAssistantId(), Types.INTEGER);
                pstmt.setString(4, borrow.getBorrowDate() != null ? borrow.getBorrowDate().toString() : null);
                pstmt.setString(5, borrow.getDueDate() != null ? borrow.getDueDate().toString() : null);
                pstmt.setString(6, borrow.getReturnDate() != null ? borrow.getReturnDate().toString() : null);
                pstmt.setBoolean(7, borrow.isApproved());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving borrow requests to database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Borrow> loadBorrowRequests() {
        ArrayList<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, approved FROM borrow_requests";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("studentId"),
                        rs.getString("bookTitle"));

                if (rs.getObject("assistantId") != null) {
                    borrow.approveBorrowRequest(rs.getInt("assistantId"));
                }

                if (rs.getString("borrowDate") != null) {
                    borrow.setBorrowDate(LocalDate.parse(rs.getString("borrowDate")));
                }
                if (rs.getString("dueDate") != null) {
                    borrow.setDueDate(LocalDate.parse(rs.getString("dueDate")));
                }
                if (rs.getString("returnDate") != null) {
                    borrow.setReturnDate(LocalDate.parse(rs.getString("returnDate")));
                }

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            System.err.println("Error loading borrow requests from database: " + e.getMessage());
        }
        return borrows;
    }

    @Override
    public void saveReturnRequests(ArrayList<Borrow> returnRequests) {
        String sql = "REPLACE INTO return_requests(studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, approved) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Borrow request : returnRequests) {
                pstmt.setInt(1, request.getStudentId());
                pstmt.setString(2, request.getBookTitle());
                pstmt.setObject(3, request.getAssistantId(), Types.INTEGER);
                pstmt.setString(4, request.getBorrowDate() != null ? request.getBorrowDate().toString() : null);
                pstmt.setString(5, request.getDueDate() != null ? request.getDueDate().toString() : null);
                pstmt.setString(6, request.getReturnDate() != null ? request.getReturnDate().toString() : null);
                pstmt.setBoolean(7, request.isApproved());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving return requests to database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Borrow> loadReturnRequests() {
        ArrayList<Borrow> returns = new ArrayList<>();
        String sql = "SELECT studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, approved FROM return_requests";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Borrow returnRequest = new Borrow(
                        rs.getInt("studentId"),
                        rs.getString("bookTitle"));

                if (rs.getObject("assistantId") != null) {
                    returnRequest.approveReturnRequest(rs.getInt("assistantId"));
                }

                if (rs.getString("borrowDate") != null) {
                    returnRequest.setBorrowDate(LocalDate.parse(rs.getString("borrowDate")));
                }
                if (rs.getString("dueDate") != null) {
                    returnRequest.setDueDate(LocalDate.parse(rs.getString("dueDate")));
                }
                if (rs.getString("returnDate") != null) {
                    returnRequest.setReturnDate(LocalDate.parse(rs.getString("returnDate")));
                }

                returns.add(returnRequest);
            }
        } catch (SQLException e) {
            System.err.println("Error loading return requests from database: " + e.getMessage());
        }
        return returns;
    }

    @Override
    public void saveTransactionToHistory(Borrow transaction) {
        String sql = "INSERT INTO transactions_history(studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, status) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transaction.getStudentId());
            pstmt.setString(2, transaction.getBookTitle());
            pstmt.setInt(3, transaction.getAssistantId());
            pstmt.setString(4, transaction.getBorrowDate().toString());
            pstmt.setString(5, transaction.getDueDate().toString());
            pstmt.setString(6, transaction.getReturnDate() != null ? transaction.getReturnDate().toString() : null);
            pstmt.setString(7, transaction.getReturnDate() != null ? "RETURNED" : "BORROWED");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving transaction to history in database: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Borrow> loadTransactionsHistory() {
        ArrayList<Borrow> history = new ArrayList<>();
        String sql = "SELECT studentId, bookTitle, assistantId, borrowDate, dueDate, returnDate, status FROM transactions_history";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Borrow transaction = new Borrow(
                        rs.getInt("studentId"),
                        rs.getString("bookTitle"));

                transaction.approveBorrowRequest(rs.getInt("assistantId"));
                transaction.setBorrowDate(LocalDate.parse(rs.getString("borrowDate")));
                transaction.setDueDate(LocalDate.parse(rs.getString("dueDate")));

                if (rs.getString("returnDate") != null) {
                    transaction.setReturnDate(LocalDate.parse(rs.getString("returnDate")));
                }

                history.add(transaction);
            }
        } catch (SQLException e) {
            System.err.println("Error loading transactions history from database: " + e.getMessage());
        }
        return history;
    }
}
