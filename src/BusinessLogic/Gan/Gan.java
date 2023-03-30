package BusinessLogic.Gan;
import BusinessLogic.Bullet.Bullet;
import BusinessLogic.Pos;

public interface Gan {
    public Bullet shoot(Pos pos,Pos pastPos,int countBullets);
}
