package LMS;

public class Clerk extends Staff {
    private int deskNo;
    private static int currentDeskNumber = 0;

    public Clerk(int id, String name, String address, int phone, double salary, int deskNo) {
        super(id, name, address, phone, salary);

        // If the provided deskNo is -1, assign a new unique desk number.
        if (deskNo == -1) {
            this.deskNo = currentDeskNumber++;
        } else {
            this.deskNo = deskNo;
        }
        currentDeskNumber++;
    }

    // Getter for desk number
    public int getDeskNo() {
        return deskNo;
    }

    // Other domain-related methods can be added here.
}
