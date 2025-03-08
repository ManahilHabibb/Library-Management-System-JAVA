package LMS;

public class LibrarianService {
    private LibraryInterface library;  // Depend on abstraction, not concrete Library class
    private LibrarianAssignmentPolicy assignmentPolicy;

    public LibrarianService(LibraryInterface library, LibrarianAssignmentPolicy assignmentPolicy) {
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


