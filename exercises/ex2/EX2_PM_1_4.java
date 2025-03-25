package exercises.ex2;

import java.util.Scanner;

public class EX2_PM_1_4 {
    public static void main(String[] args) {
        //int up = 0, down = 0, left = 0, right = 0, exit = 0, warning = 0;
        System.out.println("w = up , a = left , s = down , d = right , q = exit");
        System.out.println("press e to end the program");
        Scanner scan = new Scanner(System.in);
        while (true) {
            char response = scan.next().charAt(0);
            if (response == 'e') break;
            else {
                switch (response) {
                    case 'w':
                        System.out.println("up");
                        break;
                    case 'a':
                        System.out.println("left");
                        break;
                    case 's':
                        System.out.println("down");
                        break;
                    case 'd':
                        System.out.println("right");
                        break;
                    case 'q':
                        System.out.println("exit");
                        break;
                    default:
                        System.out.println("warning");
                        break;
                }
            }
        }
    }
}
