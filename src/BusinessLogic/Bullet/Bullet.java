package BusinessLogic.Bullet;
import BusinessLogic.Pos;
public interface Bullet {
    public void movePos();
    public Pos get_Pos();
    public int get_ImpactForce();
    public int get_Size();
}
