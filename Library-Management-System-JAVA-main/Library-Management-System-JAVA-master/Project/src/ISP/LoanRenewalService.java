package LMS;

import java.util.Date;

public class LoanRenewalService {

    /**
     * Renews the loan by updating its issued date.
     * 
     * @param loan          The loan to renew.
     * @param newIssuedDate The new issued date.
     */
    public void renewLoan(Loan loan, Date newIssuedDate) {
        loan.setIssuedDate(newIssuedDate);
        System.out.println("The loan for book '" + loan.getBook().getTitle()
                + "' has been renewed with a new issued date: " + newIssuedDate);
    }

}
