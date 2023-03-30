package BusinessLogic.Gan;
import BusinessLogic.Bullet.Bullet;
import BusinessLogic.Bullet.Bullet_Default;
import BusinessLogic.Bullet.Bullet_Little;
import BusinessLogic.Pos;

public class Gan_Fast implements Gan{
    public Bullet shoot(Pos pos,Pos pastPos,int countBullets)
    {
        double differenceX=pos.x-pastPos.x;
        double differenceY=pos.y-pastPos.y;

        if (countBullets<10) {
            if (Math.abs(differenceX) > 7 || Math.abs(differenceY) > 7)
                if (Math.abs(startTime - System.currentTimeMillis()) < 30) {
                    if (Math.abs(timeLastShoot - System.currentTimeMillis()) > 50) {
                        timeLastShoot = System.currentTimeMillis();
                        startTime = System.currentTimeMillis();

                        double gip = Math.sqrt(differenceY * differenceY + differenceX * differenceX);

                        double sin = differenceY / gip;
                        double cos = differenceX / gip;

                        return new Bullet_Little(pos.x, pos.y, cos, sin);
                    }
                }
        }
        startTime=System.currentTimeMillis();
        return null;
    }
    private long startTime=0;
    private long timeLastShoot=0;
}
