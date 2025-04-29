package exercises.ex4;

import java.util.Scanner;

public class EX4_E3_4 {
    public static void main(String[] args) {

        HallwayLight light = new HallwayLight();
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
                    light.toggleFirstSwitch();
                    System.out.println("First switch toggled.");
                    System.out.println("Lamp is " + (light.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 2:
                    light.toggleSecondSwitch();
                    System.out.println("Second switch toggled.");
                    System.out.println("Lamp is " + (light.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 3:
                    System.out.println("\nCurrent States:");
                    System.out.println("First switch: " + (light.getFirstSwitchState() == 1 ? "UP" : "DOWN"));
                    System.out.println("Second switch: " + (light.getSecondSwitchState() == 1 ? "UP" : "DOWN"));
                    System.out.println("Lamp is " + (light.getLampState() == 1 ? "ON" : "OFF"));
                    break;
                case 4:
                    System.out.println("\nexit");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid command! Please enter 1-4.");
            }
        }
    }
}

class HallwayLight {
    private int firstSwitch;
    private int secondSwitch;

    public HallwayLight() {
        firstSwitch = 0;
        secondSwitch = 0;
    }

    public int getFirstSwitchState() {
        return firstSwitch;
    }

    public int getSecondSwitchState() {
        return secondSwitch;
    }

    public int getLampState() {
        return (firstSwitch + secondSwitch) % 2;
    }

    public void toggleFirstSwitch() {
        firstSwitch = 1 - firstSwitch;
    }

    public void toggleSecondSwitch() {
        secondSwitch = 1 - secondSwitch;
    }
}
