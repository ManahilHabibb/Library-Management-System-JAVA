package LMS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldRequestRepository {

    private final List<HoldRequest> holdRequests; // Encapsulation improved

    public HoldRequestRepository() {
        this.holdRequests = new ArrayList<>();
    }

    // Adds a hold request to the repository.
    public void addHoldRequest(HoldRequest hr) {
        if (hr != null) {
            holdRequests.add(hr);
        } else {
            System.out.println("Invalid HoldRequest: Cannot add null.");
        }
    }

    // Removes a specific hold request.
    public boolean removeHoldRequest(HoldRequest hr) {
        if (hr == null) {
            System.out.println("Invalid HoldRequest: Cannot remove null.");
            return false;
        }
        return holdRequests.remove(hr);
    }

    // Removes and returns the first hold request in the list.
    public HoldRequest removeFirstHoldRequest() {
        if (holdRequests.isEmpty()) {
            System.out.println("No HoldRequests available to remove.");
            return null;
        }
        return holdRequests.remove(0);
    }

    // Returns an unmodifiable view of the hold requests.
    public List<HoldRequest> getHoldRequests() {
        return Collections.unmodifiableList(holdRequests);
    }
}
