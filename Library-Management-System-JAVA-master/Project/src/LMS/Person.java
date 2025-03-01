package LMS;

public abstract class Person {
    protected int id; 
    protected String password; 
    protected String name; 
    protected String address; 
    protected int phoneNo; 

    static int currentIdNumber = 0; 

    // Constructor initializes the person data.
    public Person(int idNum, String name, String address, int phoneNum) {
        currentIdNumber++;

        // If idNum is -1, assign a new unique id; otherwise, use the given id.
        if (idNum == -1) {
            this.id = currentIdNumber;
        } else {
            this.id = idNum;
        }

        // For simplicity, we generate a default password using the id.
        this.password = Integer.toString(this.id);
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNum;
    }

    // Setter methods (for updating the data)
    public void setAddress(String a) {
        address = a;
    }

    public void setPhone(int p) {
        phoneNo = p;
    }

    public void setName(String n) {
        name = n;
    }

    // Getter methods (for accessing the data)
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNo;
    }

    public int getID() {
        return id;
    }

    public static void setIDCount(int n) {
        currentIdNumber = n;
    }

    // Open for extension: Person actions
    public void performAction(PersonAction action) {
        action.execute(this);
    }
}

// Abstract Action Interface
interface PersonAction {
    void execute(Person person);
}

// Concrete Actions
class UpdateAddressAction implements PersonAction {
    private String newAddress;

    public UpdateAddressAction(String newAddress) {
        this.newAddress = newAddress;
    }

    @Override
    public void execute(Person person) {
        person.setAddress(newAddress);
        System.out.println(person.getName() + "'s address updated to: " + newAddress);
    }
}

class UpdatePhoneAction implements PersonAction {
    private int newPhoneNo;

    public UpdatePhoneAction(int newPhoneNo) {
        this.newPhoneNo = newPhoneNo;
    }

    @Override
    public void execute(Person person) {
        person.setPhone(newPhoneNo);
        System.out.println(person.getName() + "'s phone number updated to: " + newPhoneNo);
    }
}

class UpdateNameAction implements PersonAction {
    private String newName;

    public UpdateNameAction(String newName) {
        this.newName = newName;
    }

    @Override
    public void execute(Person person) {
        person.setName(newName);
        System.out.println("Person's name updated to: " + newName);
    }
}
