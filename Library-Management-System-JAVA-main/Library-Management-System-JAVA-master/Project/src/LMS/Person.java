package LMS;

//  Abstract class Person
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

        // Generate a default password using the id.
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
}

//  Interface for Updating Address
interface AddressUpdateAction {
    void updateAddress(Person person, String newAddress);
}

//  Interface for Updating Phone Number
interface PhoneUpdateAction {
    void updatePhone(Person person, int newPhone);
}

//  Interface for Updating Name
interface NameUpdateAction {
    void updateName(Person person, String newName);
}

//  Concrete Action for Address Update
class UpdateAddressAction implements AddressUpdateAction {
    @Override
    public void updateAddress(Person person, String newAddress) {
        person.setAddress(newAddress);
        System.out.println(person.getName() + "'s address updated to: " + newAddress);
    }
}

//  Concrete Action for Phone Update
class UpdatePhoneAction implements PhoneUpdateAction {
    @Override
    public void updatePhone(Person person, int newPhone) {
        person.setPhone(newPhone);
        System.out.println(person.getName() + "'s phone number updated to: " + newPhone);
    }
}

//  Concrete Action for Name Update
class UpdateNameAction implements NameUpdateAction {
    @Override
    public void updateName(Person person, String newName) {
        person.setName(newName);
        System.out.println("Person's name updated to: " + newName);
    }
}
