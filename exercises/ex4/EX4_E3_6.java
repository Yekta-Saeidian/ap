package exercises.ex4;

import java.util.Scanner;

public class EX4_E3_6 {
    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        Scanner scan = new Scanner(System.in);

        System.out.println("Hallway Light Control System");
        System.out.println("Commands:");
        System.out.println("1. Toggle first switch");
        System.out.println("2. Toggle second switch");
        System.out.println("3. Check all states");
        System.out.println("4. Exit");

        while (true) {
            System.out.print("\nEnter command (1-4): ");
            int command = scan.nextInt();

            switch (command) {
                case 1:
                    circuit.toggleSwitch(1);
                    System.out.println("First switch toggled.");
                    System.out.println("Lamp is " + (circuit.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 2:
                    circuit.toggleSwitch(2);
                    System.out.println("Second switch toggled.");
                    System.out.println("Lamp is " + (circuit.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 3:
                    System.out.println("\nCurrent States:");
                    System.out.println("First switch: " + (circuit.getSwitchState(1) == 1 ? "UP" : "DOWN"));
                    System.out.println("Second switch: " + (circuit.getSwitchState(2) == 1 ? "UP" : "DOWN"));
                    System.out.println("Lamp is " + (circuit.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 4:
                    System.out.println("\nExit");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid command! Please enter 1-4.");
            }
        }
    }
}

class Circuit {

    private int switchesState;

    public Circuit() {
        switchesState = 0;
    }

    public int getSwitchState(int switchNum) {
        if (switchNum == 1)
            return switchesState & 1;

        else if (switchNum == 2)
            return (switchesState >> 1) & 1;

        else
            throw new IllegalArgumentException("Invalid switch number! Use 1 or 2.");

    }

    public int getLampState() {
        return (getSwitchState(1) ^ getSwitchState(2));
    }

    public void toggleSwitch(int switchNum) {
        if (switchNum == 1)
            switchesState ^= 1;

        else if (switchNum == 2)
            switchesState ^= (1 << 1);

        else
            throw new IllegalArgumentException("Invalid switch number! Use 1 or 2.");

    }
}
