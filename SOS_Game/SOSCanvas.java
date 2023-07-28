package SOS_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SOSCanvas extends JPanel {

    private int dimension;
    private int cellWidth;
    private int cellHeight;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int GAP = 10;
    private ArrayList<Point> letterLocation = new ArrayList<Point>();
    private ArrayList<String> letters = new ArrayList<String>();
    private ArrayList<Integer> rowsOfLetters = new ArrayList< Integer>();
    private ArrayList<Integer> colsOfLetters = new ArrayList<Integer>();

    public SOSCanvas(int dimension) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.dimension = dimension;
        repaint();
        System.out.println(getWidth() + "");
    }

    public int getCellWidth () { return this.cellWidth; }
    public int getCellHeight () { return this.cellHeight; }
    public int getGap() { return this.GAP; }
    public int getDimension () { return this.dimension; }

    public void addLetter(String str, Point p, int row, int col) {
        letters.add(str);
        letterLocation.add(p);
        rowsOfLetters.add(row);
        colsOfLetters.add(col);
    }

    public boolean isSuitable(int row, int col) {
        // rowsOfLetters.size() = colsOfLetters.size()
        for (int i = 0; i < rowsOfLetters.size(); i++) {
            if (row == rowsOfLetters.get(i) && col == colsOfLetters.get(i))
                return false;
        }
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        cellWidth = (width - 2*GAP) / dimension;
        cellHeight = (height - 2*GAP) / dimension;

        // horizontal lines
        for (int i = 0; i <= dimension; i++) {
            g.drawLine(GAP, GAP + i*cellHeight, width - GAP, GAP + i*cellHeight);
        }
        for (int i = 0; i <= dimension; i++) {
            g.drawLine(GAP + i*cellWidth, GAP, GAP + i*cellWidth, height - GAP);
        }

        drawStrings(g);

    }

    public void drawStrings(Graphics g) {

        rewriteLetterLocation();

        g.setFont(new Font("Times Roman", Font.BOLD, Math.min(cellWidth, cellHeight)/2));

        if (letterLocation.size() > 0) {
            for (int i = 0; i < letterLocation.size(); i++) {
                g.drawString(letters.get(i), (int) letterLocation.get(i).getX(), (int) letterLocation.get(i).getY());
            }
        }

    }

    public void rewriteLetterLocation () {
        for (int i = 0; i < letterLocation.size(); i++) {
            int col = colsOfLetters.get(i)-1;
            int row = rowsOfLetters.get(i)-1;
            Point p = new Point(getGap() + col*cellWidth + 2*cellWidth/5, getGap() + row*cellHeight + 2*cellHeight/3);
            letterLocation.set(i, p);
        }
    }


}
