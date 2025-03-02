package LMS;

public class HoldRequestPrinter {

    /**
     * Prints the details of a HoldRequest.
     *
     * @param holdRequest The hold request to be printed.
     */
    public static void print(HoldRequest holdRequest) {
        if (holdRequest == null || holdRequest.getBook() == null || holdRequest.getBorrower() == null) {
            System.out.println("Invalid Hold Request data.");
            return;
        }

        String output = String.format("%-20s %-20s %s", 
                holdRequest.getBook().getTitle(), 
                holdRequest.getBorrower().getName(), 
                holdRequest.getRequestDate());
        
        System.out.println(output);
    }
}
