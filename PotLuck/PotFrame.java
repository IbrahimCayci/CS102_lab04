package PotLuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PotFrame extends JFrame {

    private int rows = 4;
    private int cols = 5;
    private int trials;
    private JLabel label;
    private JButton[] buttons;
    private JButton[] bombButtons;
    private int bombCount;
    private JButton[] prizeButtons;
    private int prizeCount;
    private PotStartFrame startFrame;

    public PotFrame() {

        trials = 0;
    }

    public void setVariables(int row, int column, int prize, int bomb) {

        setSize(600, 650);
        setLocationRelativeTo(null);

        this.rows = row;
        this.cols = column;
        this.prizeCount = prize;
        this.bombCount = bomb;

        createComponents();
    }

    public void createComponents() {

        label = new JLabel("Trials: " + trials);
        JPanel textPanel = new JPanel();
        textPanel.add(label);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(rows, cols));

        buttons = new JButton[rows*cols];
        bombButtons = new JButton[bombCount];
        prizeButtons = new JButton[prizeCount];

        for (int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton();
            buttons[i].addActionListener(new ClickListener());
        }

        for(JButton button: buttons) {
            buttonPanel.add(button);
        }

        appointButtons();

        getContentPane().add(textPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

    }

    public void appointButtons() {
        Random random = new Random();

        // appoints prize buttons
        for (int i = 0; i < prizeCount; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(rows*cols);
            }while (isButtonPrize(randomIndex));
            prizeButtons[i] = buttons[randomIndex];
        }

        // appoints bomb buttons
        for (int i = 0; i < bombCount; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(rows * cols);
            }while (isButtonBomb(randomIndex) || isButtonPrize(randomIndex));
            bombButtons[i] = buttons[randomIndex];
        }


    }

    private boolean isButtonPrize (int index) {
        for (int i = 0; i < prizeButtons.length; i++) {
            if (prizeButtons[i] != null && buttons[index] == prizeButtons[i] )
                return true;
        }
        return false;
    }

    private boolean isButtonBomb(int index) {
        for (int i = 0; i < bombButtons.length; i++) {
            if (bombButtons[i] != null && buttons[index] == bombButtons[i])
                return true;
        }
        return false;
    }

    private class ClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof JButton) {
                ((JButton) e.getSource()).setEnabled(false);
                trials++;
            }

            boolean isFinish = false;
            for (int i = 0; i < Math.max(prizeCount, bombCount); i++) {

                if (i < prizeCount && e.getSource() == prizeButtons[i]) {
                    label.setText("You got in " + trials + " attempts.");
                    isFinish = true;
                }

                if (i < bombCount && e.getSource() == bombButtons[i]) {
                    label.setText("Sorry! You are blown up at attempt " + trials + "!");
                    isFinish = true;
                }
            }

            if (trials >= rows*cols)
                isFinish = true;

            if (isFinish) {
                for (int i = 0; i < buttons.length; i++)
                    buttons[i].setEnabled(false);

                int input = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Game finished.", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    PotStartFrame startFrame = new PotStartFrame();
                    startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    startFrame.setVisible(true);
                    dispose();
                }
                else if (input == 1) {
                    dispose();
                }
            }

            else {
                label.setText("Trials: " + trials);
            }

        }
    }


}
