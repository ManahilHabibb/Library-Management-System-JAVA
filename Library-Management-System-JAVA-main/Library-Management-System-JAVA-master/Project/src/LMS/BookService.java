package LMS;

import java.util.Date;
import java.util.Scanner;

// Main service class that performs actions based on operations
public class BookService {
    private HoldRequestRepository holdRequestRepository = new HoldRequestRepository();

    public void performAction(BookOperation operation, Book book, Borrower borrower, Staff staff, Loan loan) {
        operation.execute(book, borrower, staff, loan, holdRequestRepository);
    }
}

// Base BookOperation interface
interface BookOperation {
    void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

// Interfaces for different operations
interface IssueBookOperation extends BookOperation {
    void issueBook(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

interface HoldRequestOperation extends BookOperation {
    void makeHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

interface ServiceHoldRequestOperation extends BookOperation {
    void serviceHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

interface ReturnBookOperation extends BookOperation {
    void returnBook(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

// Implementation for issuing a book
class IssueBookOperationImpl implements IssueBookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        issueBook(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void issueBook(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        if (book.isIssued()) {
            System.out.println("\nThe book " + book.getTitle() + " is already issued.");
            System.out.println("Would you like to place a hold request? (y/n)");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            sc.close();
            if (choice.equals("y")) {
                new MakeHoldRequestOperationImpl().execute(book, borrower, staff, loan, holdRequestRepository);
            }
            return;
        }

        if (!holdRequestRepository.getHoldRequests().isEmpty()) {
            if (holdRequestRepository.getHoldRequests().get(0).getBorrower() != borrower) {
                System.out.println("\nSorry, another user has requested this book earlier.");
                return;
            } else {
                new ServiceHoldRequestOperationImpl().execute(book, borrower, staff, loan, holdRequestRepository);
            }
        }

        book.setIssued(true);
        Loan newLoan = new Loan(borrower, book, staff, null, new Date(), null, false);
        Library.getInstance().addLoan(newLoan);
        borrower.performAction(new AddBorrowedBookAction(newLoan));
        System.out.println("\nThe book " + book.getTitle() + " is successfully issued to " + borrower.getName() + ".");
    }
}

// Implementation for placing a hold request
class MakeHoldRequestOperationImpl implements HoldRequestOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        makeHoldRequest(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void makeHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        HoldRequest hr = new HoldRequest(borrower, book, new Date());
        holdRequestRepository.addHoldRequest(hr);
        borrower.performAction(new AddHoldRequestAction(hr));
        System.out.println("\nThe book " + book.getTitle() + " has been placed on hold by " + borrower.getName());
    }
}

// Implementation for servicing a hold request
class ServiceHoldRequestOperationImpl implements ServiceHoldRequestOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        serviceHoldRequest(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void serviceHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        if (!holdRequestRepository.getHoldRequests().isEmpty()) {
            HoldRequest hr = holdRequestRepository.getHoldRequests().get(0);
            holdRequestRepository.removeHoldRequest(hr);
            hr.getBorrower().performAction(new RemoveHoldRequestAction(hr));
        }
    }
}

// Implementation for returning a book
class ReturnBookOperationImpl implements ReturnBookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        returnBook(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void returnBook(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        book.setIssued(false);
        loan.setDateReturned(new Date());
        loan.setReceiver(staff);
        borrower.performAction(new RemoveBorrowedBookAction(loan));
        System.out.println("\nThe book " + book.getTitle() + " has been returned by " + borrower.getName());
    }
}

// Fix: Missing Action Classes for Borrower Operations
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


