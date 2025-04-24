import java.awt.*;
import java.awt.event.*;

public class TicTacToeAWT extends Frame implements ActionListener {

    private static void method() {
        new TicTacToeAWTGame();
    }
    private final Button[] buttons = new Button[9];
    private final Button resetButton;
    private boolean xTurn = true; 
    private int movesCount = 0;

    public TicTacToeAWTGame() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());

        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(buttonFont);
            method(i);
            gridPanel.add(buttons[i]);
        }

        resetButton = new Button("Start New Game");
        resetButton.addActionListener(e -> resetGame());

        add(gridPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();

        if (!clicked.getLabel().equals("")) {
            return;
        }

        clicked.setLabel(xTurn ? "X" : "O");
        clicked.setBackground(new Color(255, 165, 0)); 
        clicked.setForeground(new Color(0, 102, 204)); 
        movesCount++;

        if (checkWinner()) {
            showMessage("Player " + (xTurn ? "1" : "2") + " wins!");
            resetGame();
            return;
        }

        if (movesCount == 9) {
            showMessage("Draw!");
            resetGame();
            return;
        }

        xTurn = !xTurn;
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getLabel();
        }

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

        if (!board[0][0].equals("") &&
            board[0][0].equals(board[1][1]) &&
            board[1][1].equals(board[2][2])) {
            return true;
        }
        return !board[0][2].equals("") &&
                board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0]);
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setLabel("");
            button.setBackground(null);
        }
        xTurn = true;
        movesCount = 0;
    }

    private void showMessage(String message) {
        Dialog dialog = new Dialog(this, "Game Over", true);
        dialog.setLayout(new FlowLayout());
        Label label = new Label(message);
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.add(label);
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(this);
        method(dialog);
    }

    public static void main(String[] args) {
        method();
    }

    private void method(int i) {
        buttons[i].addActionListener(this);
    }

    private void method(Dialog dialog) {
        dialog.setVisible(true);
    }
}
