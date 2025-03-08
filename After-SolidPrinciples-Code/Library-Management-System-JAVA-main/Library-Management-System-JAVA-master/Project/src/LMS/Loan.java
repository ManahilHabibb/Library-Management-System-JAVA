package LMS;

import java.util.Date;

// Loan Class
public class Loan {
    private Borrower borrower;
    private Book book;
    private Staff issuer;
    private Date issuedDate;
    private Date dateReturned;
    private Staff receiver;
    private boolean finePaid;

    public Loan(Borrower borrower, Book book, Staff issuer, Date issuedDate) {
        this.borrower = borrower;
        this.book = book;
        this.issuer = issuer;
        this.issuedDate = issuedDate;
        this.dateReturned = null;
        this.receiver = null;
        this.finePaid = false;
    }

    // Getters and setters
    public Borrower getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public Staff getIssuer() {
        return issuer;
    }

    public Staff getReceiver() {
        return receiver;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public boolean isFinePaid() {
        return finePaid;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public void setReceiver(Staff receiver) {
        this.receiver = receiver;
    }

    public void setFinePaid(boolean finePaid) {
        this.finePaid = finePaid;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }
}

// DIP Applied: Interface for Loan Actions
interface LoanActions {
    void returnLoan(Loan loan, Staff receiver);
    void renewLoan(Loan loan, Date newIssuedDate);
    void markFineAsPaid(Loan loan);
}

// Concrete Implementation (Dependency Injected)
class LoanService implements LoanActions {
    @Override
    public void returnLoan(Loan loan, Staff receiver) {
        loan.setDateReturned(new Date());
        loan.setReceiver(receiver);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been returned by "
                + loan.getBorrower().getName());
    }

    @Override
    public void renewLoan(Loan loan, Date newIssuedDate) {
        loan.setIssuedDate(newIssuedDate);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been renewed.");
    }

    @Override
    public void markFineAsPaid(Loan loan) {
        loan.setFinePaid(true);
        System.out.println("Fine for loan on book '" + loan.getBook().getTitle() + "' has been paid.");
    }
}
