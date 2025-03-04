package LMS;

import java.util.Date;

// Abstraction applied 
interface LoanRenewal {
    void renewLoan(Loan loan, Date newIssuedDate);
}

// Concrete Implementation using abstraction
class LoanRenewalService implements LoanRenewal {

    @Override
    public void renewLoan(Loan loan, Date newIssuedDate) {
        loan.setIssuedDate(newIssuedDate);
        System.out.println("The loan for book '" + loan.getBook().getTitle()
                + "' has been renewed with a new issued date: " + newIssuedDate);
    }
}
