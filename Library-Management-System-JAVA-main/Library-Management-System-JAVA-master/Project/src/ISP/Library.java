package LMS;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private Librarian librarian;
    private List<Person> persons;
    private List<Book> books;
    private List<Loan> loans;
    private int bookReturnDeadline;
    private double perDayFine;
    private int holdRequestExpiry;

    private static Library instance = null;

    private Library() {
        this.name = "";
        this.librarian = null;
        this.persons = new ArrayList<>();
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public int getBookReturnDeadline() {
        return bookReturnDeadline;
    }

    public void setBookReturnDeadline(int deadline) {
        this.bookReturnDeadline = deadline;
    }

    public double getPerDayFine() {
        return perDayFine;
    }

    public void setPerDayFine(double perDayFine) {
        this.perDayFine = perDayFine;
    }

    public int getHoldRequestExpiry() {
        return holdRequestExpiry;
    }

    public void setHoldRequestExpiry(int holdRequestExpiry) {
        this.holdRequestExpiry = holdRequestExpiry;
    }

    // Open for extension: Library operations
    public void performAction(LibraryAction action) {
        action.execute(this);
    }

    // Fix: Keep the original methods so no error occurs
    public void addPerson(Person p) {
        performAction(new AddPersonAction(p)); // Uses OCP internally
    }

    public void addBook(Book b) {
        performAction(new AddBookAction(b)); // Uses OCP internally
    }

    public void addLoan(Loan l) {
        performAction(new AddLoanAction(l)); // Uses OCP internally
    }
}

// Abstract Action Class
interface LibraryAction {
    void execute(Library library);
}

// Concrete Actions
class AddPersonAction implements LibraryAction {
    private Person person;

    public AddPersonAction(Person person) {
        this.person = person;
    }

    @Override
    public void execute(Library library) {
        library.getPersons().add(person);
    }
}

class AddBookAction implements LibraryAction {
    private Book book;

    public AddBookAction(Book book) {
        this.book = book;
    }

    @Override
    public void execute(Library library) {
        library.getBooks().add(book);
    }
}

class AddLoanAction implements LibraryAction {
    private Loan loan;

    public AddLoanAction(Loan loan) {
        this.loan = loan;
    }

    @Override
    public void execute(Library library) {
        library.getLoans().add(loan);
    }
}
