package LMS;

// Book class
public class Book {
    private int bookID;
    private String title;
    private String subject;
    private String author;
    private boolean isIssued;
    private boolean isReserved;

    private static int currentIdNumber = 0;

    public Book(int id, String title, String subject, String author, boolean issued) {
        currentIdNumber++;
        this.bookID = (id == -1) ? currentIdNumber : id;
        this.title = title;
        this.subject = subject;
        this.author = author;
        this.isIssued = issued;
        this.isReserved = false;
    }

    // Getters and setters
    public int getID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }

    public static void setIDCount(int n) {
        currentIdNumber = n;
    }
}

// Segregated interfaces following ISP

// Interface for issuing a book
interface Issuable {
    void issue(Book book);
}

// Interface for returning a book
interface Returnable {
    void returnBook(Book book);
}

// Interface for reserving a book
interface Reservable {
    void reserve(Book book);
}

// Concrete class for issuing a book
class IssueBookAction implements Issuable {
    @Override
    public void issue(Book book) {
        if (!book.isIssued()) {
            book.setIssued(true);
            System.out.println("Book '" + book.getTitle() + "' has been issued.");
        } else {
            System.out.println("Book '" + book.getTitle() + "' is already issued.");
        }
    }
}

// Concrete class for returning a book
class ReturnBookAction implements Returnable {
    @Override
    public void returnBook(Book book) {
        if (book.isIssued()) {
            book.setIssued(false);
            System.out.println("Book '" + book.getTitle() + "' has been returned.");
        } else {
            System.out.println("Book '" + book.getTitle() + "' was not issued.");
        }
    }
}

// Concrete class for reserving a book
class ReserveBookAction implements Reservable {
    @Override
    public void reserve(Book book) {
        if (!book.isReserved()) {
            book.setReserved(true);
            System.out.println("Book '" + book.getTitle() + "' has been reserved.");
        } else {
            System.out.println("Book '" + book.getTitle() + "' is already reserved.");
        }
    }
}
