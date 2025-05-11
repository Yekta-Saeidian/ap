package Project1;

public class Book {
    private String title;
    private String author;
    private int yearOfPublication;
    private int pages;

    public Book(String title , String author , int yearOfPublication , int pages) {
        this.title=title;
        this.author=author;
        this.yearOfPublication=yearOfPublication;
        this.pages=pages;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public void setAuthor(String author) {
        this.author=author;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication=yearOfPublication;
    }

    public void setPages(int pages) {
        this.pages=pages;
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
}
