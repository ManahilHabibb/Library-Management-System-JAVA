package LMS;

import java.util.Date;

public class Loan {
    private Borrower borrower;
    private Book book;
    private Staff issuer;
    private Date issuedDate;
    private Date dateReturned;
    private Staff receiver;
    private boolean finePaid;

    public Loan(Borrower borrower, Book book, Staff issuer, Staff receiver, Date issuedDate, Date dateReturned,
            boolean finePaid) {
        this.borrower = borrower;
        this.book = book;
        this.issuer = issuer;
        this.receiver = receiver;
        this.issuedDate = issuedDate;
        this.dateReturned = dateReturned;
        this.finePaid = finePaid;
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

    // Open for extension: Loan operations
    public void performAction(LoanAction action) {
        action.execute(this);
    }
}

// Abstract Action Class
interface LoanAction {
    void execute(Loan loan);
}

// Concrete Actions
class ReturnLoanAction implements LoanAction {
    private Staff receiver;

    public ReturnLoanAction(Staff receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(Loan loan) {
        loan.setDateReturned(new Date());
        loan.setReceiver(receiver);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been returned by "
                + loan.getBorrower().getName());
    }
}

class RenewLoanAction implements LoanAction {
    private Date newIssuedDate;

    public RenewLoanAction(Date newIssuedDate) {
        this.newIssuedDate = newIssuedDate;
    }

    @Override
    public void execute(Loan loan) {
        loan.setIssuedDate(newIssuedDate);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been renewed.");
    }
}

class MarkFinePaidAction implements LoanAction {
    @Override
    public void execute(Loan loan) {
        loan.setFinePaid(true);
        System.out.println("Fine for loan on book '" + loan.getBook().getTitle() + "' has been paid.");
    }
}
