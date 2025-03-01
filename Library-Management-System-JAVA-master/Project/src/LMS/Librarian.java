package LMS;

// Abstract base class for staff members with an office or desk assignment
abstract class OfficeAssignedStaff extends Staff {
    protected int officeNo;
    private static int currentOfficeNumber = 0;

    public OfficeAssignedStaff(int id, String name, String address, int phone, double salary, int officeNo) {
        super(id, name, address, phone, salary);
        if (officeNo == -1) {
            this.officeNo = currentOfficeNumber;
        } else {
            this.officeNo = officeNo;
        }
        currentOfficeNumber++;
    }

    // Getter for office
    public int getOfficeNo() {
        return officeNo;
    }
}

public class Librarian extends OfficeAssignedStaff {
    public Librarian(int id, String name, String address, int phone, double salary, int officeNo) {
        super(id, name, address, phone, salary, officeNo);
    }
}
