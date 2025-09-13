package ap.finalProject;

public class Employee {
    private String username;
    private String password;
    private String name;

    public Employee(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Name: " + name + " | Username: " + username;
    }
}