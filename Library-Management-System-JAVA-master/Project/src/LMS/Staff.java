package LMS;

public class Staff extends Person {
    private double salary;

    public Staff(int id, String name, String address, int phone, double salary) {
        super(id, name, address, phone);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    // Open for extension: Staff actions
    public void performAction(StaffAction action) {
        action.execute(this);
    }
}

// Abstract Action Interface
interface StaffAction {
    void execute(Staff staff);
}

// Concrete Actions
class UpdateSalaryAction implements StaffAction {
    private double newSalary;

    public UpdateSalaryAction(double newSalary) {
        this.newSalary = newSalary;
    }

    @Override
    public void execute(Staff staff) {
        System.out.println(staff.getName() + "'s salary updated from " + staff.getSalary() + " to " + newSalary);
    }
}
