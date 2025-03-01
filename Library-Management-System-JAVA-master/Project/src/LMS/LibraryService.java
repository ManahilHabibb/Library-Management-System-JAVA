package LMS;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    // Fix: Restore original methods to prevent errors while using OCP internally
    public Borrower findBorrower(Library library, int id) {
        return performAction(new FindBorrowerOperation(), library, id);
    }

    public Clerk findClerk(Library library, int id) {
        return performAction(new FindClerkOperation(), library, id);
    }

    public List<Book> searchBooks(Library library, int searchType, String query) {
        return performAction(new SearchBooksOperation(), library, searchType, query);
    }

    // OCP-Enabled Execution
    public <T> T performAction(LibraryOperation<T> operation, Library library, Object... args) {
        return operation.execute(library, args);
    }
}

// Abstract Interface for Library Operations
interface LibraryOperation<T> {
    T execute(Library library, Object... args);
}

// Find Borrower Implementation
class FindBorrowerOperation implements LibraryOperation<Borrower> {
    @Override
    public Borrower execute(Library library, Object... args) {
        int id = (int) args[0];
        for (Person p : library.getPersons()) {
            if (p instanceof Borrower && p.getID() == id) {
                return (Borrower) p;
            }
        }
        return null;
    }
}

// Find Clerk Implementation
class FindClerkOperation implements LibraryOperation<Clerk> {
    @Override
    public Clerk execute(Library library, Object... args) {
        int id = (int) args[0];
        for (Person p : library.getPersons()) {
            if (p instanceof Clerk && p.getID() == id) {
                return (Clerk) p;
            }
        }
        return null;
    }
}

// Search Books Implementation
class SearchBooksOperation implements LibraryOperation<List<Book>> {
    @Override
    public List<Book> execute(Library library, Object... args) {
        int searchType = (int) args[0];
        String query = (String) args[1];
        List<Book> matchedBooks = new ArrayList<>();

        for (Book b : library.getBooks()) {
            if (searchType == 1 && b.getTitle().equalsIgnoreCase(query)) {
                matchedBooks.add(b);
            } else if (searchType == 2 && b.getSubject().equalsIgnoreCase(query)) {
                matchedBooks.add(b);
            } else if (searchType == 3 && b.getAuthor().equalsIgnoreCase(query)) {
                matchedBooks.add(b);
            }
        }
        return matchedBooks;
    }
}
