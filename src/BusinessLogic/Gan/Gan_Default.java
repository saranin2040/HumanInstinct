package BusinessLogic.Gan;
import BusinessLogic.Bullet.Bullet;
import BusinessLogic.Bullet.Bullet_Default;
import BusinessLogic.Bullet.Bullet_Fat;
import BusinessLogic.Pos;

public class Gan_Default implements Gan{
    public Bullet shoot(Pos pos,Pos pastPos,int countBullets)
    {
        double differenceX=pos.x-pastPos.x;
        double differenceY=pos.y-pastPos.y;

        if (Math.abs(differenceX)>20 || Math.abs(differenceY)>20)
            if (Math.abs(startTime-System.currentTimeMillis())<70)
            {
                if (Math.abs(timeLastShoot - System.currentTimeMillis()) > 300)
                {
                    timeLastShoot = System.currentTimeMillis();
                    startTime=System.currentTimeMillis();

                    double gip = Math.sqrt(differenceY*differenceY + differenceX*differenceX);

                    double sin = differenceY / gip;
                    double cos = differenceX / gip;

                    return new Bullet_Default(pos.x, pos.y, cos, sin);
                }
            }
        startTime=System.currentTimeMillis();
        return null;
    }
    private long startTime=0;
    private long timeLastShoot=0;
}
