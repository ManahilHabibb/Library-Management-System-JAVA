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

    // Borrowing Actions
    @Override
    public void borrowBook(Loan loan) {
        borrowedBooks.add(loan);
        System.out.println("Book borrowed successfully.");
    }

    @Override
    public void returnBook(Loan loan) {
        borrowedBooks.remove(loan);
        System.out.println("Book returned successfully.");
    }

    // Hold Request Actions
    @Override
    public void addHoldRequest(HoldRequest holdRequest) {
        onHoldBooks.add(holdRequest);
        System.out.println("Hold request added successfully.");
    }

    @Override
    public void removeHoldRequest(HoldRequest holdRequest) {
        onHoldBooks.remove(holdRequest);
        System.out.println("Hold request removed successfully.");
    }
}

