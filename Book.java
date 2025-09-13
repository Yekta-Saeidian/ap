package ap.finalProject;

public class Book {
    String title;
    String author;
    int publicationYear;
    boolean isAvailable;
    private String registeredBy;

    public Book(String title, String author, int publicationYear, String registeredBy) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
        this.registeredBy = registeredBy;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }
    public String getRegisteredBy() { return registeredBy; }

    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Title: " + title +
                " | Author: " + author +
                " | Year: " + publicationYear +
                " | Available: " + (isAvailable ? "Yes" : "No") +
                " | Registered by: " + registeredBy;
    }
}