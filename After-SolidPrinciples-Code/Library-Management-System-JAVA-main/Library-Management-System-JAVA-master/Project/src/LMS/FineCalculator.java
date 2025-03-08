package LMS;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class FineCalculator {
    private FineStrategy fineStrategy;

    public FineCalculator(FineStrategy fineStrategy) {
        this.fineStrategy = fineStrategy;
    }

    public double computeFine(Loan loan, int bookReturnDeadline, double perDayFine) {
        long overdueDays = calculateOverdueDays(loan, bookReturnDeadline);
        return fineStrategy.calculateFine(overdueDays, perDayFine);
    }

    private long calculateOverdueDays(Loan loan, int bookReturnDeadline) {
        Date issuedDate = loan.getIssuedDate();
        Date returnDate = (loan.getDateReturned() != null) ? loan.getDateReturned() : new Date();

        if (issuedDate == null) {
            throw new IllegalArgumentException("Issued date cannot be null");
        }

        long totalDays = ChronoUnit.DAYS.between(issuedDate.toInstant(), returnDate.toInstant());
        return Math.max(0, totalDays - bookReturnDeadline);
    }
}

// Fine Strategy Interface
interface FineStrategy {
    double calculateFine(long overdueDays, double perDayFine);
}

// Standard Fixed Rate Fine Strategy
class FixedRateFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(long overdueDays, double perDayFine) {
        return (overdueDays > 0) ? overdueDays * perDayFine : 0.0;
    }
}

// Progressive Fine Strategy (Fine increases per day)
class ProgressiveFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(long overdueDays, double perDayFine) {
        double fine = 0.0;
        for (int i = 1; i <= overdueDays; i++) {
            fine += i * perDayFine;
        }
        return fine;
    }
}
