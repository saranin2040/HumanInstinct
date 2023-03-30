package Visual;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.sql.SQLOutput;

import BusinessLogic.Enemes.Enemy;


public class Enemy_Image extends JPanel{

    public Enemy_Image(Enemy em, Color bg)
    {
        setOpaque(false);
        main=new JPanel();

        //main.setBackground(bg);
        main.setSize(new Dimension((int)em.get_size().x, (int)em.get_size().x));
        main.setOpaque(false);

        tableXp=new JPanel();

        xp=new JPanel();

        maxXpSize=(int)em.get_size().x-6;

        xpRev=new JPanel();
        xpRev.setBackground(Color.red);

        am=new AnimationPanelEm();
        am.setSize(new Dimension((int)em.get_size().x, (int)em.get_size().x));

        add(am);
        //add(main);
        add(tableXp);add(xp); add(xpRev);

        //repaint();
    }

    private AnimationPanelEm am;

    @Override
    protected void paintComponent(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.rotate(Math.toRadians(45), getWidth() / 2, getHeight() / 2);
        //super.paintComponent(g);
    }

    public void FUpdate(Enemy em)
    {
        main.setSize(new Dimension((int)em.get_size().x, (int)em.get_size().x));
        main.setLocation(0,0);
        setSize(new Dimension((int)em.get_size().x+300,(int)em.get_size().x+300));
        setLocation((int)(em.get_Pos().x-em.get_size().x/2),(int)(em.get_Pos().y-em.get_size().x/2));

        am.setLocation(0,0);
        am.setSize(new Dimension((int)em.get_size().x, (int)em.get_size().x));

        //tableXp.setBackground(Color.CYAN);
        tableXp.setBackground(new Color(0, 0, 0, 0));
        tableXp.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
        tableXp.setSize(new Dimension((int)em.get_size().x,20));
        tableXp.setLocation(0,(int)em.get_size().x+20);

        int sizee=(int)((double)maxXpSize*((double)em.get_CurXp()/(double)em.get_FullXp()));

        xp.setBackground(Color.red);
        xp.setSize(new Dimension(sizee,14));
        xp.setLocation(3,(int)em.get_size().x+23);

        xpRev.setBackground(Color.LIGHT_GRAY);
        xpRev.setSize(new Dimension(maxXpSize-sizee,14));
        xpRev.setLocation(3+sizee,(int)em.get_size().x+23);

        double pro=em.get_Pos().x-x;
        double pri=em.get_Pos().y-y;

        double gip = Math.sqrt(pri*pri + pro*pro);
            if (gip!=0) {
                cos = pro / gip;
            }

        if (em.get_Pos().y-y<0) {
            am.animate(-Math.toDegrees(Math.acos(cos)),em.get_size().x);
        }
        else {
            am.animate(Math.toDegrees(Math.acos(cos)),em.get_size().x);
        }

        x=em.get_Pos().x;
        y=em.get_Pos().y;

        repaint();
    }

    private double x;
    private double y;

    private double cos=0;
    private int maxXpSize;

    private JPanel tableXp;
    private JPanel xp;
    private JPanel xpRev;
    private JPanel star;
    public JPanel main;

}
