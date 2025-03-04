package LMS;

import java.util.Scanner;

public class FinePaymentService {
    private FineComputationService calculator; 

    // DIP Applied Through Constructor 
    public FinePaymentService(FineComputationService calculator) {
        this.calculator = calculator;
    }

    /**
     * Processes fine payment for a loan.
     *
     * @param loan               The loan to process.
     * @param bookReturnDeadline Allowed number of days before fine.
     * @param perDayFine         Fine amount per day.
     * @param scanner            Scanner for user input (passed externally).
     * @return true if the fine was paid; false otherwise.
     */
    public boolean processFinePayment(Loan loan, int bookReturnDeadline, double perDayFine, Scanner scanner) {
        double fine = calculator.computeFine(loan, bookReturnDeadline, perDayFine); 

        if (fine <= 0) {
            System.out.println("No fine is generated.");
            loan.setFinePaid(true);
            return true;
        }

        System.out.println("Total Fine: Rs " + fine);
        System.out.println("Do you want to pay? (y/n)");
        String choice = scanner.next();
        boolean paid = choice.equalsIgnoreCase("y");
        loan.setFinePaid(paid);
        return paid;
    }
}

// Interface introduced for Dependency Inversion Principle
interface FineComputationService {
    double computeFine(Loan loan, int bookReturnDeadline, double perDayFine);
}

//  Now FineCalculator follows the dependency inversion principle
class FineCalculator implements FineComputationService {
    private FineStrategy fineStrategy;

    public FineCalculator(FineStrategy fineStrategy) {
        this.fineStrategy = fineStrategy;
    }

    @Override
    public double computeFine(Loan loan, int bookReturnDeadline, double perDayFine) {
        return fineStrategy.calculateFine(0, perDayFine); 
    }
}
