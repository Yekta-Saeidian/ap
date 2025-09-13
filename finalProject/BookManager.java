package ap.finalProject;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books;
    private FileManager fileManager;

    public BookManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.books = fileManager.loadBooks();
    }

    public void registerBook(String title, String author, int publicationYear, String registeredBy) {
        Book newBook = new Book(title, author, publicationYear, registeredBy);
        books.add(newBook);
        fileManager.saveBooks(books);
        System.out.println("Book registered successfully.");
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = getAvailableBooks();
        System.out.println("\n--- Available Books ---");
        if (availableBooks.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : availableBooks) {
                book.displayInfo();
            }
        }
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getBorrowedBooksCount() {
        int count = 0;
        for (Book book : books) {
            if (!book.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public List<Book> searchBooks(String title, String author, Integer publicationYear) {
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            boolean matches = true;

            if (title != null && !title.trim().isEmpty()) {
                if (!book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    matches = false;
                }
            }

            if (matches && author != null && !author.trim().isEmpty()) {
                if (!book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                    matches = false;
                }
            }

            if (matches && publicationYear != null) {
                if (book.getPublicationYear() != publicationYear) {
                    matches = false;
                }
            }

            if (matches) {
                results.add(book);
            }
        }

        return results;
    }

    public void displaySearchResults(List<Book> results) {
        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No books found matching your criteria.");
        } else {
            for (Book book : results) {
                book.displayInfo();
            }
            System.out.println("Total found: " + results.size() + " books");
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBook(String oldTitle, String newTitle, String newAuthor, Integer newYear) {
        Book book = findBookByTitle(oldTitle);
        if (book != null) {
            if (newTitle != null && !newTitle.isEmpty()) {
                Book existingBook = findBookByTitle(newTitle);
                if (existingBook != null && !existingBook.getTitle().equalsIgnoreCase(oldTitle)) {
                    System.out.println("A book with this title already exists!");
                    return false;
                }
                book.title = newTitle;
            }

            if (newAuthor != null && !newAuthor.isEmpty()) {
                book.author = newAuthor;
            }

            if (newYear != null) {
                book.publicationYear = newYear;
            }

            fileManager.saveBooks(books);
            return true;
        }
        return false;
    }

    public void displayAllBooks() {
        System.out.println("\n--- All Books ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                book.displayInfo();
            }
        }
    }

    public int getRegisteredBooksCount(String employeeUsername) {
        int count = 0;
        for (Book book : books) {
            if (book.getRegisteredBy().equals(employeeUsername)) {
                count++;
            }
        }
        return count;
    }
}