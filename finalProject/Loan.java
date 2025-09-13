package ap.finalProject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Manageable {
    private String loanId;
    private String studentUsername;
    private String bookTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private String status;
    private String approvedBy;

    public Loan(String loanId, String studentUsername, String bookTitle, LocalDate startDate, LocalDate endDate, String approvedBy) {
        this.loanId = loanId;
        this.studentUsername = studentUsername;
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PENDING";
        this.approvedBy = approvedBy;
    }

    public String getLoanId() { return loanId; }
    public String getStudentUsername() { return studentUsername; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }
    public String getApprovedBy() { return approvedBy; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

    public long getLoanDuration() {
        if (returnDate != null) {
            return ChronoUnit.DAYS.between(startDate, returnDate);
        }
        return ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }

    public boolean isDelayed() {
        if (returnDate != null) {
            return returnDate.isAfter(endDate);
        }
        return LocalDate.now().isAfter(endDate);
    }

    public long getDelayDays() {
        if (isDelayed()) {
            if (returnDate != null) {
                return ChronoUnit.DAYS.between(endDate, returnDate);
            }
            return ChronoUnit.DAYS.between(endDate, LocalDate.now());
        }
        return 0;
    }

    public void displayInfo() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId +
                " | Book: " + bookTitle +
                " | Student: " + studentUsername +
                " | Period: " + startDate + " to " + endDate +
                " | Status: " + status +
                " | Approved by: " + approvedBy +
                (returnDate != null ? " | Returned: " + returnDate : "");
    }
}