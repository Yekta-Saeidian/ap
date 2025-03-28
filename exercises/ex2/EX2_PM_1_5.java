package exercises.ex2;

import java.util.Scanner;
import java.util.Random;

public class EX2_PM_1_5 {
    public static void main(String[] args) {
        System.out.println("enter a number(k): ");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        char[][] place = new char[k + 2][k + 2];
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                place[i][j] = ' ';
            }
        }
        for (int i = 0; i < k + 2; i++) {
            place[0][i] = '*';
            place[k + 1][i] = '*';
            place[i][0] = '*';
            place[i][k + 1] = '*';
        }
        int a = 1, b = 1;
        place[a][b] = 'X';
        Random randomNum = new Random();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            int direction = randomNum.nextInt(4);
            switch (direction) {
                case 0:
                    if (Character.compare(place[a - 1][b], '*') != 0) {
                        place[a][b] = ' ';
                        a--;
                        place[a][b] = 'X';
                        System.out.println("up");
                    } else
                        System.out.println("hitting the game wall!");
                    break;
                case 1:
                    if (Character.compare(place[a][b + 1], '*') != 0) {
                        place[a][b] = ' ';
                        b++;
                        place[a][b] = 'X';
                        System.out.println("right");
                    } else
                        System.out.println("hitting the game wall!");
                    break;
                case 2:
                    if (Character.compare(place[a + 1][b], '*') != 0) {
                        place[a][b] = ' ';
                        a++;
                        place[a][b] = 'X';
                        System.out.println("down");
                    } else
                        System.out.println("hitting the game wall!");
                    break;
                case 3:
                    if (Character.compare(place[a][b - 1], '*') != 0) {
                        place[a][b] = ' ';
                        b--;
                        place[a][b] = 'X';
                        System.out.println("left");
                    } else
                        System.out.println("hitting the game wall!");
                    break;
            }
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    System.out.print(place[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
