package PotLuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PotStartFrame extends JFrame {

    private final int WIDTH = 10;
    private final int DEFAULT_ROW = 4;
    private final int DEFAULT_COL = 4;
    private final int DEFAULT_PRIZE = 1;
    private final int DEFAULT_BOMB = 2;

    private JButton submitButton;
    JTextField rowField;
    JTextField colField;
    JTextField prizeField;
    JTextField bombField;

    private int row;
    private int column;
    private int prize;
    private int bomb;


    public PotStartFrame() {
        setSize(400, 300);
        setLocationRelativeTo(null);

        createComponents();
    }

    public void createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));

        JLabel rowLabel = new JLabel("Enter the count of rows: ");
        rowField = new JTextField(WIDTH);
        rowField.setText("" + DEFAULT_ROW);
        mainPanel.add(rowLabel);
        mainPanel.add(rowField);

        JLabel colLabel = new JLabel("Enter the count of columns: ");
        colField = new JTextField(WIDTH);
        colField.setText("" + DEFAULT_COL);
        mainPanel.add(colLabel);
        mainPanel.add(colField);

        JLabel prizeLabel = new JLabel("Enter the count of prizes: ");
        prizeField = new JTextField(WIDTH);
        prizeField.setText("" + DEFAULT_PRIZE);
        mainPanel.add(prizeLabel);
        mainPanel.add(prizeField);

        JLabel bombLabel = new JLabel("Enter the count of bombs: ");
        bombField = new JTextField(WIDTH);
        bombField.setText("" + DEFAULT_BOMB);
        mainPanel.add(bombLabel);
        mainPanel.add(bombField);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ButtonListener());
        getContentPane().add(submitButton, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == submitButton) {

                try {
                    row = Integer.parseInt(rowField.getText());
                    column = Integer.parseInt(colField.getText());
                    prize = Integer.parseInt(prizeField.getText());
                    bomb = Integer.parseInt(bombField.getText());

                    if (row < 1 || column < 1 || prize < 0 || bomb < 0 || prize+bomb > row*column) {
                        JOptionPane.showMessageDialog(null, "Some variables are below zero, or prize and bomb counts excess total grid count.", "Unavailable Input", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        dispose();
                        PotFrame potFrame = new PotFrame();
                        potFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        potFrame.setVariables(row, column, prize, bomb);
                        potFrame.setVisible(true);
                    }

                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Some of the inputs are not number.", "Unavailable Input", JOptionPane.ERROR_MESSAGE);
                }


            }
        }
    }

}
