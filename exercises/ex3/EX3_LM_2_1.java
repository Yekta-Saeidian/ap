package exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EX3_LM_2_1 {
    public static void main(String[] args) {

        Books[] book = new Books[4];
        book[0] = new Books("Fundamentals of Physics", "Martha Smith", 1200, 2029, false, -1);
        book[1] = new Books("Thomas Calculus ", "George B. Thomas Jr.", 1200, 2018, false, -1);
        book[2] = new Books("The elegant universe", "Brian Greene", 448, 1999, false, -1);
        book[3] = new Books("A brief history of time", "Stephen Hawking", 256, 1988, false, -1);

        Students[] student = new Students[3];
        student[0] = new Students("Yekta", "Saeidian", 403463124, "computer science");
        student[1] = new Students("Neda", "Rashtchi", 403463121, "computer science");
        student[2] = new Students("Shahpoor", "Saeidian", 401463130, "physics");

        signIn(student, book);
        readDataFromFile();
    }

    public static void signIn(Students[] student, Books[] book) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String studentFirstName = scan.nextLine();
        System.out.println("Please enter your last name");
        String studentLastName = scan.nextLine();
        System.out.println("Please enter your student ID");
        int studentID = scan.nextInt();
        boolean found = false;

        for (int i = 0; i < student.length; i++) {
            if (student[i].getFirstname().toLowerCase().contains(studentFirstName)) {
                if (student[i].getLastname().toLowerCase().contains(studentLastName)) {
                    if (student[i].getId() == studentID) {
                        found = true;
                        borrowBook(student, i, book);
                    }
                }
            }
        }
        if (!found)
            System.out.println("Student not found!");
    }

    public static void borrowBook(Students[] student, int i, Books[] book) {
        System.out.println("Choose the book you want to borrow:");
        System.out.println("1.Fundamentals of Physics - author: David Halliday - pages: 1200 - published: 2014");
        System.out.println("2. Thomas Calculus - George B. Thomas Jr. - pages: 1200 - published: 2018");
        System.out.println("3.The elegant universe - author: Brian Greene - pages:448 - published: 1999");
        System.out.println("4.A brief history of time - author: Stephen Hawking - pages: 256 - published: 1988");

        Scanner scan = new Scanner(System.in);
        int response = scan.nextInt();
        switch (response) {
            case 1:
                if (book[0].isBorrowed() == false) {
                    book[0].borrowed = true;
                    book[0].person = i;
                } else
                    System.out.println("This book is already borrowed!");
                break;
            case 2:
                if (book[1].isBorrowed() == false) {
                    book[1].borrowed = true;
                    book[1].person = i;
                } else
                    System.out.println("This book is already borrowed!");
                break;
            case 3:
                if (book[2].isBorrowed() == false) {
                    book[2].borrowed = true;
                    book[2].person = i;
                } else
                    System.out.println("This book is already borrowed!");
                break;
            case 4:
                if (book[3].isBorrowed() == false) {
                    book[3].borrowed = true;
                    book[3].person = i;
                } else
                    System.out.println("This book is already borrowed!");
                break;
            default:
                System.out.println("invalid selection");
                break;
        }

        saveDataInFile(book, student);
    }

    public static void saveDataInFile(Books[] books, Students[] students) {
        try {
            FileWriter libraryWriter = new FileWriter("library.txt");

            for (int j = 0; j < books.length; j++) {
                libraryWriter.write(String.format("%s|%s|%d|%d|%b|%d%n",
                        books[j].getTitle(),
                        books[j].getAuthor(),
                        books[j].getPages(),
                        books[j].getYear(),
                        books[j].isBorrowed(),
                        books[j].getPerson()));
            }

            for (int j = 0; j < students.length; j++) {
                libraryWriter.write(String.format("%s|%s|%d|%s%n",
                        students[j].getFirstname(),
                        students[j].getLastname(),
                        students[j].getId(),
                        students[j].getField()));
            }

            libraryWriter.close();

            System.out.println("file saved successfully!");

        } catch (IOException e) {
            System.out.println("Error: file did not save!" + e.getMessage());
        }
    }

    public static void readDataFromFile() {
        Books[] books = new Books[4];
        Students[] students = new Students[3];

        try {
            BufferedReader libraryReader = new BufferedReader(new FileReader("library.txt"));
            String line;
            int bookIndex = 0;
            int studentIndex = 0;

            while ((line = libraryReader.readLine()) != null && bookIndex < books.length) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    books[bookIndex++] = new Books(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Boolean.parseBoolean(parts[4]),
                            Integer.parseInt(parts[5])
                    );
                }
            }

            while ((line = libraryReader.readLine()) != null && studentIndex < students.length) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    students[studentIndex++] = new Students(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            parts[3]
                    );
                }
            }

            libraryReader.close();

            System.out.println();
            System.out.println("books: ");
            for (int j = 0; j < books.length; j++) {
                System.out.println("title: " +books[j].getTitle());
                System.out.println("author: " +books[j].getAuthor());
                System.out.println("pages: " +books[j].getPages());
                System.out.println("year of publish: " +books[j].getYear());
                if (books[j].isBorrowed() == true) {
                    System.out.println();
                    System.out.println("borrowed by:");
                    int i = books[j].person;
                    System.out.println(students[i].getFirstname() + " " + students[i].getLastname());
                    System.out.println("ID: " + students[i].getId());
                    System.out.println("Field: " + students[i].getField());
                }
                System.out.println("---------------");
            }
        } catch (IOException e) {
            System.out.println("Error: file did not load!" + e.getMessage());
        }
    }
}

class Books {
    private String title;
    private String author;
    private int pages;
    private int year;
    boolean borrowed;
    public int person;

    public Books(String newTitle, String newAuthor, int newPage, int newYear, boolean newBorrowed, int newPerson) {
        this.title = newTitle;
        this.author = newAuthor;
        this.pages = newPage;
        this.year = newYear;
        this.borrowed = newBorrowed;
        this.person = newPerson;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public int getPerson() {
        return person;
    }
}

class Students {
    private String firstName;
    private String lastName;
    private int ID;
    private String field;

    public Students(String firstName, String lastName, int ID, String field) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.field = field;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public int getId() {
        return ID;
    }

    public String getField() {
        return field;
    }
}
