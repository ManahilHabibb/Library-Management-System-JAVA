package LMS;

import java.util.ArrayList;

public class BorrowerRepository implements IBorrowerRepository {
    private ArrayList<Loan> loans = new ArrayList<>();
    private ArrayList<HoldRequest> holdRequests = new ArrayList<>();

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    @Override
    public void addHoldRequest(HoldRequest holdRequest) {
        holdRequests.add(holdRequest);
    }

    @Override
    public void removeHoldRequest(HoldRequest holdRequest) {
        holdRequests.remove(holdRequest);
    }

    @Override
    public ArrayList<HoldRequest> getHoldRequests() {
        return holdRequests;
    }
}
