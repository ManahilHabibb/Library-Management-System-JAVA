package LMS;

import java.util.Scanner;

public class FinePaymentService {
    private FineCalculator calculator;

    public FinePaymentService(FineStrategy fineStrategy) {
        this.calculator = new FineCalculator(fineStrategy);
    }

    /**
     * Processes fine payment for a loan.
     * 
     * @param loan               The loan to process.
     * @param bookReturnDeadline Allowed number of days before fine.
     * @param perDayFine         Fine amount per day.
     * @return true if the fine was paid; false otherwise.
     */
    public boolean processFinePayment(Loan loan, int bookReturnDeadline, double perDayFine) {
        double fine = calculator.computeFine(loan, bookReturnDeadline, perDayFine);
        if (fine <= 0) {
            System.out.println("No fine is generated.");
            loan.setFinePaid(true);
            return true;
        }

        System.out.println("Total Fine: Rs " + fine);
        System.out.println("Do you want to pay? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        boolean paid = choice.equalsIgnoreCase("y");
        loan.setFinePaid(paid);
        return paid;
    }
}
