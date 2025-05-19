package Project1;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<LibraryAssistant> assistants;
    private ArrayList<Borrow> borrows;

    Input input = new Input();
    Menu menu = new Menu();
    FileHandler fileHandler = new FileHandler();

    public Library() {
        this.assistants.add(new LibraryAssistant("shahpoor", "saeidian", 1111));
        this.assistants.add(new LibraryAssistant("azam", "ahangar", 1112));
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.assistants = new ArrayList<>();
        this.borrows = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addLibraryAssistant(Library library, Input input) {
        int option = 0;

        System.out.println("first name:");
        String firstName = input.scanString();

        System.out.println("last name:");
        String lastName = input.scanString();

        System.out.println("ID:");
        int id = input.scanInt();

        ArrayList<LibraryAssistant> currentAssistants = fileHandler.loadAssistants();
        currentAssistants.add(new LibraryAssistant(firstName, lastName, id));

        System.out.println("\n1.add more assistants");
        System.out.println("2.save");
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    addLibraryAssistant(library, input);
                    break;
                case 2:
                    fileHandler.saveAssistants(currentAssistants);
                    System.out.println("new assistant added successfully\n");

                    System.out.println("1.menu");
                    System.out.println("2.exit");
                    option = 0;
                    while (true) {
                        option = input.scanInt();
                        switch (option) {
                            case 1:
                                return true;
                            case 2:
                                return false;
                            default:
                                System.out.println("invalid option");
                                break;
                        }
                    }
                default:
                    System.out.println("invalid option");
                    break;
            }
        }
    }

    public boolean addBook(Library library, Input input, int assistantID) {
        int option = 0;

        System.out.println("book title:");
        String title = input.scanString();

        System.out.println("book author:");
        String author = input.scanString();

        System.out.println("year of publication:");
        int yearOfPublication = input.scanInt();

        System.out.println("number of pages:");
        int pages = input.scanInt();

        ArrayList<Book> currentBooks = fileHandler.loadBooks();
        currentBooks.add(new Book(title, author, yearOfPublication, pages, false));

        System.out.println("\n1.add more books");
        System.out.println("2.save");
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    addBook(library, input, assistantID);
                    break;
                case 2:
                    fileHandler.saveBooks(currentBooks);
                    System.out.println("new book added successfully\n");

                    System.out.println("1.menu");
                    System.out.println("2.exit");
                    option = 0;
                    while (true) {
                        option = input.scanInt();
                        switch (option) {
                            case 1:
                                return true;
                            case 2:
                                return false;
                            default:
                                System.out.println("invalid option");
                                break;
                        }
                    }
                default:
                    System.out.println("invalid option");
                    break;
            }
        }

    }

    public boolean register(Library library, Input input) {

        System.out.println("first name:");
        String firstName = input.scanString();

        System.out.println("last name:");
        String lastName = input.scanString();

        System.out.println("ID:");
        int id = input.scanInt();

        System.out.println("field:");
        String field = input.scanString();

        LocalDate dateOfMembership = LocalDate.now();

        students.add(new Student(firstName, lastName, id, field, dateOfMembership));
        fileHandler.saveStudents(students);
        System.out.println("registered successfully\n");

        System.out.println("1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean searchBook(Library library, Input input, int id) {
        System.out.println("enter the book title: ");
        String bookTitle = input.scanString().toLowerCase();
        boolean found = false;

        for (Book book : fileHandler.loadBooks()) {
            if (book.getTitle().toLowerCase().contains(bookTitle)) {

                System.out.println("book title:" + book.getTitle());
                System.out.println("book author:" + book.getAuthor());
                System.out.println("book year of publication:" + book.getYearOfPublication());
                System.out.println("book pages:" + book.getPages());
                if (book.isBorrowed() == true)
                    System.out.println("status: borrowed");
                else {
                    System.out.println("status: available");
                    System.out.println("\nwould you like to borrow this book? (Y/N)");
                    String answer = input.scanString();

                    if (answer.toLowerCase().equals("y")) {
                        ArrayList<Borrow> requests = fileHandler.loadBorrowRequests();

                        requests.add(new Borrow(id, book.getTitle()));
                        fileHandler.saveBorrowRequests(requests);

                        System.out.println("request submitted successfully.");
                    }
                }

                found = true;
            }
        }

        if (!found) {
            System.out.println("book not found");
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean returnBookRequest(Library library, Input input, int id) {
        ArrayList<Borrow> requests = fileHandler.loadBorrowRequests();
        ArrayList<Book> books = fileHandler.loadBooks();
        ArrayList<Student> students = fileHandler.loadStudents();
        boolean found = false;

        for (Borrow request : requests) {
            if (request.isApproved() == true) {

                if (request.getStudentId() == id) {
                    Book book = findBookByTitle(books, request.getBookTitle());

                    if (book != null) {
                        System.out.println("\nbook title:" + book.getTitle());
                        System.out.println("book author:" + book.getAuthor());
                        System.out.println("book year of publication:" + book.getYearOfPublication());
                        System.out.println("book pages:" + book.getPages());
                        found = true;

                        System.out.println("\nwould you like to return this book? (Y/N)");
                        String answer = input.scanString();

                        if (answer.toLowerCase().equals("y")) {
                            ArrayList<Borrow> returnRequests = fileHandler.loadReturnRequests();

                            returnRequests.add(new Borrow(id, book.getTitle()));
                            fileHandler.saveReturnRequests(returnRequests);

                            System.out.println("request submitted successfully.");
                        }

                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("\nthere is no unreturned book");
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean unreturnedBookList(Library library, Input input, int id) {
        ArrayList<Borrow> requests = fileHandler.loadBorrowRequests();
        ArrayList<Book> books = fileHandler.loadBooks();
        boolean found = false;

        System.out.println("unreturned book list:");
        for (Borrow request : requests) {
            if (request.isApproved() == true) {
                Book book = findBookByTitle(books, request.getBookTitle());

                if (book != null) {
                    System.out.println("\nbook title:" + book.getTitle());
                    System.out.println("book author:" + book.getAuthor());
                    System.out.println("book year of publication:" + book.getYearOfPublication());
                    System.out.println("book pages:" + book.getPages());
                    found = true;
                }

            }
        }

        if (!found) {
            System.out.println("\nthere is no unreturned book");
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean overdueBooksList(Library library, Input input) {
        ArrayList<Borrow> allTransactions = fileHandler.loadTransactionsHistory();
        ArrayList<Book> books = fileHandler.loadBooks();
        ArrayList<Student> students = fileHandler.loadStudents();
        boolean found = false;

        System.out.println("overdue book list:");

        for (Borrow transaction : allTransactions) {
            if (transaction.isApproved() && transaction.getBorrowDate() != null) {
                LocalDate dueDate = transaction.getDueDate();
                LocalDate returnDate = transaction.getReturnDate();
                LocalDate currentDate = LocalDate.now();

                boolean isOverdue = (returnDate != null && returnDate.isAfter(dueDate)) ||
                        (returnDate == null && currentDate.isAfter(dueDate));

                if (isOverdue) {
                    Book book = findBookByTitle(books, transaction.getBookTitle());
                    Student student = findStudentById(students, transaction.getStudentId());

                    if (student != null && book != null) {
                        System.out.println("\nBook Title: " + book.getTitle());
                        System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("Student ID: " + transaction.getStudentId());
                        System.out.println("Borrow Date: " + transaction.getBorrowDate());
                        System.out.println("Due Date: " + dueDate);

                        if (returnDate != null) {
                            System.out.println("Return Date: " + returnDate);
                            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
                            System.out.println("Days Late: " + daysLate);
                            System.out.println("Status: RETURNED (Late)");
                        } else {
                            long daysLate = ChronoUnit.DAYS.between(dueDate, currentDate);
                            System.out.println("Days Late: " + daysLate);
                            System.out.println("Status: NOT RETURNED YET");
                        }

                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("\nthere is no overdue book");
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean showAssistantsReport(Library library, Input input) {
        ArrayList<Borrow> allTransactions = fileHandler.loadTransactionsHistory();
        ArrayList<LibraryAssistant> assistants = fileHandler.loadAssistants();

        System.out.println("\nAssistants Report:");

        for (LibraryAssistant assistant : assistants) {
            int borrowCount = 0;
            int returnCount = 0;

            for (Borrow transaction : allTransactions) {
                if (transaction.getAssistantId() == assistant.getId()) {
                    if (transaction.getReturnDate() == null) {
                        borrowCount++;
                    } else {
                        returnCount++;
                    }
                }
            }

            System.out.println("\nAssistant: " + assistant.getFirstName() + " " + assistant.getLastName());
            System.out.println("ID: " + assistant.getId());
            System.out.println("Borrow Operations: " + borrowCount);
            System.out.println("Return Operations: " + returnCount);
            System.out.println("Total Transactions: " + (borrowCount + returnCount));
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public boolean showMostBorrowedBooks(Library library, Input input) {
        ArrayList<Borrow> transactions = fileHandler.loadTransactionsHistory();
        ArrayList<Book> books = fileHandler.loadBooks();

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        ArrayList<String> bookTitles = new ArrayList<>();
        ArrayList<Integer> borrowCounts = new ArrayList<>();

        for (Borrow transaction : transactions) {
            if (transaction.isApproved() &&
                    transaction.getBorrowDate() != null &&
                    !transaction.getBorrowDate().isBefore(oneYearAgo) &&
                    transaction.getReturnDate() == null) {

                String bookTitle = transaction.getBookTitle();
                boolean found = false;

                for (int i = 0; i < bookTitles.size(); i++) {
                    if (bookTitles.get(i).equals(bookTitle)) {
                        borrowCounts.set(i, borrowCounts.get(i) + 1);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    bookTitles.add(bookTitle);
                    borrowCounts.add(1);
                }
            }
        }

        for (int i = 0; i < borrowCounts.size(); i++) {
            for (int j = i + 1; j < borrowCounts.size(); j++) {
                if (borrowCounts.get(i) < borrowCounts.get(j)) {

                    int tempCount = borrowCounts.get(i);
                    borrowCounts.set(i, borrowCounts.get(j));
                    borrowCounts.set(j, tempCount);

                    String tempTitle = bookTitles.get(i);
                    bookTitles.set(i, bookTitles.get(j));
                    bookTitles.set(j, tempTitle);
                }
            }
        }

        System.out.println("\nTop 10 Most Borrowed Books in Last Year:");

        int count = 0;
        for (int i = 0; i < bookTitles.size() && count < 10; i++) {
            Book book = findBookByTitle(books, bookTitles.get(i));
            if (book != null) {
                System.out.println("\nBook Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Year of Publication: " + book.getYearOfPublication());
                System.out.println("Total Borrows: " + borrowCounts.get(i));
                count++;
            }
        }

        if (count == 0) {
            System.out.println("\nNo borrowing records found in the last year.");
        }

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        int option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public boolean showPendingRequests(Library library, Input input, int assistantId, int option) {
        ArrayList<Borrow> requests = fileHandler.loadBorrowRequests();
        ArrayList<Borrow> returnRequests = fileHandler.loadReturnRequests();
        ArrayList<Book> books = fileHandler.loadBooks();
        ArrayList<Student> students = fileHandler.loadStudents();
        int studentId = 0;
        boolean found = false;

        if (option == 3) {
            System.out.println("\nborrow requests");

            for (Borrow request : requests) {
                if (!request.isApproved()) {
                    Student student = findStudentById(students, request.getStudentId());
                    Book book = findBookByTitle(books, request.getBookTitle());

                    if (student != null && book != null) {
                        System.out.println("student name: " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("student id: " + request.getStudentId());
                        studentId = request.getStudentId();
                        System.out.println("book title: " + book.getTitle());

                        found = true;

                        System.out.println("\ndo you approve? (Y/N)");
                        String answer = input.scanString();

                        if (answer.toLowerCase().equals("y")) {
                            approveRequest(studentId, assistantId, library, input, option);
                        }
                    }
                }
            }
        }

        if (option == 4) {
            System.out.println("\nreturn requests");

            for (Borrow request : returnRequests) {
                if (!request.isApproved()) {
                    Student student = findStudentById(students, request.getStudentId());
                    Book book = findBookByTitle(books, request.getBookTitle());

                    if (student != null && book != null) {
                        System.out.println("student name: " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("student id: " + request.getStudentId());
                        studentId = request.getStudentId();
                        System.out.println("book title: " + book.getTitle());

                        found = true;

                        System.out.println("\ndo you approve? (Y/N)");
                        String answer = input.scanString();

                        if (answer.toLowerCase().equals("y")) {
                            approveRequest(studentId, assistantId, library, input, option);
                        }
                    }
                }
            }
        }

        if (!found)
            System.out.println("request not found or already approved");

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        while (true) {
            int choice = input.scanInt();
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
            }
        }
    }

    private boolean approveRequest(int studentId, int assistantId, Library library, Input input, int option) {
        ArrayList<Borrow> requests = fileHandler.loadBorrowRequests();
        ArrayList<Borrow> returnRequests = fileHandler.loadReturnRequests();
        ArrayList<Book> books = fileHandler.loadBooks();
        boolean found = false;

        if (option == 3) {
            for (Borrow request : requests) {
                if (request.getStudentId() == studentId && !request.isApproved()) {
                    request.approveBorrowRequest(assistantId);

                    for (Book book : books) {
                        if (book.getTitle().equalsIgnoreCase(request.getBookTitle())) {
                            book.setBorrowedTrue();
                            break;
                        }
                    }

                    fileHandler.saveBorrowRequests(requests);
                    fileHandler.saveTransactionToHistory(request);
                    fileHandler.saveBooks(books);
                    System.out.println("your approval has been submitted successfully");

                    found = true;
                }
            }
        }

        if (option == 4) {
            for (Borrow request : returnRequests) {
                if (request.getStudentId() == studentId && !request.isApproved()) {
                    request.approveReturnRequest(assistantId);

                    for (Book book : books) {
                        if (book.getTitle().equalsIgnoreCase(request.getBookTitle())) {
                            book.setBorrowedFalse();
                            break;
                        }
                    }

                    Borrow borrowToRemove = null;
                    for (Borrow borrow : requests) {
                        if (borrow.getStudentId() == studentId &&
                                borrow.getBookTitle().equalsIgnoreCase(request.getBookTitle()) &&
                                borrow.isApproved()) {
                            borrowToRemove = borrow;
                            break;
                        }
                    }
                    if (borrowToRemove != null) {
                        requests.remove(borrowToRemove);
                    }

                    fileHandler.saveBorrowRequests(requests);
                    fileHandler.saveReturnRequests(returnRequests);
                    fileHandler.saveTransactionToHistory(request);
                    fileHandler.saveBooks(books);
                    System.out.println("your approval has been submitted successfully");

                    found = true;
                }
            }
        }

        if (!found)
            System.out.println("request not found or already approved");

        System.out.println("\n1.menu");
        System.out.println("2.exit");
        option = 0;
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("invalid option");
                    break;
            }
        }
    }

    private Student findStudentById(ArrayList<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private Book findBookByTitle(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
