package Project1;

public class Book {
    private String title;
    private String author;
    private int yearOfPublication;
    private int pages;
    private boolean borrowed;

    public Book(String title, String author, int yearOfPublication, int pages, boolean borrowed) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.pages = pages;
        this.borrowed = borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public int getPages() {
        return pages;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowedTrue() {
        this.borrowed = true;
    }

    public void setBorrowedFalse() {
        this.borrowed = false;
    }
}
