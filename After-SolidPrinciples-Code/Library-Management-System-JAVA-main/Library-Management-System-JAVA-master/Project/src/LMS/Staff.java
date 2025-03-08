package LMS;

// Staff class implementing SalaryUpdatable interface
public class Staff extends Person implements SalaryUpdatable {
    private double salary;

    public Staff(int id, String name, String address, int phone, double salary) {
        super(id, name, address, phone);
        this.salary = salary;
    }

    @Override
    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    public void performAction(StaffAction action) {
        action.execute(this);
    }
}


interface SalaryUpdatable {
    void setSalary(double newSalary);
    double getSalary();
}


interface StaffAction {
    void execute(SalaryUpdatable staff);
}


class UpdateSalaryAction implements StaffAction {
    private double newSalary;

    public UpdateSalaryAction(double newSalary) {
        this.newSalary = newSalary;
    }

    @Override
    public void execute(SalaryUpdatable staff) {
        System.out.println("Salary updated from " + staff.getSalary() + " to " + newSalary);
        staff.setSalary(newSalary);
    }
}
