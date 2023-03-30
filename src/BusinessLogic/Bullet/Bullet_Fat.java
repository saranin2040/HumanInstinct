package BusinessLogic.Bullet;

import BusinessLogic.Pos;

public class Bullet_Fat implements Bullet {
    public Bullet_Fat(double x, double y, double cos, double sin)
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
    private double speed=15;
    private int size=50;
    private int impactForce=4;
    private double cos;
    private double sin;
}
