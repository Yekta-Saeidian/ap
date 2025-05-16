package Project1;

import java.util.ArrayList;
import java.time.LocalDate;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<LibraryAssistant> assistants;

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
    }

    public int addLibraryAssistant(Library library, Input input) {
        int option = 0;

        System.out.println("first name:");
        String firstName = input.scanString();

        System.out.println("last name:");
        String lastName = input.scanString();

        System.out.println("ID:");
        int id = input.scanInt();

        assistants.add(new LibraryAssistant(firstName, lastName, id));

        System.out.println("\n1.add more assistants");
        System.out.println("2.save");
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    addLibraryAssistant(library, input);
                    break;
                case 2:
                    fileHandler.saveAssistants(assistants);
                    System.out.println("new assistant added successfully\n");

                    System.out.println("1.menu");
                    System.out.println("2.exit");
                    option = 0;
                    while (true) {
                        option = input.scanInt();
                        switch (option) {
                            case 1:
                                menu.printManagerMenu(library, input);
                                return 1;
                            case 2:
                                return 0;
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

    public int addBook(Library library, Input input) {
        int option = 0;

        System.out.println("book title:");
        String title = input.scanString();

        System.out.println("book author:");
        String author = input.scanString();

        System.out.println("year of publication:");
        int yearOfPublication = input.scanInt();

        System.out.println("number of pages:");
        int pages = input.scanInt();

        books.add(new Book(title, author, yearOfPublication, pages , false));

        System.out.println("\n1.add more books");
        System.out.println("2.save");
        while (true) {
            option = input.scanInt();
            switch (option) {
                case 1:
                    addBook(library, input);
                    break;
                case 2:
                    fileHandler.saveBooks(books);
                    System.out.println("new book added successfully\n");

                    System.out.println("1.menu");
                    System.out.println("2.exit");
                    option = 0;
                    while (true) {
                        option = input.scanInt();
                        switch (option) {
                            case 1:
                                menu.printLibraryAssistantMenu(library, input);
                                return 1;
                            case 2:
                                return 0;
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

    public int register(Library library, Input input) {

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
                    menu.printStudentMenu(library, input);
                    return 1;
                case 2:
                    return 0;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public int searchBook(Library library, Input input) {
        System.out.println("enter the book title::");
        String bookTitle = input.scanString().toLowerCase();
        boolean found = false;

        for (Book book : fileHandler.loadBooks()) {
            if (book.getTitle().toLowerCase().contains(bookTitle)) {
                System.out.println("book title:" + book.getTitle());
                System.out.println("book author:" + book.getAuthor());
                System.out.println("book year of publication:" + book.getYearOfPublication());
                System.out.println("book pages:" + book.getPages());
                if(book.isBorrowed() == false)
                    System.out.println("status: available");
                else {
                    System.out.println("status: borrowed");
                    System.out.println("would you like to borrow this book? (Y/N)");
                    String answer = input.scanString();

                    if (answer.toLowerCase().equals("y")) {
                        int bookIndex = books.indexOf(book);
                        borrowRequest(library, input, bookIndex);
                    }
                    else
                        break;
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
                    menu.printStudentMenu(library, input);
                    return 1;
                case 2:
                    return 0;
                default:
                    System.out.println("invalid option");
                    break;
            }

        }
    }

    public void borrowRequest(Library library, Input input, int bookIndex) {

    }

}
