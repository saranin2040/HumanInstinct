package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {

    private JButton startBtn, quitBtn;

    public MainMenu() {
        super("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this);
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(startBtn);
        mainPanel.add(quitBtn);
        add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            dispose();
            setVisible(false);
            new Game_Visual();
        } else if (e.getSource() == quitBtn) {
            dispose();
            System.exit(0);
        }
    }
}