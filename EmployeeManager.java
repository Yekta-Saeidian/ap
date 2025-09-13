package ap.finalProject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employees;
    private FileManager fileManager;

    public EmployeeManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.employees = fileManager.loadEmployees();
    }

    public void registerEmployee(String username, String password, String name) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Employee newEmployee = new Employee(username, password, name);
        employees.add(newEmployee);
        fileManager.saveEmployees(employees);
        System.out.println("Employee registration completed successfully.");
    }

    public Employee authenticateEmployee(String username, String password) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }

    public void changeEmployeePassword(Employee employee, String newPassword) {
        employee.setPassword(newPassword);
        fileManager.saveEmployees(employees);
        System.out.println("Password changed successfully.");
    }

    public void displayEmployees() {
        System.out.println("\n--- List of Registered Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees have registered yet.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private boolean isUsernameTaken(String username) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public Employee getEmployeeByUsername(String username) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }
        return null;
    }
}