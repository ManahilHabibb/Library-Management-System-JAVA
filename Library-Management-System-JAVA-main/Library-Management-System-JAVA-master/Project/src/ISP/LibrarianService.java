package LMS;

// Interface for defining librarian assignment policies
interface LibrarianAssignmentPolicy {
    boolean canAssignLibrarian(Library library, Librarian librarian);
}

class SingleLibrarianPolicy implements LibrarianAssignmentPolicy {
    @Override
    public boolean canAssignLibrarian(Library library, Librarian librarian) {
        return library.getLibrarian() == null;
    }
}

public class LibrarianService {
    private Library library;
    private LibrarianAssignmentPolicy assignmentPolicy;

    public LibrarianService(Library library, LibrarianAssignmentPolicy assignmentPolicy) {
        this.library = library;
        this.assignmentPolicy = assignmentPolicy;
    }

    /**
     * Adds a librarian to the library based on the assignment policy.
     *
     * @param lib The librarian to add.
     * @return true if the librarian was added successfully; false otherwise.
     */
    public boolean addLibrarian(Librarian lib) {
        if (assignmentPolicy.canAssignLibrarian(library, lib)) {
            library.setLibrarian(lib);
            library.getPersons().add(lib);
            return true;
        } else {
            System.out.println("\nLibrarian assignment policy prevents adding this librarian.");
            return false;
        }
    }
}
