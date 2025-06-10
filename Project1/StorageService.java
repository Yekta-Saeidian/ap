package Project1;

import java.util.ArrayList;

public interface StorageService {

    void saveBooks(ArrayList<Book> books);
    void saveStudents(ArrayList<Student> students);
    void saveAssistants(ArrayList<LibraryAssistant> assistants);
    void saveBorrowRequests(ArrayList<Borrow> borrows);
    void saveReturnRequests(ArrayList<Borrow> returnRequests);
    void saveTransactionToHistory(Borrow transaction);

    ArrayList<Book> loadBooks();
    ArrayList<Student> loadStudents();
    ArrayList<LibraryAssistant> loadAssistants();
    ArrayList<Borrow> loadBorrowRequests();
    ArrayList<Borrow> loadReturnRequests();
    ArrayList<Borrow> loadTransactionsHistory();

    static StorageService create(StorageType type) {
        switch (type) {
            case TABSPLIT:
                return new TabSplitStorageService();
            case JSON:
            case SQLITE:
            default:
                throw new IllegalArgumentException("Unknown storage type: " + type);
        }
    }
}
