package LMS;

public class Book {
    private int bookID;
    private String title;
    private String subject;
    private String author;
    private boolean isIssued;

    private static int currentIdNumber = 0;

    public Book(int id, String title, String subject, String author, boolean issued) {
        currentIdNumber++;
        this.bookID = (id == -1) ? currentIdNumber : id;
        this.title = title;
        this.subject = subject;
        this.author = author;
        this.isIssued = issued;
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

    public static void setIDCount(int n) {
        currentIdNumber = n;
    }

    // Open for extension: Book actions
    public void performAction(BookAction action) {
        action.execute(this);
    }
}

// Abstract Action Class
interface BookAction {
    void execute(Book book);
}

// Action: Issuing a book
class IssueBookAction implements BookAction {
    @Override
    public void execute(Book book) {
        book.setIssued(true);
        System.out.println("Book '" + book.getTitle() + "' has been issued.");
    }
}

// Action: Returning a book
class ReturnBookAction implements BookAction {
    @Override
    public void execute(Book book) {
        book.setIssued(false);
        System.out.println("Book '" + book.getTitle() + "' has been returned.");
    }
}
