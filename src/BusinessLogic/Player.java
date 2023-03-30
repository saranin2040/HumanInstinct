package BusinessLogic;

import BusinessLogic.Bullet.Bullet_Default;
import BusinessLogic.Bullet.Bullet;

import java.util.ArrayList;

import BusinessLogic.Gan.*;

public class Player {
    public Player()
    {
        pos=new Pos();
        pos.x=0;
        pos.y=0;

        pastPos=new Pos();
        pastPos.x=0;
        pastPos.y=0;

        size=new Pos();
        size.x=100;
        size.y=100;

        gan=new Gan_Default();
    }

    public void movePos(double x, double y)
    {
        pastPos.x = pos.x;
        pastPos.y = pos.y;

        pos.x=x;
        pos.y=y;
    }

    public void tryShoot()
    {
        Bullet b=gan.shoot(pos,pastPos,bullets.size());

        if (b!=null)
        {
            bullets.add(b);
        }
    }
    public Pos Get_Pos()
    {
        return pos;
    }
    public Pos Get_Size()
    {
        return size;
    }



    public void deleteBullet(int i)
    {
        bullets.remove(i);
    }

    public void deleteBullets()
    {
        bullets.clear();
    }

    public void moveBullets()
    {
        for (int i=0; i<bullets.size();i++)
        {
            bullets.get(i).movePos();
        }
    }

    public void changeGan(TypeGan typeGan)
    {
        Factory_Gans f=new Factory_Gans();
        gan=f.createGan(typeGan.toString());

        System.out.println("win!");
    }

    public Bullet get_BulletsByInd(int i)
    {
        return bullets.get(i);
    }

    public int get_SizeBullets()
    {
        return bullets.size();
    }

    private Pos pos;
    private  Pos pastPos;
    private Pos size;
    private ArrayList<Bullet> bullets =new ArrayList<Bullet>();
    private Gan gan;

    long startTime=0;
    long start=0;
    long endTime=0;
}
