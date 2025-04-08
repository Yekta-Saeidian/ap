package exercises.ex2;

import java.io.*;
import java.util.Random;

public class PacmanEngine {

    private char[][] place;
    private int k;
    private int c;
    private int a, b;
    private int score;
    private long startTime;
    private boolean gameOver;

    public PacmanEngine(int k, int c) {
        this.k = k;
        this.c = c;
        initializeGame();
    }

    private void initializeGame() {
        place = new char[k+2][k+2];

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

        a = b = 1;
        place[a][b] = 'Z';

        Random random = new Random();
        for (int i = 0; i < c; i++) {
            int row, column;
            do {
                row = random.nextInt(k) + 1;
                column = random.nextInt(k) + 1;
            } while (place[row][column] != ' ');
            place[row][column] = '.';
        }

        score = 0;
        startTime = System.currentTimeMillis();
        gameOver = false;
    }
}
