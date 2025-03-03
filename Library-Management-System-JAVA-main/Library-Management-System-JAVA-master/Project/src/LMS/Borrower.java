package LMS;

import java.util.ArrayList;

// Interface for Borrowing Actions
interface BorrowingActions {
    void borrowBook(Loan loan);
    void returnBook(Loan loan);
}

// Interface for Hold Request Actions
interface HoldRequestActions {
    void addHoldRequest(HoldRequest holdRequest);
    void removeHoldRequest(HoldRequest holdRequest);
}

// Borrower class implementing separate interfaces
public class Borrower extends Person implements BorrowingActions, HoldRequestActions {
    private ArrayList<Loan> borrowedBooks;
    private ArrayList<HoldRequest> holdRequests;

    public Borrower(int id, String name, String address, int phoneNum) {
        super(id, name, address, phoneNum);
        this.borrowedBooks = new ArrayList<>();
        this.holdRequests = new ArrayList<>();
    }

    // Getters
    public ArrayList<Loan> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); // Returning a copy to preserve encapsulation
    }

    public ArrayList<HoldRequest> getHoldRequests() {
        return new ArrayList<>(holdRequests);
    }

    // Borrowing Actions
    @Override
    public void borrowBook(Loan loan) {
        if (loan != null) {
            borrowedBooks.add(loan);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Invalid loan record.");
        }
    }

    @Override
    public void returnBook(Loan loan) {
        if (loan != null && borrowedBooks.contains(loan)) {
            borrowedBooks.remove(loan);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Loan record not found.");
        }
    }

    // New methods for loan management
    public void addLoan(Loan loan) {
        borrowedBooks.add(loan);
    }

    public void removeLoan(Loan loan) {
        borrowedBooks.remove(loan);
    }

    // Hold Request Actions
    @Override
    public void addHoldRequest(HoldRequest holdRequest) {
        if (holdRequest != null) {
            holdRequests.add(holdRequest);
            System.out.println("Hold request added successfully.");
        } else {
            System.out.println("Invalid hold request.");
        }
    }

    @Override
    public void removeHoldRequest(HoldRequest holdRequest) {
        if (holdRequest != null && holdRequests.contains(holdRequest)) {
            holdRequests.remove(holdRequest);
            System.out.println("Hold request removed successfully.");
        } else {
            System.out.println("Hold request not found.");
        }
    }

    // Perform an action on the borrower
    public void performAction(BorrowerAction action) {
        if (action != null) {
            action.execute(this);
        } else {
            System.out.println("Invalid action.");
        }
    }
}

// BorrowerAction Interface
interface BorrowerAction {
    void execute(Borrower borrower);
}

// Action for Adding a Borrowed Book
class AddBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public AddBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.addLoan(loan);
    }
}

// Action for Removing a Borrowed Book
class RemoveBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public RemoveBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.removeLoan(loan);
    }
}

// Action for Adding a Hold Request
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

// Action for Removing a Hold Request
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
