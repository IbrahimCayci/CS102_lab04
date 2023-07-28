package SOS_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SOSGUIPanel extends JPanel {
    private SOSCanvas sosCanvas;
    private JLabel scoreLabel;
    private String playerName1;
    private String playerName2;
    private int score1;
    private int score2;
    private JComboBox letterBox;
    SOS sos;

    public SOSGUIPanel(SOS sos, String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.sos = sos;

        setLayout(new BorderLayout());

        scoreLabel = new JLabel(playerName1 + " " + score1 + " - " + score2 + " " + playerName2);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        sosCanvas = new SOSCanvas(sos.getDimension());
        add(sosCanvas, BorderLayout.CENTER);
        SosListener sosListener = new SosListener();
        sosCanvas.addMouseListener(sosListener);

        letterBox = new JComboBox();
        letterBox.addItem("s");
        letterBox.addItem("o");
        add(letterBox, BorderLayout.SOUTH);
    }

    private class SosListener extends MouseAdapter {

        private int canvasWidth;
        private int canvasHeight;
        private int canvasGap;
        private int dimension;
        @Override
        public void mouseClicked(MouseEvent e) {
            canvasWidth = sosCanvas.getWidth();
            canvasHeight = sosCanvas.getHeight();
            canvasGap = sosCanvas.getGap();

            if (e.getPoint().getX() >= sosCanvas.getLocation().getX() + canvasGap && e.getPoint().getX() <= sosCanvas.getLocation().getX() + canvasWidth - canvasGap
                && e.getPoint().getY() >= sosCanvas.getLocation().getY() + canvasGap && e.getPoint().getY() <= sosCanvas.getLocation().getY() + canvasHeight - canvasGap) {

                writeLetter(e);
                score1 = sos.getPlayerScore1();
                score2 = sos.getPlayerScore2();
                scoreLabel.setText(playerName1 + " " + score1 + " - " + score2 + " " + playerName2);

            }

            if (sos.isGameOver())  {
                sosCanvas.setEnabled(false);
                if(score1 > score2)
                    JOptionPane.showMessageDialog(null, playerName1 + " is the winner.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                else if (score1 < score2)
                    JOptionPane.showMessageDialog(null, playerName2 + " is the winner.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, playerName1 + " and " + playerName2 + " are the winners.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        private void writeLetter(MouseEvent e) {

            int cellWidth = sosCanvas.getCellWidth();
            int cellHeight = sosCanvas.getCellHeight();
            dimension = sosCanvas.getDimension();
            int row;
            int col;

            for (int i = 0; i < dimension; i++) {
                if (e.getPoint().getX() >= canvasGap + i*cellWidth
                    && e.getPoint().getX() <= canvasGap + (i+1)*cellWidth) {

                    for (int j = 0; j < dimension; j++) {
                        if (e.getPoint().getY() >= canvasGap + j*cellHeight
                            && e.getPoint().getY() < canvasGap + (j+1) * cellHeight) {

                            row = j+1;
                            col = i+1;

                            if (sosCanvas.isSuitable(row, col)) {
                                int x = canvasGap + i*cellWidth + 2*cellWidth/5;
                                int y = canvasGap + j*cellHeight + 2*cellHeight/3;
                                String letter = (String) letterBox.getSelectedItem();
                                sosCanvas.addLetter(letter, new Point(x,y), row, col);
                                sosCanvas.repaint();

                                sos.play(letter.charAt(0), j+1, i+1);
                            }

                            return;
                        }
                    }

                }
            }
        }
    }

}
