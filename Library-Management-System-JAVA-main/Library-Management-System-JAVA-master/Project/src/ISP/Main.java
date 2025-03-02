package LMS;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class Main {

    // Clears the screen (simulate by printing blank lines)
    public static void clrscr() {
        for (int i = 0; i < 20; i++)
            System.out.println();
    }

    // Asking for Input as Choice (returns a number > min and < max)
    public static int takeInput(int min, int max) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter Choice: ");
            String choice = input.next();
            try {
                int num = Integer.parseInt(choice);
                if (num > min && num < max) {
                    return num;
                } else {
                    System.out.println("\nInvalid Input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid Input.");
            }
        }
    }

    // ----- Helper methods to replace missing Library functionalities -----

    // Stub: Display all books in the library and return a list
    public static ArrayList<Book> searchForBooks(Library lib, Scanner scanner) {
        ArrayList<Book> bookList = new ArrayList<>(lib.getBooks());
        if (bookList.isEmpty()) {
            System.out.println("No books found.");
            return null;
        } else {
            System.out.println("Available Books:");
            for (int i = 0; i < bookList.size(); i++) {
                Book b = bookList.get(i);
                System.out.println(i + " - " + b.getTitle() + " by " + b.getAuthor());
            }
            return bookList;
        }
    }

    // Finds a borrower by asking for their ID
    public static Borrower findBorrower(Library lib, Scanner scanner) {
        System.out.print("Enter Borrower ID: ");
        int id = scanner.nextInt();
        for (Person p : lib.getPersons()) {
            if (p instanceof Borrower && p.getID() == id) {
                return (Borrower) p;
            }
        }
        System.out.println("Borrower not found.");
        return null;
    }

    // Computes total fine for a borrower (using issued date, deadline and per day
    // fine)
    public static double computeFine2(Library lib, Borrower bor) {
        double totalFine = 0;
        double fineRate = lib.getPerDayFine();
        for (Loan loan : lib.getLoans()) {
            if (loan.getBorrower().getID() == bor.getID()) {
                // if not yet returned, assume overdue calculation
                if (loan.getDateReturned() == null) {
                    long diff = new Date().getTime() - loan.getIssuedDate().getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    int allowedDays = lib.getBookReturnDeadline();
                    if (days > allowedDays) {
                        totalFine += (days - allowedDays) * fineRate;
                    }
                }
            }
        }
        return totalFine;
    }

    // Displays history of issued (and returned) books
    public static void viewHistory(Library lib) {
        System.out.println("Issued Books History:");
        for (Loan loan : lib.getLoans()) {
            if (loan.getDateReturned() != null) {
                System.out.println(
                        "Book: " + loan.getBook().getTitle() + " borrowed by: " + loan.getBorrower().getName());
            }
        }
    }

    // Displays all books in the library
    public static void viewAllBooks(Library lib) {
        System.out.println("All Books in Library:");
        for (Book b : lib.getBooks()) {
            System.out.println("ID: " + b.getID() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor());
        }
    }

    // Creates a new Person (Borrower, Clerk, or Librarian) based on the type
    // provided
    public static void createPerson(Library lib, char type, Scanner scanner) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter name:");
        String name = reader.readLine();
        System.out.println("Enter address:");
        String address = reader.readLine();
        System.out.println("Enter phone number:");
        int phone = scanner.nextInt();
        if (type == 'b') {
            Borrower b = new Borrower(-1, name, address, phone);
            lib.addPerson(b);
            System.out.println("Borrower created with ID: " + b.getID());
        } else if (type == 'c') {
            System.out.println("Enter salary:");
            double salary = scanner.nextDouble();
            Clerk c = new Clerk(-1, name, address, phone, salary, -1);
            lib.addPerson(c);
            System.out.println("Clerk created with ID: " + c.getID());
        } else if (type == 'l') {
            System.out.println("Enter salary:");
            double salary = scanner.nextDouble();
            Librarian l = new Librarian(-1, name, address, phone, salary, -1);
            lib.addPerson(l);
            System.out.println("Librarian created with ID: " + l.getID());
        }
    }

    // Simple login that checks ID and password from existing persons in the library
    public static Person login(Library lib, Scanner scanner) {
        System.out.print("Enter your ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter your password: ");
        String pwd = scanner.next();
        for (Person p : lib.getPersons()) {
            if (p.getID() == id && p.getPassword().equals(pwd)) {
                return p;
            }
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    // Stub: Place a hold request on a book for a borrower
    public static void makeHoldRequest(Book b, Borrower bor) {
        System.out.println("Hold request placed for book \"" + b.getTitle() + "\" by borrower " + bor.getName());
    }

    // Prints basic personal information of a person
    public static void printPersonInfo(Person p) {
        System.out.println("ID: " + p.getID() + ", Name: " + p.getName() + ", Address: " + p.getAddress() + ", Phone: "
                + p.getPhoneNumber());
    }

    // Issues a book to a borrower (creates a new Loan)
    public static void issueBook(Book b, Borrower bor, Staff issuer, Library lib) {
        System.out.println("Book \"" + b.getTitle() + "\" issued to " + bor.getName() + " by " + issuer.getName());
        Loan loan = new Loan(bor, b, issuer, issuer, new Date(), null, false);
        lib.addLoan(loan);
        bor.addBorrowedBook(loan);
    }

    // Returns a book (marks the loan as returned)
    public static void returnBook(Book b, Borrower bor, Loan l, Staff receiver) {
        System.out.println(
                "Book \"" + b.getTitle() + "\" returned by " + bor.getName() + " processed by " + receiver.getName());
        l.setDateReturned(new Date());
    }

    // Renews an issued book by updating the issued date
    public static void renewIssuedBook(Loan l, Date newDate) {
        System.out.println("Loan for book \"" + l.getBook().getTitle() + "\" renewed with new issued date: " + newDate);
        l.setIssuedDate(newDate);
    }

    // Removes a book from the library
    public static void removeBookFromLibrary(Library lib, Book b) {
        lib.getBooks().remove(b);
        System.out.println("Book \"" + b.getTitle() + "\" removed from library.");
    }

    // Stub: Changes a book's information (this example only prints the new info)
    public static void changeBookInfo(Book b, Scanner scanner) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter new title (leave blank to keep unchanged): ");
        String title = reader.readLine();
        if (!title.isEmpty()) {
            System.out.println("Book title updated to: " + title);
        }
        System.out.println("Enter new subject (leave blank to keep unchanged): ");
        String subject = reader.readLine();
        if (!subject.isEmpty()) {
            System.out.println("Book subject updated to: " + subject);
        }
        System.out.println("Enter new author (leave blank to keep unchanged): ");
        String author = reader.readLine();
        if (!author.isEmpty()) {
            System.out.println("Book author updated to: " + author);
        }
    }

    // Finds a clerk by asking for their ID
    public static Clerk findClerk(Library lib, Scanner scanner) {
        System.out.print("Enter Clerk ID: ");
        int id = scanner.nextInt();
        for (Person p : lib.getPersons()) {
            if (p instanceof Clerk && p.getID() == id) {
                return (Clerk) p;
            }
        }
        System.out.println("Clerk not found.");
        return null;
    }

    // Updates a borrower's personal information
    public static void updateBorrowerInfo(Borrower bor, Scanner scanner) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter new address: ");
        String addr = reader.readLine();
        bor.setAddress(addr);
        System.out.println("Enter new phone number: ");
        int phone = scanner.nextInt();
        bor.setPhone(phone);
        System.out.println("Borrower info updated.");
    }

    // ----- End of helper methods -----

    // A consolidated method to handle all functionalities for a logged-in person
    public static void allFunctionalities(Person person, int choice, Library lib, Scanner scanner) throws IOException {
        int input = 0;
        if (choice == 1) {
            // Search Book
            searchForBooks(lib, scanner);
        } else if (choice == 2) {
            ArrayList<Book> books = searchForBooks(lib, scanner);
            if (books != null) {
                input = takeInput(-1, books.size());
                Book b = books.get(input);
                if (person instanceof Clerk || person instanceof Librarian) {
                    Borrower bor = findBorrower(lib, scanner);
                    if (bor != null)
                        makeHoldRequest(b, bor);
                } else {
                    makeHoldRequest(b, (Borrower) person);
                }
            }
        } else if (choice == 3) {
            if (person instanceof Clerk || person instanceof Librarian) {
                Borrower bor = findBorrower(lib, scanner);
                if (bor != null)
                    printPersonInfo(bor);
            } else {
                printPersonInfo(person);
            }
        } else if (choice == 4) {
            if (person instanceof Clerk || person instanceof Librarian) {
                Borrower bor = findBorrower(lib, scanner);
                if (bor != null) {
                    double totalFine = computeFine2(lib, bor);
                    System.out.println("\nTotal Fine for " + bor.getName() + " is: Rs " + totalFine);
                }
            } else {
                double totalFine = computeFine2(lib, (Borrower) person);
                System.out.println("\nTotal Fine for " + person.getName() + " is: Rs " + totalFine);
            }
        } else if (choice == 5) {
            ArrayList<Book> books = searchForBooks(lib, scanner);
            if (books != null) {
                input = takeInput(-1, books.size());
                System.out.println("Hold requests for book \"" + books.get(input).getTitle() + "\" (stub).");
            }
        } else if (choice == 6) {
            ArrayList<Book> books = searchForBooks(lib, scanner);
            if (books != null) {
                input = takeInput(-1, books.size());
                Book b = books.get(input);
                Borrower bor = findBorrower(lib, scanner);
                if (bor != null)
                    issueBook(b, bor, (Staff) person, lib);
            }
        } else if (choice == 7) {
            Borrower bor = findBorrower(lib, scanner);
            if (bor != null) {
                System.out.println("Borrowed Books:");
                ArrayList<Loan> loans = new ArrayList<>(bor.getBorrowedBooks());
                if (!loans.isEmpty()) {
                    for (int i = 0; i < loans.size(); i++) {
                        System.out.println(i + " - " + loans.get(i).getBook().getTitle());
                    }
                    input = takeInput(-1, loans.size());
                    Loan l = loans.get(input);
                    returnBook(l.getBook(), bor, l, (Staff) person);
                } else
                    System.out.println("\nThis borrower " + bor.getName() + " has no book to return.");
            }
        } else if (choice == 8) {
            Borrower bor = findBorrower(lib, scanner);
            if (bor != null) {
                System.out.println("Borrowed Books:");
                ArrayList<Loan> loans = new ArrayList<>(bor.getBorrowedBooks());
                if (!loans.isEmpty()) {
                    for (int i = 0; i < loans.size(); i++) {
                        System.out.println(i + " - " + loans.get(i).getBook().getTitle());
                    }
                    input = takeInput(-1, loans.size());
                    renewIssuedBook(loans.get(input), new Date());
                } else
                    System.out.println("\nThis borrower " + bor.getName() + " has no issued book to renew.");
            }
        } else if (choice == 9) {
            createPerson(lib, 'b', scanner);
        } else if (choice == 10) {
            Borrower bor = findBorrower(lib, scanner);
            if (bor != null)
                updateBorrowerInfo(bor, scanner);
        } else if (choice == 11) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nEnter Title:");
            String title = reader.readLine();
            System.out.println("\nEnter Subject:");
            String subject = reader.readLine();
            System.out.println("\nEnter Author:");
            String author = reader.readLine();
            Book b = new Book(-1, title, subject, author, false);
            lib.addBook(b);
            System.out.println("Book added with ID: " + b.getID());
        } else if (choice == 12) {
            ArrayList<Book> books = searchForBooks(lib, scanner);
            if (books != null) {
                input = takeInput(-1, books.size());
                removeBookFromLibrary(lib, books.get(input));
            }
        } else if (choice == 13) {
            ArrayList<Book> books = searchForBooks(lib, scanner);
            if (books != null) {
                input = takeInput(-1, books.size());
                changeBookInfo(books.get(input), scanner);
            }
        } else if (choice == 14) {
            Clerk clerk = findClerk(lib, scanner);
            if (clerk != null)
                printPersonInfo(clerk);
        }
        System.out.println("\nPress any key to continue..\n");
        scanner.next();
    }

    // ----- MAIN METHOD -----
    public static void main(String[] args) {
        Scanner admin = new Scanner(System.in);
        Library lib = Library.getInstance();
        LibraryDAO dao = new LibraryDAO();

        // Setting default information
        lib.setPerDayFine(20);
        lib.setHoldRequestExpiry(7);
        lib.setBookReturnDeadline(5);
        lib.setName("FAST Library");

        Connection con = dao.getConnection();
        if (con == null) {
            System.out.println("\nError connecting to Database. Exiting.");
            return;
        }

        try {
            dao.populateLibrary(lib); // Populate Library with records

            boolean stop = false;
            while (!stop) {
                clrscr();
                System.out.println("--------------------------------------------------------");
                System.out.println("\tWelcome to Library Management System");
                System.out.println("--------------------------------------------------------");
                System.out.println("Following Functionalities are available: \n");
                System.out.println("1- Login");
                System.out.println("2- Exit");
                System.out.println("3- Administrative Functions");
                System.out.println("-----------------------------------------\n");

                int choice = takeInput(0, 4);

                if (choice == 3) {
                    System.out.println("\nEnter Password: ");
                    String aPass = admin.next();
                    if (aPass.equals("lib")) {
                        while (true) { // Admin Portal
                            clrscr();
                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Admin's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Add Clerk");
                            System.out.println("2- Add Librarian");
                            System.out.println("3- View Issued Books History");
                            System.out.println("4- View All Books in Library");
                            System.out.println("5- Logout");
                            System.out.println("---------------------------------------------");
                            choice = takeInput(0, 6);
                            if (choice == 5)
                                break;
                            if (choice == 1)
                                createPerson(lib, 'c', admin);
                            else if (choice == 2)
                                createPerson(lib, 'l', admin);
                            else if (choice == 3)
                                viewHistory(lib);
                            else if (choice == 4)
                                viewAllBooks(lib);
                            System.out.println("\nPress any key to continue..\n");
                            admin.next();
                        }
                    } else {
                        System.out.println("\nSorry! Wrong Password.");
                    }
                } else if (choice == 1) {
                    Person person = login(lib, admin);
                    if (person == null) {
                        // login failed
                    } else if (person instanceof Borrower) {
                        while (true) { // Borrower's Portal
                            clrscr();
                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Borrower's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Logout");
                            System.out.println("--------------------------------------------------------");
                            choice = takeInput(0, 7);
                            if (choice == 6)
                                break;
                            allFunctionalities(person, choice, lib, admin);
                        }
                    } else if (person instanceof Clerk) {
                        while (true) { // Clerk's Portal
                            clrscr();
                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Clerk's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Check out a Book");
                            System.out.println("7- Check in a Book");
                            System.out.println("8- Renew a Book");
                            System.out.println("9- Add a new Borrower");
                            System.out.println("10- Update a Borrower's Info");
                            System.out.println("11- Logout");
                            System.out.println("--------------------------------------------------------");
                            choice = takeInput(0, 12);
                            if (choice == 11)
                                break;
                            allFunctionalities(person, choice, lib, admin);
                        }
                    } else if (person instanceof Librarian) {
                        while (true) { // Librarian's Portal
                            clrscr();
                            System.out.println("--------------------------------------------------------");
                            System.out.println("\tWelcome to Librarian's Portal");
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Following Functionalities are available: \n");
                            System.out.println("1- Search a Book");
                            System.out.println("2- Place a Book on hold");
                            System.out.println("3- Check Personal Info of Borrower");
                            System.out.println("4- Check Total Fine of Borrower");
                            System.out.println("5- Check Hold Requests Queue of a Book");
                            System.out.println("6- Check out a Book");
                            System.out.println("7- Check in a Book");
                            System.out.println("8- Renew a Book");
                            System.out.println("9- Add a new Borrower");
                            System.out.println("10- Update a Borrower's Info");
                            System.out.println("11- Add new Book");
                            System.out.println("12- Remove a Book");
                            System.out.println("13- Change a Book's Info");
                            System.out.println("14- Check Personal Info of Clerk");
                            System.out.println("15- Logout");
                            System.out.println("--------------------------------------------------------");
                            choice = takeInput(0, 16);
                            if (choice == 15)
                                break;
                            allFunctionalities(person, choice, lib, admin);
                        }
                    }
                } else
                    stop = true;
                System.out.println("\nPress any key to continue..\n");
                admin.next();
            }
            dao.persistLibrary(lib);
        } catch (Exception e) {
            System.out.println("\nExiting...\n");
        }
    }
}
