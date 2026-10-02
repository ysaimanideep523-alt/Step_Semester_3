abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected int dueDay;

    Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getLateDays(int submissionDay) {
        return Math.max(0, submissionDay - dueDay);
    }

    public abstract double applyPenalty(double marks, int lateDays);
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 0.10;
        return Math.max(0, marks * (1 - penalty));
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 0.20;
        return Math.max(0, marks * (1 - penalty));
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private SubmissionStatus status;
    private double finalMarks;

    Submission(Student student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = SubmissionStatus.SUBMITTED;
    }

    public void grade(double awardedMarks) {

        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot grade again.");
            return;
        }

        int lateDays = assignment.getLateDays(submissionDay);

        finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
        status = SubmissionStatus.GRADED;

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks());
        } else {
            double penalty = lateDays * 20;

            if (assignment instanceof CodingAssignment) {
                penalty = lateDays * 10;
            }

            System.out.printf(
                    "%s graded: %.0f/%d after %.0f%% late penalty. Status: Graded.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks(),
                    penalty
            );
        }
    }

    public boolean isGraded() {
        return status == SubmissionStatus.GRADED;
    }
}

public class AssignmentPortal {

    public static Submission submit(
            Student student,
            Assignment assignment,
            int submissionDay) {

        Submission submission =
                new Submission(student, assignment, submissionDay);

        int lateDays = assignment.getLateDays(submissionDay);

        if (lateDays == 0) {
            System.out.println(student.getName() +
                    "'s submission for '" +
                    assignment.getTitle() +
                    "' received (on time).");
        } else {
            System.out.println(student.getName() +
                    "'s submission for '" +
                    assignment.getTitle() +
                    "' received (" +
                    lateDays + " days late).");
        }

        System.out.println("Status: Submitted.");

        return submission;
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab", 50, 10);

        Assignment written =
                new WrittenAssignment(
                        "Design Essay", 50, 12);

        Submission s1 =
                submit(asha, coding, 10);

        Submission s2 =
                submit(ravi, written, 14);

        s1.grade(45);
        s2.grade(40);

        if (s1.isGraded()) {
            System.out.println(
                    "Cannot resubmit: 'Linked List Lab' has already been graded."
            );
        }
    }
}