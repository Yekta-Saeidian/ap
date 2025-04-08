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
        place = new char[k + 2][k + 2];

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

    public void printMatrix() {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                System.out.print(place[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printScore() {
        System.out.println("Score: " + score);
    }

    public void printRemainTime() {
        if (gameOver) {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Game Over! Total time: " + totalTime + "ms");
        } else {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Time passed: " + totalTime + "ms");
        }
    }

    public void move(int direction) {
        if (gameOver)
            return;

        char newPlace = ' ';
        int newA = a, newB = b;

        switch (direction) {
            case 0:
                newA = a - 1;
                newB = b;
                break;
            case 1:
                newA = a;
                newB = b - 1;
                break;
            case 2:
                newA = a + 1;
                newB = b;
                break;
            case 3:
                newA = a;
                newB = b + 1;
                break;
            default:
                return;
        }

        if (place[newA][newB] == '*') {
            System.out.println("Warning! You hit the wall.");
            return;
        }

        place[a][b] = ' ';
        a = newA;
        b = newB;

        if (place[a][b] == '.') {
            score++;
        }

        place[a][b] = 'X';

        if (score == c) {
            gameOver = true;
        }
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter("pacman_save.txt")) {
            writer.println(k);
            writer.println(c);
            writer.println(score);
            writer.println(a);
            writer.println(b);

            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    writer.print(place[i][j]);
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Was not able to save the game!");
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
