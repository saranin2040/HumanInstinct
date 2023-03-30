package BusinessLogic.Bullet;

import BusinessLogic.Pos;

public class Bullet_Default implements Bullet {

    public Bullet_Default(double x, double y, double cos, double sin)
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
    private double speed=30;
    private int size=20;
    private int impactForce=2;
    private double cos;
    private double sin;
}
