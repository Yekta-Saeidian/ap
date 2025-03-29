package exercises.ex2;

import java.util.Scanner;

public class EX2_PM_2_1 {
    public static void main(String[] args) {
        System.out.println("enter a number(k): ");
        System.out.println("w = up , a = left , s = down , d = right , q = exit");
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
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(place[i][j] + " ");
            }
            System.out.println();
        }
        while (true) {
            char response = scan.next().charAt(0);
            if (Character.compare(response, 'q') == 0) {
                System.out.println("exit");
                break;
            } else {
                switch (response) {
                    case 'w':
                        //System.out.println("up");
                        if (Character.compare(place[a - 1][b], '*') != 0) {
                            place[a][b] = ' ';
                            a--;
                            place[a][b] = 'X';
                            System.out.println("up");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 'a':
                        //System.out.println("left");
                        if (Character.compare(place[a][b - 1], '*') != 0) {
                            place[a][b] = ' ';
                            b--;
                            place[a][b] = 'X';
                            System.out.println("left");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 's':
                        //System.out.println("down");
                        if (Character.compare(place[a + 1][b], '*') != 0) {
                            place[a][b] = ' ';
                            a++;
                            place[a][b] = 'X';
                            System.out.println("down");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    case 'd':
                        //System.out.println("right");
                        if (Character.compare(place[a][b + 1], '*') != 0) {
                            place[a][b] = ' ';
                            b++;
                            place[a][b] = 'X';
                            System.out.println("right");
                        } else
                            System.out.println("hitting the game wall!");
                        break;
                    default:
                        System.out.println("warning");
                        break;
                }
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
