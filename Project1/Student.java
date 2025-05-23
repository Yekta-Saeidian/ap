package Project1;

import java.time.LocalDate;

public class Student {
    private String firstName;
    private String lastName;
    private int id;
    private String field;
    private LocalDate dateOfMembership;

    public Student(String firstName, String lastName, int id, String field, LocalDate dateOfMembership) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.field = field;
        this.dateOfMembership = dateOfMembership;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }
}
