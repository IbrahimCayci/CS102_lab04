package SOS_Game;

import javax.swing.*;

public class SOSMain {
    public static void main(String[] args) {

        JFrame frame = new JFrame("SOS Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SOS sos = new SOS(5);
        SOSGUIPanel guiPanel = new SOSGUIPanel(sos, "George", "Gerald");

        frame.getContentPane().add(guiPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
