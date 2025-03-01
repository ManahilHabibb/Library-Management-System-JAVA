package LMS;

import java.util.Date;
import java.util.Scanner;

public class BookService {
    private HoldRequestRepository holdRequestRepository = new HoldRequestRepository();

    public void performAction(BookOperation operation, Book book, Borrower borrower, Staff staff, Loan loan) {
        operation.execute(book, borrower, staff, loan, holdRequestRepository);
    }
}

// Abstract Operation Class
interface BookOperation {
    void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository);
}

// Concrete Operations
class IssueBookOperation implements BookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        if (book.isIssued()) {
            System.out.println("\nThe book " + book.getTitle() + " is already issued.");
            System.out.println("Would you like to place a hold request? (y/n)");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            sc.close();
            if (choice.equals("y")) {
                new MakeHoldRequestOperation().execute(book, borrower, staff, loan, holdRequestRepository);
            }
            return;
        }

        if (!holdRequestRepository.getHoldRequests().isEmpty()) {
            if (holdRequestRepository.getHoldRequests().get(0).getBorrower() != borrower) {
                System.out.println("\nSorry, another user has requested this book earlier.");
                return;
            } else {
                new ServiceHoldRequestOperation().execute(book, borrower, staff, loan, holdRequestRepository);
            }
        }

        book.setIssued(true);
        Loan newLoan = new Loan(borrower, book, staff, null, new Date(), null, false);
        Library.getInstance().addLoan(newLoan);
        borrower.performAction(new AddBorrowedBookAction(newLoan));
        System.out.println("\nThe book " + book.getTitle() + " is successfully issued to " + borrower.getName() + ".");
    }
}

class MakeHoldRequestOperation implements BookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        HoldRequest hr = new HoldRequest(borrower, book, new Date());
        holdRequestRepository.addHoldRequest(hr);
        borrower.performAction(new AddHoldRequestAction(hr));
        System.out.println("\nThe book " + book.getTitle() + " has been placed on hold by " + borrower.getName());
    }
}

class ServiceHoldRequestOperation implements BookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        if (!holdRequestRepository.getHoldRequests().isEmpty()) {
            HoldRequest hr = holdRequestRepository.getHoldRequests().get(0);
            holdRequestRepository.removeHoldRequest(hr);
            hr.getBorrower().performAction(new RemoveHoldRequestAction(hr));
        }
    }
}

class ReturnBookOperation implements BookOperation {
    @Override
    public void execute(Book book, Borrower borrower, Staff staff, Loan loan, HoldRequestRepository holdRequestRepository) {
        book.setIssued(false);
        loan.setDateReturned(new Date());
        loan.setReceiver(staff);
        borrower.performAction(new RemoveBorrowedBookAction(loan));
        System.out.println("\nThe book " + book.getTitle() + " has been returned by " + borrower.getName());
    }
}
