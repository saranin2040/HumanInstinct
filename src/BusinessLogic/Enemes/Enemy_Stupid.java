package BusinessLogic.Enemes;

import BusinessLogic.Pos;

public class Enemy_Stupid implements Enemy{

    public Enemy_Stupid()
    {

    }
    public Enemy_Stupid(double x, double y, int sizeX, int sizeY, double speedEnemy)
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
    }
    public void movePos(double playerX, double playerY)
    {
        if (!activeDir) {
            double differenceX = playerX - pos.x;
            double differenceY = playerY - pos.y;


            double gip = Math.sqrt(differenceY * differenceY + differenceX * differenceX);

            if (gip != 0) {
                double sin = differenceY / gip;
                double cos = differenceX / gip;
                double dx = dSpeedEnemy * cos;
                double dy = dSpeedEnemy * sin;
                this.dx=dx;
                this.dy=dy;

                tg=sin/cos;
            }
            activeDir=true;
        }

        pos.x += dx;
        pos.y += dy;
    }

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
    public double gettg(){return tg;}
    private double tg=0;

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
}
