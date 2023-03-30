package Visual;

import javax.swing.*;
import java.awt.*;

public class MainWidget extends JFrame {

    public MainWidget() {
        super("Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameWidget=new Game_Visual();
        gameWidget.setSize(WIDTH,HEIGHT);
        gameWidget.setLocation(0,0);
        //gameWidget.setOpaque(false);
        add(gameWidget);


        player = new JPanel();
        player.setBackground(Color.BLACK);

        player.setSize(50,50);
        player.setLocation(500,500);
        //(player);



        setVisible(true);
    }


    private Game_Visual gameWidget;
    private  JPanel player;

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

}
