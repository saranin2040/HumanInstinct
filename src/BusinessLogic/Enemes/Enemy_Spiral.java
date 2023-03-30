package BusinessLogic.Enemes;

import BusinessLogic.Pos;

public class Enemy_Spiral implements Enemy{

    public Enemy_Spiral()
    {

    }
    public Enemy_Spiral(double x, double y, int sizeX, int sizeY, double speedEnemy)
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

        dSpeedEnemy=speedEnemy;

        xpCur=xp;
        xpMain=xp;

        radius=1920;
    }
    public void movePos(double playerX, double playerY)
    {
        pos.x = (int) (playerX + radius * Math.cos(Math.toRadians(angle)));
        pos.y = (int) (playerY + radius * Math.sin(Math.toRadians(angle)));
        angle += dSpeedEnemy/2;
        if (angle >= 360)
            angle = 0;

        radius-=dSpeedEnemy/4;
        //pos.x += dx;
        //pos.y += dy;
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

    public boolean checkCollision_With_Player(Pos playerPos,Pos playerSize)
    {
        if (Math.abs((int)pos.x-(int)playerPos.x)<(int)(size.x/2+ playerSize.x/2) && Math.abs((int)pos.y-(int)playerPos.y)<(int)(size.y/2+ playerSize.y/2))
        {
            return true;
        }
        return false;
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

    private boolean activeDir=false;
    private double dx;
    private double dy;
    private double angle = 0;
    private double radius;
}
