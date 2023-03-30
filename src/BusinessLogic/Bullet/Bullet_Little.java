package BusinessLogic.Bullet;

import BusinessLogic.Pos;

public class Bullet_Little implements Bullet {
    public Bullet_Little(double x, double y, double cos, double sin)
    {
        pos=new Pos();
        pos.x=x;
        pos.y=y;

        this.cos=cos;
        this.sin=sin;
    }

    public void movePos()
    {
        double dx = speed * cos;
        double dy = speed * sin;

        pos.x+=dx;
        pos.y+=dy;
    }
    public Pos get_Pos()
    {
        return pos;
    }
    public int get_ImpactForce()
    {
        return impactForce;
    }

    public int get_Size()
    {
        return size;
    }

    private Pos pos;
    private double speed=40;
    private int size=10;
    private int impactForce=1;
    private double cos;
    private double sin;
}
