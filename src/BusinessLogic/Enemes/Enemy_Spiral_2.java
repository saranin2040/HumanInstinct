package BusinessLogic.Enemes;

import BusinessLogic.Pos;

public class Enemy_Spiral_2 implements Enemy{

    public Enemy_Spiral_2()
    {
        pastPlayerPos=new Pos();
        center=new Pos();
    }
    public Enemy_Spiral_2(double x, double y, int sizeX, int sizeY, double speedEnemy)
    {
        pos=new Pos();
        size=new Pos();
        pos.x=x;
        pos.y=y;
        size.x=sizeX;
        size.y=sizeY;

        dSpeedEnemy=speedEnemy;

        pastPlayerPos=new Pos();
        center=new Pos();
    }

    public void initEnemy(double x,double y,int sizeX,int sizeY,double speedEnemy,int xp)
    {
        pos=new Pos();
        size=new Pos();
        pos.x=x;
        pos.y=y;
        center.x=x;
        center.y=y;
        size.x=sizeX;
        size.y=sizeY;

        dSpeedEnemy=speedEnemy;

        xpCur=xp;
        xpMain=xp;

        radius=620;

        pos.x=0;
        pos.y=500;
    }
    public void movePos(double playerX, double playerY) {

        //double differenceX = playerX - center.x;
        // double differenceY = playerY - center.y;
        double differenceX = playerX - center.x;
        double differenceY = playerY - center.y;


            double gip = Math.sqrt(differenceY * differenceY + differenceX * differenceX);



        if (gip != 0) {
            double sin = differenceY / gip;
            double cos = differenceX / gip;
            double dx = dSpeedEnemy * cos;
            double dy = dSpeedEnemy  * sin;

            center.x += dx;
            center.y += dy;
            //radius += Math.sqrt(dy * dy + dx * dx) / 2;
        }

        if (Math.abs(center.x - playerX) > 10 && Math.abs(center.y - playerY)  > 10) {
            double differenceXw = center.x - pos.x;
            double differenceYw = center.y - pos.y;


            double gip2 = Math.sqrt(differenceYw * differenceYw + differenceXw * differenceXw);
            //radius = gip2;
        }

        //System.out.println("differenceX="+differenceX+":"+"differenceY="+differenceY);
        System.out.println("x="+center.x+":"+"y="+center.y);
        System.out.println("radius="+radius);

        pos.x =center.x+radius * Math.cos(Math.toRadians(angle));
        pos.y =center.y+radius * Math.sin(Math.toRadians(angle));
        angle += dSpeedEnemy/2;
        if (angle >= 360)
            angle = 0;

        radius-=dSpeedEnemy;
        if (radius <0)
        {

            radius*=-1;

        }

        pastPlayerPos.x=center.x;
        pastPlayerPos.y=center.y;
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
    public double gettg(){return tg;}
    private double tg=0;
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
    private Pos pastPlayerPos;
    private Pos center;
}
