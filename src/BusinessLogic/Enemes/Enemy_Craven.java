package BusinessLogic.Enemes;

import BusinessLogic.Pos;

public class Enemy_Craven implements Enemy{

    public Enemy_Craven()
    {

    }
    public Enemy_Craven(double x, double y, int sizeX, int sizeY, double speedEnemy)
    {
        pos=new Pos();
        size=new Pos();
        pos.x=x;
        pos.y=y;
        size.x=sizeX;
        size.y=sizeY;

        dSpeedEnemy=speedEnemy;
    }

    public void initEnemy(double x,double y,int sizeX,int sizeY,double speedEnemy,int xp)
    {
        pos=new Pos();
        size=new Pos();
        pos.x=x;
        pos.y=y;
        size.x=sizeX;
        size.y=sizeY;


        dSpeedEnemy=speedEnemy*3;

        xpCur=xp;
        xpMain=xp;
    }
    public void movePos(double playerX, double playerY)
    {
        double differenceX=playerX-pos.x;
        double differenceY=playerY-pos.y;


        double gip = Math.sqrt(differenceY*differenceY + differenceX*differenceX);

        if (gip!=0)
        {
            double cutSpeed=1;
            if (gip<500)
            {
                double k=20*(gip/500.0);
                cutSpeed=10000.0/(Math.abs(gip)*k);
            }

            double sin = differenceY / gip;
            double cos = differenceX / gip;
            double dx = dSpeedEnemy/cutSpeed * cos;
            double dy = dSpeedEnemy/cutSpeed * sin;

            tg=sin/cos;

            pos.x += dx;
            pos.y += dy;
        }
    }
    public double gettg(){return tg;}
    private double tg=0;
    public Pos get_Pos()
    {
        return pos;
    }

    public Pos get_size()
    {
        return size;
    }

    public boolean damage(int impactForce)
    {
        xpCur-=impactForce;
        if (xpCur<=0)
        {
            return true;
        }
        return false;
    }

    public int get_FullXp()
    {
        return xpMain;
    }

    public int get_CurXp()
    {
        return xpCur;
    }
    int xpCur;
    int xpMain;

    Pos pos;
    Pos size;
    double dSpeedEnemy;

}
