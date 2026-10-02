import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] correctedBooks =
                Arrays.copyOf(bookIds, bookIds.length);

        correctedBooks[index] = newId;

        return new LoanReceipt(memberId, correctedBooks);
    }
}


// Reference-only loan receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


// Circulation Ledger
class CirculationLedger {

    private static String branchCode;

    static {
        branchCode = "PT-MAIN";
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int regularCount = 0;
        int referenceCount = 0;
        int skippedCount = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                skippedCount++;
                continue;
            }

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceCount++;
            } else {
                regularCount++;
            }
        }

        return "Branch=" + branchCode
                + ", Regular=" + regularCount
                + ", ReferenceOnly=" + referenceCount
                + ", Skipped=" + skippedCount;
    }
}


// Main class
public class LoanReceipt {

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        // Test defensive copy
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println("Original: "
                + Arrays.toString(r.getBookIds()));

        // Test correction
        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println("Corrected: "
                + Arrays.toString(corrected.getBookIds()));

        // Reference-only receipt
        ReferenceOnlyLoanReceipt referenceReceipt =
                new ReferenceOnlyLoanReceipt(
                        "LIB-9001",
                        new String[]{"REF-201", "REF-202"},
                        "ROOM-3"
                );

        System.out.println("Reference room: "
                + referenceReceipt.getRoomNumber());

        // Nightly circulation
        LoanReceipt[] receipts = {
                r,
                corrected,
                referenceReceipt,
                null
        };

        System.out.println("\nNightly circulation:");

        System.out.println(
                CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}