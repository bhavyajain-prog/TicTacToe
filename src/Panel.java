import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Panel extends JPanel implements MouseListener {

    static final int height = 650;
    static final int width = 650;

    static char chance = 'X';
    static char[][] board = new char[3][3]; // Initialize with empty cells

    static boolean gameON = true;
    static char winner = 'D';

    // Images for X and O
    Image imgX;
    Image imgO;

    // Color library
    Color red = new Color(231, 76, 60);
    Color green = new Color(26, 188, 156);
    static Color blue = new Color(52, 73, 94);
    Color white = new Color(255, 255, 255);
    Color lightGreen = new Color(46, 204, 113);

    Panel() throws IOException {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(width, height));
        this.addMouseListener(this);
        this.setBackground(blue);

        // Load images
        imgX = Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/x.png"));
        imgO = Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/o.png"));

        // Initialize board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        // Define margins and cell size
        int margin = 20;
        int topMargin = 70;
        int cellWidth = (width - 2 * margin) / 3;
        int cellHeight = (height - topMargin - margin) / 3;

        // Draw the box around the board
        g.setColor(lightGreen);
        g.drawRect(margin, topMargin, 3 * cellWidth, 3 * cellHeight);

        // Draw the grid
        for (int i = 1; i < 3; i++) {
            g.drawLine(margin + i * cellWidth, topMargin, margin + i * cellWidth, topMargin + 3 * cellHeight);
            g.drawLine(margin, topMargin + i * cellHeight, margin + 3 * cellWidth, topMargin + i * cellHeight);
        }

        // Draw the Xs and Os
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    drawX(g, row, col, margin, topMargin, cellWidth, cellHeight);
                } else if (board[row][col] == 'O') {
                    drawO(g, row, col, margin, topMargin, cellWidth, cellHeight);
                }
            }
        }

        // Draw winner message
        if (!gameON) {
            g.setColor(white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics fm = g.getFontMetrics(g.getFont());
            if (winner == 'D') {
                g.drawString("Draw!", (width-fm.stringWidth("Draw!"))/2, height / 2);
            } else {
                g.drawString(winner + " Wins!", (width-fm.stringWidth(winner + " Wins!"))/2, height / 2);
            }
        }
    }

    private void drawX(Graphics g, int row, int col, int margin, int topMargin, int cellWidth, int cellHeight) {
        int xStart = margin + col * cellWidth;
        int yStart = topMargin + row * cellHeight;
        g.drawImage(imgX, xStart + 10, yStart + 10, cellWidth - 20, cellHeight - 20, this);
    }

    private void drawO(Graphics g, int row, int col, int margin, int topMargin, int cellWidth, int cellHeight) {
        int xStart = margin + col * cellWidth;
        int yStart = topMargin + row * cellHeight;
        g.drawImage(imgO, xStart + 10, yStart + 10, cellWidth - 20, cellHeight - 20, this);
    }

    private void checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                winner = board[i][0];
                gameON = false;
                return;
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winner = board[0][i];
                gameON = false;
                return;
            }
        }
        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = board[0][0];
            gameON = false;
            return;
        }
        if (board[2][0] != ' ' && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            winner = board[2][0];
            gameON = false;
            return;
        }
        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            winner = 'D';
            gameON = false;
        }
    }

    private void switchChance() {
        chance = (chance == 'X') ? 'O' : 'X';
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameON) return;

        int margin = 20;
        int topMargin = 70;
        int cellWidth = (width - 2 * margin) / 3;
        int cellHeight = (height - topMargin - margin) / 3;

        int col = (e.getX() - margin) / cellWidth;
        int row = (e.getY() - topMargin) / cellHeight;

        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = chance;
            checkWin();
            if (gameON) {
                switchChance();
            }
            repaint();
        } else {
            System.out.println("Invalid move or cell already occupied.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
