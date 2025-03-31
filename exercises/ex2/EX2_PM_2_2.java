package exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class EX2_PM_2_2 {
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
        System.out.println("w = up , a = left , s = down , d = right , q = exit");
        int a = 1, b = 1;
        place[a][b] = 'X';
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
        int score = 0;
        long start = System.currentTimeMillis();
        while (true) {
            char response = scan.next().charAt(0);
            if (Character.compare(response, 'q') == 0) {
                System.out.println("exit");
                break;
            } else {
                switch (response) {
                    case 'w':
                        if (Character.compare(place[a - 1][b], '*') != 0) {
                            place[a][b] = ' ';
                            a--;
                            if (Character.compare(place[a][b], '.') == 0)
                                score++;
                            place[a][b] = 'X';
                            System.out.println("up");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 'a':
                        if (Character.compare(place[a][b - 1], '*') != 0) {
                            place[a][b] = ' ';
                            b--;
                            if (Character.compare(place[a][b], '.') == 0)
                                score++;
                            place[a][b] = 'X';
                            System.out.println("left");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 's':
                        if (Character.compare(place[a + 1][b], '*') != 0) {
                            place[a][b] = ' ';
                            a++;
                            if (Character.compare(place[a][b], '.') == 0)
                                score++;
                            place[a][b] = 'X';
                            System.out.println("down");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 'd':
                        if (Character.compare(place[a][b + 1], '*') != 0) {
                            place[a][b] = ' ';
                            b++;
                            if (Character.compare(place[a][b], '.') == 0)
                                score++;
                            place[a][b] = 'X';
                            System.out.println("right");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    default:
                        System.out.println("warning");
                        break;
                }
                if (score == c) {
                    long finish = System.currentTimeMillis();
                    for (int i = 0; i < k + 2; i++) {
                        for (int j = 0; j < k + 2; j++) {
                            System.out.print(place[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("total score: " + score);
                    System.out.println("time: " + (finish - start) + "ms");
                    break;
                }
            }
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    System.out.print(place[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("score: " + score);
        }
    }
}
