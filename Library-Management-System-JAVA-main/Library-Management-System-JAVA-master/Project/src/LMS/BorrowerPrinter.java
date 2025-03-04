package LMS;

import java.util.ArrayList;

//  Applying Dependency Inversion Principle By Creating Interface for Borrower Data 
interface IBorrowerData {
    String getName();
    String getAddress();
    int getPhoneNumber();
    ArrayList<Loan> getBorrowedBooks();
    ArrayList<HoldRequest> getHoldRequests();
}

public class BorrowerPrinter {
    public static void printBorrowerInfo(IBorrowerData borrower) { // Now using abstraction
        System.out.println("\nBorrower Details:");
        System.out.println("Name: " + borrower.getName());
        System.out.println("Address: " + borrower.getAddress());
        System.out.println("Phone: " + borrower.getPhoneNumber());

        printBorrowedBooks(borrower);
        printOnHoldBooks(borrower);
    }

    public static void printBorrowedBooks(IBorrowerData borrower) {
        ArrayList<Loan> borrowedBooks = borrower.getBorrowedBooks();
        if (borrowedBooks == null || borrowedBooks.isEmpty()) {
            System.out.println("\nNo borrowed books.");
            return;
        }

        System.out.println("\nBorrowed Books:");
        System.out.println("------------------------------------------------");
        System.out.println("No.\tTitle\t\tAuthor\t\tSubject");
        System.out.println("------------------------------------------------");

        BookPrinter bookPrinter = new BookPrinter(); 

        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i).getBook();
            if (book != null) {
                System.out.print(i + "-\t");
                bookPrinter.printBookInfo(book);  
            } else {
                System.out.println("Book information is missing.");
            }
        }
    }

    public static void printOnHoldBooks(IBorrowerData borrower) {
        ArrayList<HoldRequest> onHoldBooks = borrower.getHoldRequests();
        if (onHoldBooks == null || onHoldBooks.isEmpty()) {
            System.out.println("\nNo on-hold books.");
            return;
        }

        System.out.println("\nOn Hold Books:");
        System.out.println("------------------------------------------------");
        System.out.println("No.\tTitle\t\tAuthor\t\tSubject");
        System.out.println("------------------------------------------------");

        BookPrinter bookPrinter = new BookPrinter(); 

        for (int i = 0; i < onHoldBooks.size(); i++) {
            Book book = onHoldBooks.get(i).getBook();
            if (book != null) {
                System.out.print(i + "-\t");
                bookPrinter.printBookInfo(book);  
            } else {
                System.out.println("Book information is missing.");
            }
        }
    }
}
