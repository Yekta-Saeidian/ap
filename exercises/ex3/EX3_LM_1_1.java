package exercises.ex3;

import java.util.Scanner;

public class EX3_LM_1_1 {
    public static void main(String[] args) {
        Book book1 = new Book("Fundamentals of Physics", "Martha Smith", 1200, 2029);
        Book book2 = new Book("Thomas' Calculus", "George B. Thomas Jr.", 3000, 2018);

        System.out.println("book information:");
        System.out.println();
        book1.showBookInformation();
        book2.showBookInformation();

        book1.setAuthor("David Halliday");
        book2.setYear(2014);
        book2.setPages(1200);

        System.out.println("edited book information:");
        System.out.println();
        book1.showBookInformation();
        book2.showBookInformation();
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
