package Project1;

import java.time.LocalDate;

public class Borrow {
    private int studentId;
    private String bookTitle;
    private int assistantId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean approved;

    public Borrow(int studentId, String bookTitle) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.approved = false;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getAssistantId() {
        return assistantId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approveBorrowRequest(int assistantId) {
        this.assistantId = assistantId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusWeeks(2);
        this.approved = true;
    }

    public void approveReturnRequest(int assistantId) {
        this.assistantId = assistantId;
        this.returnDate = LocalDate.now();
        this.approved = true;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = LocalDate.now();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = LocalDate.now();
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = LocalDate.now();
    }
}
