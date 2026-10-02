abstract class Employee {

    protected int employeeId;
    protected String name;

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public abstract boolean canTakeLeave(int days);

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int employeeId, String name) {
        super(employeeId, name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(int employeeId, String name) {
        super(employeeId, name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {

    public Contractor(int employeeId, String name) {
        super(employeeId, name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {

    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(
        Employee employee,
        String startDate,
        String endDate,
        int days
    ) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot approve. Current status: " + status
            );
            return;
        }

        if (!employee.canTakeLeave(days)) {
            System.out.println(
                "Leave policy does not allow " +
                days + " days for " +
                employee.getName()
            );
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
            employee.getName() +
            "'s leave request (" +
            startDate + " - " +
            endDate +
            ") approved."
        );

        System.out.println("Status: " + status);
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot reject. Current status: " + status
            );
            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
            employee.getName() +
            "'s leave request (" +
            startDate + " - " +
            endDate +
            ") rejected."
        );

        System.out.println("Status: " + status);
    }

    public void changeStatus(LeaveStatus newStatus) {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                "Cannot change leave request status from " +
                status + " to " + newStatus + "."
            );

            return;
        }

        status = newStatus;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}

class LeaveManager {

    public void submitRequest(LeaveRequest request,
                              Employee employee,
                              String startDate,
                              String endDate) {

        System.out.println(
            "Leave request submitted for " +
            employee.getName() +
            " (" +
            startDate +
            " - " +
            endDate +
            ")."
        );

        System.out.println("Status: " + request.getStatus());
    }
}

public class EmployeeLeaveSystem {

    public static void main(String[] args) {

        Employee john =
            new FullTimeEmployee(101, "John");

        Employee jane =
            new PartTimeEmployee(102, "Jane");

        LeaveRequest johnRequest =
            new LeaveRequest(
                john,
                "Jan 1",
                "Jan 5",
                5
            );

        LeaveRequest janeRequest =
            new LeaveRequest(
                jane,
                "Feb 10",
                "Feb 11",
                2
            );

        LeaveManager manager = new LeaveManager();

        
        manager.submitRequest(
            johnRequest,
            john,
            "Jan 1",
            "Jan 5"
        );

        
        johnRequest.approve();

        
        manager.submitRequest(
            janeRequest,
            jane,
            "Feb 10",
            "Feb 11"
        );

        
        janeRequest.reject();

       
        johnRequest.changeStatus(LeaveStatus.PENDING);
    }
}