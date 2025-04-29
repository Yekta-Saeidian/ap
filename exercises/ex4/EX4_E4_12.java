package exercises.ex4;

public class EX4_E4_12 {
    public static void main(String[] args) {
        Employee harry = new Employee("Hacker, Harry", 50000);

        System.out.println("Employee Name: " + harry.getName());
        System.out.println("Employee Salary: " + harry.getSalary() + "$");

        harry.raiseSalary(10);
        System.out.println("\nAfter 10% Raise:");
        System.out.println("Updated Salary: " + harry.getSalary() + "$");
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String employeeName, double currentSalary) {
        name = employeeName;
        salary = currentSalary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double percentage) {
        if (percentage > 0) {
            salary += (salary * (percentage / 100));
        }
    }
}
