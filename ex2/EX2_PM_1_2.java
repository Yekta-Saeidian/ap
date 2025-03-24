package ex2;

import java.util.Scanner;

public class EX2_PM_1_2 {
    public static void main(String[] args) {
        System.out.println("enter a number: ");
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        scan.close();
        char[][] star = new char[k + 2][k + 2];
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                star[i][j] = ' ';
            }
        }
        for (int i = 0; i < k + 2; i++) {
            star[0][i] = '*';
            star[k + 1][i] = '*';
            star[i][0] = '*';
            star[i][k + 1] = '*';
        }
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(star[i][j] + " ");
            }
            System.out.println();
        }
    }
}
