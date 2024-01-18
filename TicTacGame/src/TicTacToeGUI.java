import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TicTacToeGUI extends JFrame implements ActionListener {

	private JButton[] buttons = new JButton[9];
    private int turn = 1;
    private JLabel label = new JLabel("Tic Tac Toe");
    
    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (turn % 2 == 1) {
            buttonClicked.setText("X");
            label.setText("O's turn");
        } else {
            buttonClicked.setText("O");
            label.setText("X's turn");
        }
        buttonClicked.setEnabled(false);
        turn++;
        checkForWinner();
    }

    public void checkForWinner() {
        if (checkRow(0, 1, 2) || checkRow(3, 4, 5) || checkRow(6, 7, 8) || checkRow(0, 3, 6) || checkRow(1, 4, 7) || checkRow(2, 5, 8) || checkRow(0, 4, 8) || checkRow(2, 4, 6)) {
            if (turn % 2 == 0) {
                label.setText("X wins!");
            } else {
                label.setText("O wins!");
            }
            disableAllButtons();
        } else if (turn > 9) {
            label.setText("It's a draw!");
        }
    }

    public boolean checkRow(int a, int b, int c) {
        return !buttons[a].getText().equals("") && buttons[a].getText().equals(buttons[b].getText()) && buttons[b].getText().equals(buttons[c].getText());
    }

    public void disableAllButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }

}
