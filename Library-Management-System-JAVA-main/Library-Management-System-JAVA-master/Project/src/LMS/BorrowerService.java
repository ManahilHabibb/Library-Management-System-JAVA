package LMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BorrowerService {

    public void updateBorrowerInfo(Borrower borrower) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nDo you want to update " + borrower.getName() + "'s Name? (y/n)");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.println("\nType New Name: ");
            borrower.setName(reader.readLine());
            System.out.println("\nThe name is successfully updated.");
        }

        System.out.println("\nDo you want to update " + borrower.getName() + "'s Address? (y/n)");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.println("\nType New Address: ");
            borrower.setAddress(reader.readLine());
            System.out.println("\nThe address is successfully updated.");
        }

        System.out.println("\nDo you want to update " + borrower.getName() + "'s Phone Number? (y/n)");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.println("\nType New Phone Number: ");
            borrower.setPhone(sc.nextInt());
            System.out.println("\nThe phone number is successfully updated.");
        }

        System.out.println("\nBorrower is successfully updated.");
    }
}
