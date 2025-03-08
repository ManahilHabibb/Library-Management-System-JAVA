package LMS;

public class HoldRequestService {

    private HoldRequestRepository repository;

    public HoldRequestService(HoldRequestRepository repository) {
        this.repository = repository;
    }

    // Processes the next hold request (if any) in the repository.
    public void processNextHoldRequest() {
        HoldRequest hr = repository.removeFirstHoldRequest();
        if (hr != null) {
            // Business logic for processing the hold request.
            System.out.println("Processing hold request for book: "
                    + hr.getBook().getTitle() + " by borrower: "
                    + hr.getBorrower().getName());
            // Additional processing logic can be added here.
        } else {
            System.out.println("No hold requests to process.");
        }
    }

    // Other business methods could be added here (e.g., cancel hold, check for
    // expired requests, etc.).
}
