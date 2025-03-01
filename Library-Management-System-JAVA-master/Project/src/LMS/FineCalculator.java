package LMS;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class FineCalculator {
    private FineStrategy fineStrategy;

    public FineCalculator(FineStrategy fineStrategy) {
        this.fineStrategy = fineStrategy;
    }

    public double computeFine(Loan loan, int bookReturnDeadline, double perDayFine) {
        return fineStrategy.calculateFine(loan, bookReturnDeadline, perDayFine);
    }
}

// Fine Strategy Interface
interface FineStrategy {
    double calculateFine(Loan loan, int bookReturnDeadline, double perDayFine);
}

// Standard Fixed Rate Fine Strategy
class FixedRateFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(Loan loan, int bookReturnDeadline, double perDayFine) {
        Date issuedDate = loan.getIssuedDate();
        Date returnDate = loan.getDateReturned() != null ? loan.getDateReturned() : new Date();

        long totalDays = ChronoUnit.DAYS.between(issuedDate.toInstant(), returnDate.toInstant());
        long overdueDays = totalDays - bookReturnDeadline;

        return (overdueDays > 0) ? overdueDays * perDayFine : 0.0;
    }
}

// Progressive Fine Strategy (Fine increases per day)
class ProgressiveFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(Loan loan, int bookReturnDeadline, double perDayFine) {
        Date issuedDate = loan.getIssuedDate();
        Date returnDate = loan.getDateReturned() != null ? loan.getDateReturned() : new Date();

        long totalDays = ChronoUnit.DAYS.between(issuedDate.toInstant(), returnDate.toInstant());
        long overdueDays = totalDays - bookReturnDeadline;
        double fine = 0.0;

        for (int i = 1; i <= overdueDays; i++) {
            fine += i * perDayFine; 
        }

        return fine;
    }
}
