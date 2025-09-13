package ap.finalProject;

public interface LibraryItem {
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    boolean isAvailable();
    void setAvailable(boolean available);
}