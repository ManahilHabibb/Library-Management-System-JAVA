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

// Abstraction for managing borrower's loans and hold requests (DIP applied)
interface IBorrowerRepository {
    void addLoan(Loan loan);
    void removeLoan(Loan loan);
    ArrayList<Loan> getLoans();
    void addHoldRequest(HoldRequest holdRequest);
    void removeHoldRequest(HoldRequest holdRequest);
    ArrayList<HoldRequest> getHoldRequests();
}

// Borrower class now depends on IBorrowerRepository instead of directly using ArrayList
// This follows DIP by depending on an abstraction
public class Borrower extends Person implements BorrowingActions, HoldRequestActions {
    private IBorrowerRepository borrowerRepository; // Dependency injection

    public Borrower(int id, String name, String address, int phoneNum, IBorrowerRepository borrowerRepository) {
        super(id, name, address, phoneNum); // ✅ Ensure this constructor exists in Person class
        this.borrowerRepository = borrowerRepository;
    }

    public void performAction(BorrowerAction action) {
        action.execute(this);
    }

    // Getters following DIP
    public ArrayList<Loan> getBorrowedBooks() {
        return borrowerRepository.getLoans();
    }

    public ArrayList<HoldRequest> getHoldRequests() {
        return borrowerRepository.getHoldRequests();
    }

    // Borrowing Actions following DIP
    @Override
    public void borrowBook(Loan loan) {
        if (loan != null) {
            borrowerRepository.addLoan(loan);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Invalid loan record.");
        }
    }

    @Override
    public void returnBook(Loan loan) {
        if (loan != null) {
            borrowerRepository.removeLoan(loan);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Loan record not found.");
        }
    }

    // Hold Request Actions following DIP
    @Override
    public void addHoldRequest(HoldRequest holdRequest) {
        if (holdRequest != null) {
            borrowerRepository.addHoldRequest(holdRequest);
            System.out.println("Hold request added successfully.");
        } else {
            System.out.println("Invalid hold request.");
        }
    }

    @Override
    public void removeHoldRequest(HoldRequest holdRequest) {
        if (holdRequest != null) {
            borrowerRepository.removeHoldRequest(holdRequest);
            System.out.println("Hold request removed successfully.");
        } else {
            System.out.println("Hold request not found.");
        }
    }
}

// BorrowerAction Interface remains the same
interface BorrowerAction {
    void execute(Borrower borrower);
}

// Actions remain the same as they work with the abstraction
class AddBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public AddBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.borrowBook(loan);
    }
}

class RemoveBorrowedBookAction implements BorrowerAction {
    private Loan loan;

    public RemoveBorrowedBookAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Borrower borrower) {
        borrower.returnBook(loan);
    }
}

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
