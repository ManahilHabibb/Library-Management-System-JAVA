package LMS;

// ISP: Separate interface for printing book information
interface BookPrintable {
    void printBookInfo(Book book);
}

// ISP: Separate interface for printing hold requests
interface HoldRequestPrintable {
    void printHoldRequests(HoldRequestRepository holdRequestRepository);
}

// BookPrinter only handles book-related printing
class BookPrinter implements BookPrintable {
    @Override
    public void printBookInfo(Book book) {
        System.out.println(book.getTitle() + "\t\t" + book.getAuthor() + "\t\t" + book.getSubject());
    }
}

// HoldRequestPrinter only handles hold request-related printing
class HoldRequestPrinter implements HoldRequestPrintable {
    @Override
    public void printHoldRequests(HoldRequestRepository holdRequestRepository) {
        if (!holdRequestRepository.getHoldRequests().isEmpty()) {
            System.out.println("\nHold Requests:");
            System.out.println("------------------------------------------------------------------");
            System.out.println("No.\tBook Title\tBorrower Name\tRequest Date");
            System.out.println("------------------------------------------------------------------");
            for (int i = 0; i < holdRequestRepository.getHoldRequests().size(); i++) {
                System.out.print(i + "-\t");
                print(holdRequestRepository.getHoldRequests().get(i));
            }
        } else {
            System.out.println("\nNo Hold Requests.");
        }
    }

    private void print(HoldRequest holdRequest) {
        System.out.println(holdRequest.getBookTitle() + "\t" + holdRequest.getBorrowerName() + "\t" + holdRequest.getRequestDate());
    }
}
