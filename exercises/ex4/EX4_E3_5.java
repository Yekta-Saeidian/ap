package exercises.ex4;

public class EX4_E3_5 {
    public static void main(String[] args) {

        System.out.println("Testing all switch combinations for HallwayLight:");
        System.out.println("------------------------------------------------");
        System.out.println("| Switch 1 | Switch 2 | Expected Lamp | Actual Lamp |");
        System.out.println("------------------------------------------------");

        testCombination(0, 0);
        testCombination(0, 1);
        testCombination(1, 0);
        testCombination(1, 1);
    }

    private static void testCombination(int switch1, int switch2) {
        HallwayLight light = new HallwayLight();

        if (switch1 == 1) light.toggleFirstSwitch();
        if (switch2 == 1) light.toggleSecondSwitch();

        int expectedLamp = (switch1 + switch2) % 2;
        int actualLamp = light.getLampState();

        System.out.println("|    " + switch1 + "     |    " + switch2 + "     |       " + expectedLamp + "       |      " + actualLamp + "      |");
    }
}
