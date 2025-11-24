import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 3));

        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 50);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; 
        }

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this,
                    "Player " + currentPlayer + " wins!");
            disableButtons();
            askRestart();
            return;
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            askRestart();
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWin() {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            // Row
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) return true;

            // Column
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) return true;
        }

        // Diagonal 1
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) return true;

        // Diagonal 2
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setEnabled(false);
    }

    private void clearBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
    }

    private void askRestart() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Play again?", "Restart", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            clearBoard();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
