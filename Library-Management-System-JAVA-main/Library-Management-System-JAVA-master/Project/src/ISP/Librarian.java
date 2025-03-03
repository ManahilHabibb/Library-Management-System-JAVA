package LMS;

// Abstract base class for staff members with an office or desk assignment
abstract class OfficeAssignedStaff extends Staff {
    protected int officeNo;

    public OfficeAssignedStaff(int id, String name, String address, int phone, double salary, int officeNo) {
        super(id, name, address, phone, salary);
        this.officeNo = officeNo; // Removed static counter dependency
    }

    // Getter for office
    public int getOfficeNo() {
        return officeNo;
    }
}

public class Librarian extends OfficeAssignedStaff {
    private static int librarianOfficeNumber = 0;

    public Librarian(int id, String name, String address, int phone, double salary, int officeNo) {
        super(id, name, address, phone, salary, (officeNo == -1) ? librarianOfficeNumber++ : officeNo);
    }
}
