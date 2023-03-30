package BusinessLogic.Enemes;

import BusinessLogic.Pos;

public interface Enemy {
    public void initEnemy(double x,double y,int sizeX,int sizeY,double speedEnemy,int xp);
    public void movePos(double x, double y);
    public Pos get_Pos();
    public Pos get_size();
    public boolean damage(int impactForce);
    public int get_FullXp();
    public double gettg();
    public int get_CurXp();
}
