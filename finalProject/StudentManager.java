package ap.finalProject;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private FileManager fileManager;

    public StudentManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.students = fileManager.loadStudents();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Student newStudent = new Student(name, studentId, username, password);
        students.add(newStudent);
        fileManager.saveStudents(students);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateStudent(String username, String password) {
        for (Student student : students) {
            if (student.getUsername().equals(username) && student.getPassword().equals(password) && student.isActive()) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");
        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            student.displayInfo();
        }
    }

    private boolean isUsernameTaken(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int getStudentCount() {
        return students.size();
    }

    public Student getStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    public void toggleStudentStatus(String username) {
        Student student = getStudentByUsername(username);
        if (student != null) {
            student.setActive(!student.isActive());
            fileManager.saveStudents(students);
            System.out.println("Student status changed to: " + (student.isActive() ? "Active" : "Inactive"));
        } else {
            System.out.println("Student not found!");
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void displayAllStudentsWithStatus() {
        System.out.println("\n--- All Students with Status ---");
        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println("Username: " + student.getUsername() +
                    " | Name: " + student.getName() +
                    " | Status: " + (student.isActive() ? "Active" : "Inactive"));
        }
    }
}