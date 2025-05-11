package Project1;

import java.util.ArrayList;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<LibraryAssistant> assistants;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.assistants = new ArrayList<>();
    }
}
