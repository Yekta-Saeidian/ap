package Project1;

import java.util.ArrayList;
import java.time.LocalDate;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<LibraryAssistant> assistants;

    Input input = new Input();
    FileHandler fileHandler = new FileHandler();

    public  Library() {
        this.assistants.add(new LibraryAssistant("shahpoor", "saeidian", 1111));
        this.assistants.add(new LibraryAssistant("azam", "ahangar", 1112));
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.assistants = new ArrayList<>();
    }

    public void addLibraryAssistant() {

        System.out.println("first name:");
        String firstName = input.scanString();

        System.out.println("last name:");
        String lastName = input.scanString();

        System.out.println("ID:");
        int id = input.scanInt();

        assistants.add(new LibraryAssistant(firstName, lastName, id));
        fileHandler.saveAssistants(assistants);
        System.out.println("new assistant added successfully");
    }

    public void addBook(Input input) {

        System.out.println("book title:");
        String title = input.scanString();

        System.out.println("book author:");
        String author = input.scanString();

        System.out.println("year of publication:");
        int yearOfPublication = input.scanInt();

        System.out.println("number of pages:");
        int pages = input.scanInt();

        books.add(new Book(title, author, yearOfPublication, pages));
        fileHandler.saveBooks(books);
        System.out.println("new book added successfully");
    }

    public void register() {

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
        System.out.println("registered successfully:");
    }
}
