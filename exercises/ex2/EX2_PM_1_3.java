package exercises.ex2;

import java.util.Scanner;
import java.util.Random;

public class EX2_PM_1_3 {
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
        int empty = k * k;
        int c = empty + 1;
        while (c > empty) {
            System.out.println("enter a number(c): ");
            c = scan.nextInt();
            if (c > empty) {
                System.out.println("number c is invalid!");
                System.out.println("try something lower than " + empty);
            }
        }
        Random randomDot = new Random();
        for (int i = 0; i < c; i++) {
            int row, column;
            do {
                row = randomDot.nextInt(k + 1);
                column = randomDot.nextInt(k + 1);
            } while (Character.compare(place[row][column], ' ') != 0);
            place[row][column] = '.';
        }
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(place[i][j] + " ");
            }
            System.out.println();
        }
    }
}
