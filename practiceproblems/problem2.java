class Employee {
    String empId;
    double salary;

    Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    void raiseSalary(double salary) {
        this.salary += salary;
    }
}

public class problem2 {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        for (int i = 0; i < employees.length; i++) {
            employees[i].raiseSalary(5000);

            System.out.println(employees[i].empId
                    + " | Final Salary: Rs "
                    + employees[i].salary);
        }
    }
}