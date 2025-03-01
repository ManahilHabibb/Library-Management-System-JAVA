package LMS;

import java.util.Date;

public class HoldRequest {
    private Borrower borrower;
    private Book book;
    private Date requestDate;

    public HoldRequest(Borrower borrower, Book book, Date requestDate) {
        this.borrower = borrower;
        this.book = book;
        this.requestDate = requestDate;
    }

    // Getter methods
    public Borrower getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    // Open for extension: Hold the request actions
    public void performAction(HoldRequestAction action) {
        action.execute(this);
    }
}

// Abstract Action Class
interface HoldRequestAction {
    void execute(HoldRequest holdRequest);
}

// Concrete Actions
class ApproveHoldRequestAction implements HoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        System.out.println("Hold request approved for book: " + holdRequest.getBook().getTitle() +
                " by borrower: " + holdRequest.getBorrower().getName());
    }
}

class RejectHoldRequestAction implements HoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        System.out.println("Hold request rejected for book: " + holdRequest.getBook().getTitle() +
                " by borrower: " + holdRequest.getBorrower().getName());
    }
}

class CancelHoldRequestAction implements HoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        System.out.println("Hold request canceled for book: " + holdRequest.getBook().getTitle() +
                " by borrower: " + holdRequest.getBorrower().getName());
    }
}
