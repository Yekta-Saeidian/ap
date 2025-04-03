package exercises.ex2;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EX2_PM_2_3 {
    private static final String SAVE_FILE = "save_game.txt";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean loaded = false;
        int k = 0, c = 0, a = 1, b = 1, score = 0;
        char[][] place = null;
        long startTime = 0;
        System.out.println("do you want to load the saved game? (y/n)");
        char loadChoice = scan.next().charAt(0);
        if (loadChoice == 'y' || loadChoice == 'Y') {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE));
                k = Integer.parseInt(reader.readLine());
                c = Integer.parseInt(reader.readLine());
                a = Integer.parseInt(reader.readLine());
                b = Integer.parseInt(reader.readLine());
                score = Integer.parseInt(reader.readLine());
                startTime = Long.parseLong(reader.readLine());
                place = new char[k + 2][k + 2];
                for (int i = 0; i < k + 2; i++) {
                    String line = reader.readLine();
                    for (int j = 0; j < k + 2; j++) {
                        place[i][j] = line.charAt(j * 2);
                    }
                }
                reader.close();
                loaded = true;
                for (int i = 0; i < k + 2; i++) {
                    for (int j = 0; j < k + 2; j++) {
                        System.out.print(place[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("score: " + score);
                System.out.println("game loaded successfully!");
                moveX(place, k, c, a, b, score, startTime);
            } catch (IOException e) {
                System.out.println("no game was saved!");
                System.out.println("new game: ");
            }
        }
        if (!loaded) {
            System.out.println("enter a number(k): ");
            k = scan.nextInt();
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
            int empty = k * k;
            c = empty + 1;
            while (c > empty) {
                System.out.println("enter a number(c): ");
                c = scan.nextInt();
                if (c > empty) {
                    System.out.println("number c is invalid!");
                    System.out.println("try something lower than " + empty);
                }
            }
            System.out.println("w = up , a = left , s = down , d = right , q = exit, v = save");
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
            long start = System.currentTimeMillis();
            moveX(place, k, c, a, b, score, startTime);
            scan.close();
        }
    }

    public static void moveX(char[][] place, int k, int c, int a, int b, int score, long startTime) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            char response = scan.next().charAt(0);
            if (Character.compare(response, 'q') == 0) {
                System.out.println("exit");
                break;
            } else if (Character.compare(response, 'v') == 0) {
                try {
                    FileWriter writer = new FileWriter(SAVE_FILE);
                    writer.write(k + "\n");
                    writer.write(c + "\n");
                    writer.write(a + "\n");
                    writer.write(b + "\n");
                    writer.write(score + "\n");
                    writer.write(startTime + "\n");
                    for (int i = 0; i < k + 2; i++) {
                        for (int j = 0; j < k + 2; j++) {
                            writer.write(place[i][j] + " ");
                        }
                        writer.write("\n");
                    }
                    writer.close();
                    System.out.println("game saved successfully!");
                } catch (IOException e) {
                    System.out.println("error saving game: " + e.getMessage());
                }
                continue;
            }
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
                System.out.println("time: " + (finish - startTime) + "ms");
                new File(SAVE_FILE).delete();
                break;
            }
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    System.out.print(place[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("score: " + score);
            //scan.close();
        }
        scan.close();
    }
}