package LMS;

import java.util.ArrayList;

public class BorrowerPrinter {

    public static void printBorrowerInfo(Borrower borrower) {
        System.out.println("\nBorrower Details:");
        System.out.println("Name: " + borrower.getName());
        System.out.println("Address: " + borrower.getAddress());
        System.out.println("Phone: " + borrower.getPhoneNumber());

        printBorrowedBooks(borrower);
        printOnHoldBooks(borrower);
    }

    public static void printBorrowedBooks(Borrower borrower) {
        ArrayList<Loan> borrowedBooks = borrower.getBorrowedBooks();
        if (!borrowedBooks.isEmpty()) {
            System.out.println("\nBorrowed Books:");
            System.out.println("------------------------------------------------");
            System.out.println("No.\tTitle\t\tAuthor\t\tSubject");
            System.out.println("------------------------------------------------");

            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.print(i + "-\t");
                BookPrinter.printBookInfo(borrowedBooks.get(i).getBook());
            }
        } else {
            System.out.println("\nNo borrowed books.");
        }
    }

    public static void printOnHoldBooks(Borrower borrower) {
        ArrayList<HoldRequest> onHoldBooks = borrower.getOnHoldBooks();
        if (!onHoldBooks.isEmpty()) {
            System.out.println("\nOn Hold Books:");
            System.out.println("------------------------------------------------");
            System.out.println("No.\tTitle\t\tAuthor\t\tSubject");
            System.out.println("------------------------------------------------");

            for (int i = 0; i < onHoldBooks.size(); i++) {
                System.out.print(i + "-\t");
                BookPrinter.printBookInfo(onHoldBooks.get(i).getBook());
            }
        } else {
            System.out.println("\nNo on-hold books.");
        }
    }
}
