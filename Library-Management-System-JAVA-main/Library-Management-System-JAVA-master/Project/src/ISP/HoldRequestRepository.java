package LMS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldRequestRepository {

    public List<HoldRequest> holdRequests;

    public HoldRequestRepository() {
        holdRequests = new ArrayList<>();
    }

    // Adds a hold request to the repository.
    public void addHoldRequest(HoldRequest hr) {
        holdRequests.add(hr);
    }

    // Removes a specific hold request.
    public boolean removeHoldRequest(HoldRequest hr) {
        return holdRequests.remove(hr);
    }

    // Removes and returns the first hold request in the list.
    public HoldRequest removeFirstHoldRequest() {
        if (!holdRequests.isEmpty()) {
            return holdRequests.remove(0);
        }
        return null;
    }

    // Returns an unmodifiable view of the hold requests.
    public List<HoldRequest> getHoldRequests() {
        return Collections.unmodifiableList(holdRequests);
    }
}
