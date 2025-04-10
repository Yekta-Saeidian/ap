package exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EX2_PM_3_2 extends JFrame implements KeyListener {
    private int k;
    private int x = 1, y = 1;
    private char[][] grid;
    private final int CELL_SIZE = 30;
    private final Color WALL_COLOR = Color.BLACK;
    private final Color PLAYER_COLOR = Color.RED;
    private final Color EMPTY_COLOR = Color.WHITE;

    public EX2_PM_3_2(int size) {
        this.k = size;
        initializeGrid();
        setupGUI();
    }

    private void initializeGrid() {
        grid = new char[k + 2][k + 2];

        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                grid[i][j] = ' ';
            }
        }

        for (int i = 0; i < k + 2; i++) {
            grid[0][i] = '*';
            grid[k + 1][i] = '*';
            grid[i][0] = '*';
            grid[i][k + 1] = '*';
        }
        grid[x][y] = 'X';
    }

    private void setupGUI() {
        setTitle("Grid Movement Game");
        setSize((k + 2) * CELL_SIZE, (k + 2) * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);

        JOptionPane.showMessageDialog(this,
                "Controls:\n" +
                        "W = Up\n" +
                        "A = Left\n" +
                        "S = Down\n" +
                        "D = Right\n" +
                        "Q = Exit",
                "Game Instructions",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {

                if (grid[i][j] == '*') {
                    g2d.setColor(WALL_COLOR);
                } else if (grid[i][j] == 'X') {
                    g2d.setColor(PLAYER_COLOR);
                } else {
                    g2d.setColor(EMPTY_COLOR);
                }
                g2d.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g2d.setColor(Color.GRAY);
                g2d.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g2d.setColor(Color.WHITE);
                if (grid[i][j] != ' ') {
                    g2d.drawString(String.valueOf(grid[i][j]),
                            j * CELL_SIZE + CELL_SIZE / 2 - 5,
                            i * CELL_SIZE + CELL_SIZE / 2 + 5);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        if (key == 'q') {
            JOptionPane.showMessageDialog(this, "Game exited!");
            System.exit(0);
        }

        boolean moved = false;

        switch (key) {
            case 'w':
                if (grid[x - 1][y] != '*') {
                    movePlayer(x - 1, y);
                    moved = true;
                }
                break;
            case 'a':
                if (grid[x][y - 1] != '*') {
                    movePlayer(x, y - 1);
                    moved = true;
                }
                break;
            case 's':
                if (grid[x + 1][y] != '*') {
                    movePlayer(x + 1, y);
                    moved = true;
                }
                break;
            case 'd':
                if (grid[x][y + 1] != '*') {
                    movePlayer(x, y + 1);
                    moved = true;
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid key! Use W, A, S, D to move or Q to quit.");
                return;
        }

        if (!moved) {
            JOptionPane.showMessageDialog(this, "Hitting the game wall!");
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void movePlayer(int newX, int newY) {
        grid[x][y] = ' ';
        x = newX;
        y = newY;
        grid[x][y] = 'X';
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter grid size (k):");
        int k = Integer.parseInt(input);

        SwingUtilities.invokeLater(() -> {
            EX2_PM_3_2 game = new EX2_PM_3_2(k);
            game.setVisible(true);
        });
    }
}