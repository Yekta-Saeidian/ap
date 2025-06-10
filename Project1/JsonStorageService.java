package Project1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonStorageService implements StorageService {
    private final String BOOKS_FILE = "books.json";
    private final String STUDENTS_FILE = "students.json";
    private final String ASSISTANTS_FILE = "assistants.json";
    private final String BORROW_REQUESTS_FILE = "borrowRequests.json";
    private final String RETURN_REQUESTS_FILE = "returnRequests.json";
    private final String TRANSACTIONS_HISTORY_FILE = "transactions_history.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @Override
    public void saveBooks(ArrayList<Book> books) {
        try (Writer writer = new FileWriter(BOOKS_FILE)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.err.println("Error saving books to JSON file: " + e.getMessage());
        }
    }

    @Override
    public void saveStudents(ArrayList<Student> students) {
        try (Writer writer = new FileWriter(STUDENTS_FILE)) {
            gson.toJson(students, writer);
        } catch (IOException e) {
            System.err.println("Error saving students to JSON file: " + e.getMessage());
        }
    }

    @Override
    public void saveAssistants(ArrayList<LibraryAssistant> assistants) {
        try (Writer writer = new FileWriter(ASSISTANTS_FILE)) {
            gson.toJson(assistants, writer);
        } catch (IOException e) {
            System.err.println("Error saving assistants to JSON file: " + e.getMessage());
        }
    }

    @Override
    public void saveBorrowRequests(ArrayList<Borrow> borrows) {
        try (Writer writer = new FileWriter(BORROW_REQUESTS_FILE)) {
            gson.toJson(borrows, writer);
        } catch (IOException e) {
            System.err.println("Error saving borrow requests to JSON file: " + e.getMessage());
        }
    }

    @Override
    public void saveReturnRequests(ArrayList<Borrow> returnRequests) {
        try (Writer writer = new FileWriter(RETURN_REQUESTS_FILE)) {
            gson.toJson(returnRequests, writer);
        } catch (IOException e) {
            System.err.println("Error saving return requests to JSON file: " + e.getMessage());
        }
    }

    @Override
    public void saveTransactionToHistory(Borrow transaction) {
        ArrayList<Borrow> history = loadTransactionsHistory();
        history.add(transaction);
        try (Writer writer = new FileWriter(TRANSACTIONS_HISTORY_FILE)) {
            gson.toJson(history, writer);
        } catch (IOException e) {
            System.err.println("Error saving transaction history to JSON file: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> loadBooks() {
        try (Reader reader = new FileReader(BOOKS_FILE)) {
            Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
            return gson.fromJson(reader, bookListType);
        } catch (IOException e) {
            System.err.println("Error loading books from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Student> loadStudents() {
        try (Reader reader = new FileReader(STUDENTS_FILE)) {
            Type studentListType = new TypeToken<ArrayList<Student>>(){}.getType();
            return gson.fromJson(reader, studentListType);
        } catch (IOException e) {
            System.err.println("Error loading students from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<LibraryAssistant> loadAssistants() {
        try (Reader reader = new FileReader(ASSISTANTS_FILE)) {
            Type assistantListType = new TypeToken<ArrayList<LibraryAssistant>>(){}.getType();
            return gson.fromJson(reader, assistantListType);
        } catch (IOException e) {
            System.err.println("Error loading assistants from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Borrow> loadBorrowRequests() {
        try (Reader reader = new FileReader(BORROW_REQUESTS_FILE)) {
            Type borrowListType = new TypeToken<ArrayList<Borrow>>(){}.getType();
            return gson.fromJson(reader, borrowListType);
        } catch (IOException e) {
            System.err.println("Error loading borrow requests from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Borrow> loadReturnRequests() {
        try (Reader reader = new FileReader(RETURN_REQUESTS_FILE)) {
            Type returnListType = new TypeToken<ArrayList<Borrow>>(){}.getType();
            return gson.fromJson(reader, returnListType);
        } catch (IOException e) {
            System.err.println("Error loading return requests from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Borrow> loadTransactionsHistory() {
        try (Reader reader = new FileReader(TRANSACTIONS_HISTORY_FILE)) {
            Type historyListType = new TypeToken<ArrayList<Borrow>>(){}.getType();
            return gson.fromJson(reader, historyListType);
        } catch (IOException e) {
            System.err.println("Error loading transaction history from JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
