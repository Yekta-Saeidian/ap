package ap.finalProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanManager {
    private List<Loan> loans;
    private FileManager fileManager;

    public LoanManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.loans = fileManager.loadLoans();
    }

    public void requestLoan(String studentUsername, String bookTitle, LocalDate startDate, LocalDate endDate) {
        String loanId = generateLoanId();
        Loan newLoan = new Loan(loanId, studentUsername, bookTitle, startDate, endDate, null);
        loans.add(newLoan);
        fileManager.saveLoans(loans);
        System.out.println("Loan request submitted successfully. Loan ID: " + loanId);
    }

    private String generateLoanId() {
        return "LN" + System.currentTimeMillis();
    }

    public List<Loan> getPendingLoans() {
        List<Loan> pending = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getStatus().equals("PENDING")) {
                pending.add(loan);
            }
        }
        return pending;
    }

    public List<Loan> getPendingLoansForTodayOrYesterday() {
        List<Loan> pending = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        for (Loan loan : loans) {
            if (loan.getStatus().equals("PENDING") &&
                    (loan.getStartDate().equals(today) || loan.getStartDate().equals(yesterday))) {
                pending.add(loan);
            }
        }
        return pending;
    }

    public List<Loan> getStudentLoans(String studentUsername) {
        List<Loan> studentLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getStudentUsername().equals(studentUsername)) {
                studentLoans.add(loan);
            }
        }
        return studentLoans;
    }

    public void approveLoan(String loanId, String approvedBy) {
        for (Loan loan : loans) {
            if (loan.getLoanId().equals(loanId)) {
                loan.setStatus("APPROVED");
                loan.setApprovedBy(approvedBy);
                fileManager.saveLoans(loans);
                System.out.println("Loan approved successfully.");
                return;
            }
        }
        System.out.println("Loan not found!");
    }

    public void returnBook(String loanId) {
        for (Loan loan : loans) {
            if (loan.getLoanId().equals(loanId) && loan.getStatus().equals("APPROVED")) {
                loan.setStatus("RETURNED");
                loan.setReturnDate(LocalDate.now());
                fileManager.saveLoans(loans);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Loan not found or not approved!");
    }

    public List<Loan> getAllLoans() {
        return loans;
    }

    public int getTotalLoans() {
        return loans.size();
    }

    public int getActiveLoansCount() {
        int count = 0;
        for (Loan loan : loans) {
            if (loan.getStatus().equals("APPROVED")) {
                count++;
            }
        }
        return count;
    }

    public Loan getLoanById(String loanId) {
        for (Loan loan : loans) {
            if (loan.getLoanId().equals(loanId)) {
                return loan;
            }
        }
        return null;
    }

    public int getBooksLentByEmployee(String employeeUsername) {
        int count = 0;
        for (Loan loan : loans) {
            if (loan.getApprovedBy() != null && loan.getApprovedBy().equals(employeeUsername)) {
                count++;
            }
        }
        return count;
    }

    public int getBooksReturnedByEmployee(String employeeUsername) {
        int count = 0;
        for (Loan loan : loans) {
            if (loan.getStatus().equals("RETURNED") && loan.getApprovedBy() != null &&
                    loan.getApprovedBy().equals(employeeUsername)) {
                count++;
            }
        }
        return count;
    }

    public double getAverageLoanDuration() {
        if (loans.isEmpty()) return 0;

        long totalDays = 0;
        int count = 0;

        for (Loan loan : loans) {
            if (loan.getReturnDate() != null) {
                totalDays += loan.getLoanDuration();
                count++;
            }
        }

        return count > 0 ? (double) totalDays / count : 0;
    }

    public List<String> getTopStudentsWithDelays(int limit) {
        Map<String, Integer> studentDelays = new HashMap<>();

        for (Loan loan : loans) {
            if (loan.isDelayed()) {
                String username = loan.getStudentUsername();
                studentDelays.put(username, studentDelays.getOrDefault(username, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(studentDelays.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, sortedEntries.size()); i++) {
            Map.Entry<String, Integer> entry = sortedEntries.get(i);
            result.add(entry.getKey() + ":" + entry.getValue());
        }

        return result;
    }
}