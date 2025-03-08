package LMS;

import java.util.Date;
import java.util.Scanner;

// BookService now depends on an abstraction (IHoldRequestRepository) instead of a concrete class
public class BookService {
    private IHoldRequestRepository holdRequestRepository;

    //  (DIP applied)
    public BookService(IHoldRequestRepository holdRequestRepository) {
        this.holdRequestRepository = holdRequestRepository;
    }

    public void performAction(BookOperation operation, Book book, Borrower borrower, Staff staff, Loan loan) {
        operation.execute(book, borrower, staff, loan, holdRequestRepository);
    }
}

// Interface for HoldRequestRepository 
interface IHoldRequestRepository {
    void addHoldRequest(HoldRequest holdRequest);
    void removeHoldRequest(HoldRequest holdRequest);
    java.util.List<HoldRequest> getHoldRequests();
}

// Concrete implementation of IHoldRequestRepository
class HoldRequestRepository implements IHoldRequestRepository {
    private java.util.List<HoldRequest> holdRequests = new java.util.ArrayList<>();

    @Override
    public void addHoldRequest(HoldRequest holdRequest) {
        holdRequests.add(holdRequest);
    }

    @Override
    public void removeHoldRequest(HoldRequest holdRequest) {
        holdRequests.remove(holdRequest);
    }

    @Override
    public java.util.List<HoldRequest> getHoldRequests() {
        return holdRequests;
    }

    public HoldRequest removeFirstHoldRequest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirstHoldRequest'");
    }
}

// Base BookOperation interface
interface BookOperation {
    void execute(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository);
}

// Interfaces for different operations
interface IssueBookOperation extends BookOperation {
    void issueBook(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository);
}

interface HoldRequestOperation extends BookOperation {
    void makeHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository);
}

interface ServiceHoldRequestOperation extends BookOperation {
    void serviceHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository);
}

interface ReturnBookOperation extends BookOperation {
    void returnBook(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository);
}

// Implementation for issuing a book
class IssueBookOperationImpl implements IssueBookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        issueBook(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void issueBook(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        if (book.isIssued()) {
            System.out.println("\nThe book " + book.getTitle() + " is already issued.");
            System.out.println("Would you like to place a hold request? (y/n)");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            if (choice.equalsIgnoreCase("y")) {
                new MakeHoldRequestOperationImpl().execute(book, borrower, staff, loan, holdRequestRepository);
            }
            sc.close();
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
        Loan newLoan = new Loan(borrower, book, staff, new Date());

        Library.getInstance().addLoan(newLoan);
        borrower.performAction(new AddBorrowedBookAction(newLoan));
        System.out.println("\nThe book " + book.getTitle() + " is successfully issued to " + borrower.getName() + ".");
    }
}

// Implementation for placing a hold request
class MakeHoldRequestOperationImpl implements HoldRequestOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        makeHoldRequest(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void makeHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        HoldRequest hr = new HoldRequest(borrower, book, new Date());
        holdRequestRepository.addHoldRequest(hr);
        borrower.performAction(new AddHoldRequestAction(hr));
        System.out.println("\nThe book " + book.getTitle() + " has been placed on hold by " + borrower.getName());
    }
}

// Implementation for servicing a hold request
class ServiceHoldRequestOperationImpl implements ServiceHoldRequestOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        serviceHoldRequest(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void serviceHoldRequest(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
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
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        returnBook(book, borrower, staff, loan, holdRequestRepository);
    }

    @Override
    public void returnBook(Book book, Borrower borrower, Staff staff, Loan loan, IHoldRequestRepository holdRequestRepository) {
        book.setIssued(false);
        loan.setDateReturned(new Date());
        loan.setReceiver(staff);
        borrower.performAction(new RemoveBorrowedBookAction(loan));
        System.out.println("\nThe book " + book.getTitle() + " has been returned by " + borrower.getName());
    }
}

// Action for Adding a Borrowed Book
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

// Action for Removing a Borrowed Book
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
