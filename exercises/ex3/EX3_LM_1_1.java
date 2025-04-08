package exercises.ex3;

import java.util.Scanner;

public class EX3_LM_1_1 {
    public static void main(String[] args) {
        Book book1 = new Book("Fundamentals of Physics", "Martha Smith", 1200, 2029);
        Book book2 = new Book("Thomas' Calculus", "George B. Thomas Jr.", 3000, 2018);

        Student student1 = new Student("Dina" , "Saeidian" , 403463124 , "computer science");
        Student student2 = new Student("Neda" , "Rashtchi" , 401463120 , "physics");

        System.out.println("book information:");
        System.out.println();
        book1.showBookInformation();
        book2.showBookInformation();

        System.out.println("student information:");
        System.out.println();
        student1.showStudentInformation();
        student2.showStudentInformation();

        book1.setAuthor("David Halliday");
        book2.setYear(2014);
        book2.setPages(1200);

        student1.setFirstName("Yekta");
        student2.setID(403463121);
        student2.setField("computer science");

        System.out.println("edited book information:");
        System.out.println();
        book1.showBookInformation();
        book2.showBookInformation();

        System.out.println("edited student information:");
        System.out.println();
        student1.showStudentInformation();
        student2.showStudentInformation();
    }
}

class Book {
    private String title;
    private String author;
    private int pages;
    private int year;

    public Book(String newTitle, String newAuthor, int newPage, int newYear) {
        this.title = newTitle;
        this.author = newAuthor;
        this.pages = newPage;
        this.year = newYear;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    public void setPages(int newPage) {
        this.pages = newPage;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void showBookInformation() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pages);
        System.out.println("Year: " + year);
        System.out.println("-------------------------");
    }
}

class Student {
   private String firstName;
   private String lastName;
   private int ID;
   private String field;

    public Student (String firstName, String lastName, int ID, String field) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.field = field;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void showStudentInformation() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("ID: " + ID);
        System.out.println("Field: " + field);
        System.out.println("-------------------------");
    }
}
