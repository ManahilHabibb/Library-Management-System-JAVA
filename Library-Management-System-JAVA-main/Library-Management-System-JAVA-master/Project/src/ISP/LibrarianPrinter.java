package LMS;

public class LibrarianPrinter {

    /**
     * Prints the librarian's details.
     * Assumes that Staff provides getters for ID, name, address, phone number, and
     * salary.
     */
    public static void printInfo(Librarian librarian) {
        System.out.println("ID: " + librarian.getID());
        System.out.println("Name: " + librarian.getName());
        System.out.println("Address: " + librarian.getAddress());
        System.out.println("Phone: " + librarian.getPhoneNumber());
        System.out.println("Salary: " + librarian.getSalary());
        System.out.println("Office Number: " + librarian.getOfficeNo());
    }
}
