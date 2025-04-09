package exercises.ex3;

import java.util.Scanner;

public class EX3_LM_1_3 {
    public static void main(String[] args) {

        Ex3_Students[] students = new Ex3_Students[4];
        students[0] = new Ex3_Students("Yekta", "Saeidian", 403463124, "Computer Science");
        students[1] = new Ex3_Students("Neda", "Rashtchi", 403463121, "Computer Science");
        students[2] = new Ex3_Students("Azam", "Ahahngar", 403463123, "Mathematics");
        students[3] = new Ex3_Students("Shahpoor", "Saeidian", 403463122, "Physics");

        System.out.println("1.search by name");
        System.out.println("2.search by ID");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();

        if (option == 1) {
            searchStudentByName(students);
        } else if (option == 2) {
            searchStudentByID(students);
        } else {
            System.out.println("Invalid option");
        }


    }

    private static void searchStudentByName(Ex3_Students[] students) {
        System.out.println("enter student first name");
        Scanner scan = new Scanner(System.in);
        String firstName = scan.nextLine().toLowerCase();
        System.out.println("enter student last name");
        String lastName = scan.nextLine().toLowerCase();

        boolean found = false;

        for (Ex3_Students student : students) {
            if (student.getFirstName().toLowerCase().contains(firstName)) {
                if (student.getLastName().toLowerCase().contains(lastName)) {
                    System.out.println("-------------------");
                    System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("ID: " + student.getID());
                    System.out.println("Field: " + student.getField());
                    System.out.println("-------------------");
                    found = true;
                }
            }
        }

        if (!found)
            System.out.println("Student not found");
    }

    private static void searchStudentByID(Ex3_Students[] students) {
        System.out.println("enter student ID");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        boolean found = false;

        for (Ex3_Students student : students) {
            if (student.getID() == id) {
                System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("ID: " + student.getID());
                System.out.println("Field: " + student.getField());
                System.out.println("-------------------");
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student not found");
    }
}

class Ex3_Students {
    private String firstName;
    private String lastName;
    private int ID;
    private String field;

    public Ex3_Students(String firstName, String lastName, int ID, String field) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.field = field;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }

    public String getField() {
        return field;
    }
}
