
package LMS;
// dependency inversion principle applies to existing interfaces to depend on abstraction
interface AddressUpdateAction {
    void updateAddress(PersonUpdater updater, String newAddress);
}

interface PhoneUpdateAction {
    void updatePhone(PersonUpdater updater, int newPhone);
}

interface NameUpdateAction {
    void updateName(PersonUpdater updater, String newName);
}

// Created an abstraction (interface) for Person
interface PersonUpdater {
    void setAddress(String newAddress);
    void setPhone(int newPhone);
    void setName(String newName);
}

// Implement PersonUpdater in Person class
public abstract class Person implements PersonUpdater {
    protected int id;
    protected String password;
    protected String name;
    protected String address;
    protected int phoneNo;
    
    static int currentIdNumber = 0;

    public Person(int idNum, String name, String address, int phoneNum) {
        currentIdNumber++;
        this.id = (idNum == -1) ? currentIdNumber : idNum;
        this.password = Integer.toString(this.id);
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNum;
    }

    // Implementing PersonUpdater methods
    @Override
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    @Override
    public void setPhone(int newPhone) {
        this.phoneNo = newPhone;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    // Getter methods remain unchanged
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
}

// Update concrete classes to use abstraction
class UpdateAddressAction implements AddressUpdateAction {
    @Override
    public void updateAddress(PersonUpdater updater, String newAddress) {
        updater.setAddress(newAddress);
        System.out.println("Address updated to: " + newAddress);
    }
}

class UpdatePhoneAction implements PhoneUpdateAction {
    @Override
    public void updatePhone(PersonUpdater updater, int newPhone) {
        updater.setPhone(newPhone);
        System.out.println("Phone number updated to: " + newPhone);
    }
}

class UpdateNameAction implements NameUpdateAction {
    @Override
    public void updateName(PersonUpdater updater, String newName) {
        updater.setName(newName);
        System.out.println("Name updated to: " + newName);
    }
}
