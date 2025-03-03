package LMS;

public class PersonPrinter {

    /**
     * Prints the details of a Person.
     * 
     * @param person The person whose details are to be printed.
     */
    public static void printInfo(Person person) {
        System.out.println("-----------------------------------------");
        System.out.println("The details are:");
        System.out.println("ID: " + person.getID());
        System.out.println("Name: " + person.getName());
        System.out.println("Address: " + person.getAddress());
        System.out.println("Phone No: " + person.getPhoneNumber());
        System.out.println("-----------------------------------------");
    }
}
