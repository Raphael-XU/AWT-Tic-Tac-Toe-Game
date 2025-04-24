import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private JButton resetButton;
    private boolean xTurn = true; // X starts
    private int movesCount = 0;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(buttonFont);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }

        resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> resetGame());

        add(gridPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            // Cell already taken
            return;
        }

        clicked.setText(xTurn ? "X" : "O");
        movesCount++;

        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + (xTurn ? "X" : "O") + " wins!");
            resetGame();
            return;
        }

        if (movesCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
            return;
        }

        xTurn = !xTurn;
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") &&
                board[i][0].equals(board[i][1]) &&
                board[i][1].equals(board[i][2])) {
                return true;
            }
            if (!board[0][i].equals("") &&
                board[0][i].equals(board[1][i]) &&
                board[1][i].equals(board[2][i])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].equals("") &&
            board[0][0].equals(board[1][1]) &&
            board[1][1].equals(board[2][2])) {
            return true;
        }
        if (!board[0][2].equals("") &&
            board[0][2].equals(board[1][1]) &&
            board[1][1].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        xTurn = true;
        movesCount = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
