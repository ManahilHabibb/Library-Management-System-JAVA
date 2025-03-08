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

    public String getBookTitle() {
        return book.getTitle();
    }

    public String getBorrowerName() {
        return borrower.getName();
    }

    // Dependency Principle Applied
    private HoldRequestActionStrategy actionStrategy;

    public void setActionStrategy(HoldRequestActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void performAction() {
        if (actionStrategy != null) {
            actionStrategy.execute(this);
        } else {
            System.out.println("No action strategy set for HoldRequest.");
        }
    }
}


interface HoldRequestActionStrategy {
    void execute(HoldRequest holdRequest);
}


abstract class AbstractHoldRequestAction implements HoldRequestActionStrategy {
    protected void logAction(HoldRequest holdRequest, String action) {
        System.out.println("Hold request " + action + " for book: " +
                holdRequest.getBookTitle() +
                " by borrower: " + holdRequest.getBorrowerName());
    }
}

class ApproveHoldRequestAction extends AbstractHoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        logAction(holdRequest, "approved");
    }
}

class RejectHoldRequestAction extends AbstractHoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        logAction(holdRequest, "rejected");
    }
}

class CancelHoldRequestAction extends AbstractHoldRequestAction {
    @Override
    public void execute(HoldRequest holdRequest) {
        logAction(holdRequest, "canceled");
    }
}
