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
    }

    public void addBook() {

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

        students.add(new Student(firstName , lastName , id , field, dateOfMembership));
        fileHandler.saveStudents(students);
    }
}
