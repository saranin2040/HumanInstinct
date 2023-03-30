package Visual;

import BusinessLogic.BusinessLogic;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import BusinessLogic.TypeGan;
import Visual.Bullet_Image;
import Visual.CInterface;
import Visual.Enemy_Image;

public class Game_Visual extends JFrame implements ActionListener, AncestorListener {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //star.printComponents(g);
    }

    public Game_Visual() {
        super("Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        animationPanel = new AnimationPanel();

        //star=new Star();

        intface=new CInterface(this);

        player = new JPanel();
        //player.setBackground(Color.BLACK);

        //add(animationPanel);

        //player.add(animationPanel,BorderLayout.CENTER);



        //mainPanel.add(player, BorderLayout.CENTER);
        mainPanel.add(animationPanel, BorderLayout.CENTER);
        //mainPanel.add(animationPanel, BorderLayout.CENTER);
//        player.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //startGame();
//                //bGamePlay=true;
//            }
//        });

        mainPanel.add(intface);
        mainPanel.setComponentZOrder(intface,1);
        //mainPanel.setComponentZOrder(player,0);

        //add(animationPanel);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (bGamePlay)
                {
                    bc.movePlayer(e.getX(), e.getY());



                    updatePlayer();
                }
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_1)
                {
                    bc.changeGan(TypeGan.DEFAULT);
                    System.out.println(TypeGan.DEFAULT.toString());
                }
                else if (e.getKeyCode()==KeyEvent.VK_2)
                {
                    bc.changeGan(TypeGan.BIG);
                    System.out.println(TypeGan.BIG.toString());
                }
                else if (e.getKeyCode()==KeyEvent.VK_3)
                {
                    bc.changeGan(TypeGan.FAST);
                    System.out.println(TypeGan.FAST.toString());
                }

                System.out.println("perfect");

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)

            {

                if (bGamePlay)
                {
                    bc.actionPerformed();

                    updateEnemes();
                    updateBullets();

                    checkAttack();
                    deleteConflictBullets();
                    checkDanage();

                    if (bGamePlay)
                        intface.setText_CountEnemesKill(bc.get_CountKiils());

                    mainPanel.repaint();
                }
                else
                {
                    if (intface.gamerule)
                    {
                        startGame();
                        intface.gamerule=true;
                    }
                }
            }
        });


        bGamePlay=true;
        timer.start();

        bc=new BusinessLogic();

        startGame();
    }

    private void deleteConflictBullets()
    {
        for (int i=0; i<bullets.size();i++)
        {
            if (checkConflictBullets(bullets.get(i)))
            {
                bc.deleteBullet(i);
                removeBullet(i);
            }
        }
    }

    private boolean checkConflictBullets(Bullet_Image bl)
    {
        if (bl.getLocation().x>1920
                || bl.getLocation().y>1080
                || bl.getLocation().x<0
                || bl.getLocation().y<0)
        {
            return true;
        }
        return false;
    }

    private void checkAttack()
    {
        try{
        for (int i=0; i<bc.get_EnemesSize();i++) {
            for (int j = 0; j < bullets.size(); j++) {
                if (bullets.get(j)!=null && enemes.size()>0) {
                    int x=enemes.get(i).main.getSize().width;
                    int y=enemes.get(i).main.getSize().height;
                    int locx=enemes.get(i).getX();
                    int locy=enemes.get(i).getY();

                    if (bullets.get(j).getX()+bullets.get(j).getSize().width>locx && bullets.get(j).getX()<locx+x
                            && bullets.get(j).getY()+bullets.get(j).getSize().height>locy && bullets.get(j).getY()<locy+y)
                    //if (enemes.get(i).getBounds().intersects(bullets.get(j).getBounds()))
                    {
                        if (bc.attack(i,j))
                        {
                            removeEnemy(i);
                        }
                        removeBullet(j);
                    }
                }
            }
        }}
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }


    private void removeEnemy(int i)
    {
        mainPanel.remove(enemes.get(i));
        enemes.remove(i);
    }
    private void removeBullet(int i)
    {
        mainPanel.remove(bullets.get(i));
        bullets.remove(i);
    }

    private void checkDanage()
    {
        for (int i=0; i<enemes.size();i++)
        {
            int x=enemes.get(i).main.getSize().width;
            int y=enemes.get(i).main.getSize().height;
            int locx=enemes.get(i).getX();
            int locy=enemes.get(i).getY();

            if (player.getX()+player.getSize().width>locx && player.getX()<locx+x
            && player.getY()+player.getSize().height>locy && player.getY()<locy+y)
            {
                gameOver();
            }
        }
    }
    private void gameOver()
    {
        bc.gameOver();


        for (int i=0; i<enemes.size();i++)
        {
            mainPanel.remove(enemes.get(i));
        }

        for (int i = 0; i < bullets.size(); i++) {
            mainPanel.remove(bullets.get(i));
        }
        bullets.clear();
        enemes.clear();

        bGamePlay=false;
        //timer.stop();
        intface.gamerule=false;

        intface.turnInterface_GameOver(bc.get_CountKiils(),bc.get_timeHeldOut());
        player.setVisible(false);
        mainPanel.repaint();


    }

    public void startGame()
    {
        bc.startGame();
        //timer.start();
        intface.turnInterface_GamePlay();
        player.setVisible(true);
        bGamePlay=true;
        updatePlayer();

    }

    private void updatePlayer()
    {
        player.setSize(squareSize,squareSize);
        player.setLocation((int)(bc.player.Get_Pos().x-bc.player.Get_Size().x/2), (int)(bc.player.Get_Pos().y-bc.player.Get_Size().x/2));

        //animationPanel.animate();

        double pro=bc.player.Get_Pos().x-lastx;
        double pri=bc.player.Get_Pos().y-lasty;

        double gip = Math.sqrt(pri*pri + pro*pro);
        if (gip!=0) {
            cos = pro / gip;
        }

        //System.out.println("["+Math.toDegrees(Math.acos(cos))+"]");

        if (bc.player.Get_Pos().y-lasty<0) {
            animationPanel.animate(-Math.toDegrees(Math.acos(cos)));
        }
        else {
            animationPanel.animate(Math.toDegrees(Math.acos(cos)));
        }

        animationPanel.setSize(squareSize,squareSize);
        animationPanel.setLocation((int)(bc.player.Get_Pos().x-bc.player.Get_Size().x/2), (int)(bc.player.Get_Pos().y-bc.player.Get_Size().x/2));

        lastx=bc.player.Get_Pos().x;
        lasty=bc.player.Get_Pos().y;
        //animationPanel.animate();
    }
    private void updateEnemes()
    {
        if (enemes.size()<bc.get_EnemesSize())
        {
            for (int i=0; i<bc.get_EnemesSize() -enemes.size();i++)
            {
                enemes.add(new Enemy_Image(bc.get_EnemyByInd(i),Color.RED));
                mainPanel.add(enemes.get(enemes.size()-1));
            }
        }

        if (enemes.size()>bc.get_EnemesSize())
        {
            for (int i=0; i<enemes.size()-bc.get_EnemesSize();i++)
            {
                mainPanel.remove(enemes.get(i));
                enemes.remove(enemes.size()-1);
            }
        }

        for (int i=0; i<enemes.size();i++)
        {
            enemes.get(i).FUpdate(bc.get_EnemyByInd(i));
            if (enemes.get(i).getLocation().x>2220 || enemes.get(i).getLocation().y>1220 || enemes.get(i).getLocation().x<-220 || enemes.get(i).getLocation().y<-220)
            {
                if (enemes.get(i).getName()=="Enemy_Stupid")
                {
                    bc.deleteEnemy(i);
                    removeEnemy(i);
                }
            }
        }
    }

    private void updateBullets()
    {
        if (bullets.size()<bc.player.get_SizeBullets())
        {
            for (int i=0; i<bc.player.get_SizeBullets() -bullets.size();i++)
            {
                bullets.add(new Bullet_Image(bc.player.get_BulletsByInd(i)));
                mainPanel.add(bullets.get(bullets.size()-1));
            }
        }

        if (bullets.size()>bc.player.get_SizeBullets())
        {
            for (int i=0; i<bullets.size()-bc.player.get_SizeBullets();i++)
            {
                mainPanel.remove(bullets.get(i));
                bullets.remove(bullets.size()-1);
            }
        }

        for (int i=0; i<bullets.size();i++)
        {
            bullets.get(i).FUpdate(bc.player.get_BulletsByInd(i));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            //dispose();

            gameOver();

            startBtn.setVisible(false);
            text.setVisible(false);
            bGamePlay=true;
            timer.start();

            //new MouseFollower();
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

    private double lastx=0;
    private double lasty=0;
    private double cos=0;

    private BusinessLogic bc;
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private JPanel mainPanel;
    private CInterface intface;
    private JPanel player;
    private JPanel gam;

    private ArrayList<Enemy_Image>enemes =new ArrayList<Enemy_Image>();
    private ArrayList<Bullet_Image>bullets =new ArrayList<Bullet_Image>();

    private boolean bGamePlay;
    private int squareSize = 100;

    private int squareX = 100;
    private int squareY = 100;

    private JButton startBtn;
    private JLabel text;
    private JLabel text2;

    //private Star star;

    private AnimationPanel animationPanel;

    Timer timer;
    Timer timer2;
}