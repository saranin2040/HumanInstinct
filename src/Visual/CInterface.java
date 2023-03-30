package Visual;

import BusinessLogic.BusinessLogic;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CInterface extends JPanel implements ActionListener, AncestorListener {

    public CInterface (Game_Visual game)
    {
        this.game=game;

        setLayout(null);
        setOpaque(false);

        gameplay=new JPanel();
        gameplay.setLayout(new FlowLayout());
        gameplay.setOpaque(false);

        gameover=new JPanel();
        gameover.setLayout(new GridLayout(4, 1));

        startBtn = new JButton("Retry");
        startBtn.addActionListener(this);
        startBtn.setVisible(true);

        text=new JLabel("Game Over!");

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.anchor = GridBagConstraints.CENTER;

        text.setVisible(true);
        text.setFont(new Font("Arial", Font.BOLD, 150));

        text4=new JLabel("You held out 0 sec.");

        text4.setVisible(true);
        text4.setFont(new Font("Arial", Font.BOLD, 40));

        text3=new JLabel("You killed 0 enemes");

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 1;
        gbc3.gridy = 1;
        gbc3.weightx = 1;
        gbc3.weighty = 1;
        gbc3.anchor = GridBagConstraints.CENTER;

        text3.setVisible(true);
        text3.setFont(new Font("Arial", Font.BOLD, 50));


        gameover.add(text,gbc3);
        gameover.add(text3,gbc3);gameover.add(text4,gbc3);

        gameover.add(startBtn);

        text2=new JLabel("count enemy kill: ");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        text2.setVisible(true);
        text2.setFont(new Font("Arial", Font.BOLD, 50));


        gameplay.add(text2,gbc);
        //add(text2,gbc);

        //text2.setLocation(500,500);
        //text2.setBounds(0,0,500,100);


        add(gameplay);
        add(gameover);

        gameplay.setBounds(0,0,500,100);
        gameover.setBounds(300,150,1000,500);
    }

    public void setText_CountEnemesKill(int x)
    {
        text2.setText("Count enemy kill: "+x);
    }

    public void turnInterface_GamePlay()
    {
        gameover.setVisible(false);
        gameplay.setVisible(true);
    }
    public void turnInterface_GameOver(int i,long j)
    {
        gameover.setVisible(true);
        text3.setText("You killed "+i+" enemes");
        text4.setText("You held out "+j/1000+" sec.");

        gameplay.setVisible(false);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            //game.startGame();
            gamerule=true;
        }
    }


    public void ancestorAdded(AncestorEvent event)
    {

    }

    public void ancestorRemoved(AncestorEvent event)
    {

    }

    public void ancestorMoved(AncestorEvent event){

    }

    public boolean gamerule=true;

    private JLabel text2;
    private JLabel text4;
    private JButton startBtn;
    private JLabel text;
    private JLabel text3;
    private JPanel gameplay;
    private JPanel gameover;

    private Game_Visual game;
}
