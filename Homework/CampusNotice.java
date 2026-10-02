import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[Email → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[SMS → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[App → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class Student {

    private String name;
    private String department;

    private List<NotificationChannel> channels;

    Student(String name, String department) {

        this.name = name;
        this.department = department;
        channels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Notice title is required."
            );
        }

        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one target department is required."
            );
        }

        this.title = title;
        this.departments = departments;
    }

    public String getTitle() {
        return title;
    }

    public boolean targets(String department) {
        return departments.contains(department);
    }

    public Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {

    private List<Student> students;

    NoticeBoard(List<Student> students) {
        this.students = students;
    }

    public void postNotice(Notice notice) {

        System.out.print(
                "Notice '" +
                notice.getTitle() +
                "' posted to "
        );

        int count = 0;

        for (String department :
                notice.getDepartments()) {

            if (count > 0) {
                System.out.print(", ");
            }

            System.out.print(department);
            count++;
        }

        System.out.println(".");

        for (Student student : students) {

            if (notice.targets(
                    student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNotice {

    public static void main(String[] args) {

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addChannel(
                new EmailChannel());

        asha.addChannel(
                new AppChannel());

        ravi.addChannel(
                new SmsChannel());

        List<Student> students =
                new ArrayList<>();

        students.add(asha);
        students.add(ravi);

        NoticeBoard board =
                new NoticeBoard(students);

        Set<String> cse =
                new HashSet<>();

        cse.add("CSE");

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        cse
                );

        board.postNotice(notice1);

        Set<String> cseEce =
                new HashSet<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        cseEce
                );

        board.postNotice(notice2);

        try {

            Set<String> empty =
                    new HashSet<>();

            Notice invalid =
                    new Notice(
                            "Sports Day",
                            empty
                    );

            board.postNotice(invalid);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Cannot post notice: " +
                    e.getMessage()
            );
        }
    }
}