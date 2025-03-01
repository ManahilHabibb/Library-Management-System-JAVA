package LMS;

import java.util.ArrayList;

public class Borrower extends Person {
    private ArrayList<Loan> borrowedBooks;
    private ArrayList<HoldRequest> onHoldBooks;

    public Borrower(int id, String name, String address, int phoneNum) {
        super(id, name, address, phoneNum);
        this.borrowedBooks = new ArrayList<>();
        this.onHoldBooks = new ArrayList<>();
    }

    // Getters
    public ArrayList<Loan> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); 
    }

    public ArrayList<HoldRequest> getOnHoldBooks() {
        return new ArrayList<>(onHoldBooks);
    }

    // Perform an operation on a borrower (Open/Closed Principle applied)
    public void performAction(BorrowerAction action) {
        action.execute(this);
    }

    // Methods to modify lists safely
    public void addBorrowedBook(Loan loan) {
        borrowedBooks.add(loan);
    }

    public void removeBorrowedBook(Loan loan) {
        borrowedBooks.remove(loan);
    }

    public void addHoldRequest(HoldRequest holdRequest) {
        onHoldBooks.add(holdRequest);
    }

    public void removeHoldRequest(HoldRequest holdRequest) {
        onHoldBooks.remove(holdRequest);
    }
}

// Abstract Borrower Action
interface BorrowerAction {
    void execute(Borrower borrower);
}

// Concrete Action: Add Borrowed Book
class AddBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public AddBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.addBorrowedBook(loan);
    }
}

// Concrete Action: Remove Borrowed Book
class RemoveBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public RemoveBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.removeBorrowedBook(loan);
    }
}

// Concrete Action: Add Hold Request
class AddHoldRequestAction implements BorrowerAction {
    private HoldRequest holdRequest;

    public AddHoldRequestAction(HoldRequest holdRequest) {
        this.holdRequest = holdRequest;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.addHoldRequest(holdRequest);
    }
}

// Concrete Action: Remove Hold Request
class RemoveHoldRequestAction implements BorrowerAction {
    private HoldRequest holdRequest;

    public RemoveHoldRequestAction(HoldRequest holdRequest) {
        this.holdRequest = holdRequest;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.removeHoldRequest(holdRequest);
    }
}
