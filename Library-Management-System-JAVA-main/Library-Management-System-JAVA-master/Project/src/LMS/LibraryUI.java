package LMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class LibraryUI {

    private Library library;
    private LibraryService service;
    private LibraryDAO dao;

    public LibraryUI() {
        library = Library.getInstance();
        service = new LibraryService();
        dao = new LibraryDAO();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Load library data from the database
        try {
            dao.populateLibrary(library);
        } catch (Exception ex) {
            System.out.println("Error populating library: " + ex.getMessage());
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Search Books");
            System.out.println("2. Compute Fine for Borrower");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    handleSearchBooks(sc, reader);
                    break;
                case 2:
                    handleComputeFine(sc);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        sc.close();

        // Persist changes back to the database before exit
        try {
            dao.persistLibrary(library);
        } catch (Exception ex) {
            System.out.println("Error persisting library: " + ex.getMessage());
        }
    }

    private void handleSearchBooks(Scanner sc, BufferedReader reader) {
        try {
            System.out.println("Search by: 1. Title, 2. Subject, 3. Author");
            int searchChoice = sc.nextInt();
            System.out.print("Enter search query: ");
            String query = reader.readLine();
            List<Book> results = service.searchBooks(library, searchChoice, query);
            if (results.isEmpty()) {
                System.out.println("No books found.");
            } else {
                System.out.println("Found Books:");
                for (Book b : results) {
                    System.out.println(b.getID() + ": " + b.getTitle() + " by " + b.getAuthor());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading input: " + ex.getMessage());
        }
    }

    private void handleComputeFine(Scanner sc) {
        System.out.print("Enter Borrower ID: ");
        int borrowerId = sc.nextInt();
        Borrower borrower = service.findBorrower(library, borrowerId);
        if (borrower == null) {
            System.out.println("Borrower not found.");
        } else {
            // double fine = service.computeTotalFine(library, borrower);
            // System.out.println("Total fine for " + borrower.getName() + " is: " + fine);
        }
    }

    public static void main(String[] args) {
        LibraryUI ui = new LibraryUI();
        ui.start();
    }
}
