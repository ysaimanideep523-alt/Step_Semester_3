class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().isEmpty()
                || memberId.length() < 4) {
            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                "Borrow limit must be positive"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        // Polymorphic method
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        // Student-specific display
    }
}

public class Problem4 {

    public static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {

            if (member instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) member;

                report.append(
                    "Student | Course: "
                    + student.getCourse()
                    + " | Books: "
                    + student.getBooksBorrowed()
                    + " [Course via downcast: "
                    + student.getCourse()
                    + "] | "
                );

            } else {

                report.append(
                    "General | Books: "
                    + member.getBooksBorrowed()
                    + " | "
                );
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB05", 3);

        StudentMember student =
            new StudentMember(
                "STU6", 3, "ECE"
            );

        LibraryMember[] members = {
            general,
            student
        };

        System.out.println(
            batchPrint(members)
        );
    }
}