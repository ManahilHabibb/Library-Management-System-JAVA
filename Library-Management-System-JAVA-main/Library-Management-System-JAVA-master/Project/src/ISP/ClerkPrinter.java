package LMS;

public class ClerkPrinter {

    /**
     * Prints the details of a Clerk.
     * 
     * @param clerk The clerk whose information is to be printed.
     */
    public static void printInfo(Clerk clerk) {
        // Assuming Staff class has getters for id, name, address, phone, and salary.
        System.out.println("ID: " + clerk.getID());
        System.out.println("Name: " + clerk.getName());
        System.out.println("Address: " + clerk.getAddress());
        System.out.println("Phone: " + clerk.getPhoneNumber());
        System.out.println("Salary: " + clerk.getSalary());
        System.out.println("Desk Number: " + clerk.getDeskNo());
    }
}
