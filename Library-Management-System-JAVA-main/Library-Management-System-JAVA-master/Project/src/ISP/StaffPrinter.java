package LMS;

public class StaffPrinter {

    /**
     * Prints the details of a Staff member.
     * This method displays the information without modifying any domain data.
     * It uses getters provided by Staff and Person.
     */
    public static void printInfo(Staff staff) {
        System.out.println("ID: " + staff.getID());
        System.out.println("Name: " + staff.getName());
        System.out.println("Address: " + staff.getAddress());
        System.out.println("Phone: " + staff.getPhoneNumber());
        System.out.println("Salary: " + staff.getSalary());
        System.out.println("-----------------------------------------");
    }
}
