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
}

// Separate Interfaces (ISP Applied)
interface ReturnLoan {
    void returnLoan(Loan loan, Staff receiver);
}

interface RenewLoan {
    void renewLoan(Loan loan, Date newIssuedDate);
}

interface MarkFinePaid {
    void markFineAsPaid(Loan loan);
}

// Concrete Action for Returning Loan
class ReturnLoanAction implements ReturnLoan {
    @Override
    public void returnLoan(Loan loan, Staff receiver) {
        loan.setDateReturned(new Date());
        loan.setReceiver(receiver);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been returned by "
                + loan.getBorrower().getName());
    }
}

// Concrete Action for Renewing Loan
class RenewLoanAction implements RenewLoan {
    @Override
    public void renewLoan(Loan loan, Date newIssuedDate) {
        loan.setIssuedDate(newIssuedDate);
        System.out.println("Loan for book '" + loan.getBook().getTitle() + "' has been renewed.");
    }
}

// Concrete Action for Marking Fine as Paid
class MarkFinePaidAction implements MarkFinePaid {
    @Override
    public void markFineAsPaid(Loan loan) {
        loan.setFinePaid(true);
        System.out.println("Fine for loan on book '" + loan.getBook().getTitle() + "' has been paid.");
    }
}
