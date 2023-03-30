package BusinessLogic;

import java.util.ArrayList;

public class SettingsSpawnEnemes {

    public SettingsSpawnEnemes()
    {
        setDefault();
    }
    public void setDefault()
    {
        minSize=200;
        maxSize=200;
        minSpeed=1;
        maxSpeed=1;
        maxEnemes=1;
        minTime=2000;
        maxTime=3000;
        minXP=3;
        maxXp=5;

        typesEnemy.clear();
        //typesEnemy.add('5');
        typesEnemy.add('1');
        typesEnemy.add('2');
        typesEnemy.add('3');
        //typesEnemy.add('4');
    }
    public void setSettings(int minSize, int maxSize,int minSpeed, int maxSpeed,int minTime,int maxTime,int maxEnemes)
    {
        this.minSize=minSize;
        this.maxSize=maxSize;
        this.minSpeed=minSpeed;
        this.maxSpeed=maxSpeed;
        this.minTime=minTime;
        this.maxTime=maxTime;
        this.maxEnemes=maxEnemes;
    }
    public void setXp(int minXp,int maxXP)
    {
        this.minXP=minXp;
        this.maxXp=maxXP;
    }

    public void addEnemy(char c)
    {
        typesEnemy.add(c);
    }
    public void addTypeEnemy(Character c)
    {
        typesEnemy.add(c);
    }

    public int minSize;
    public int maxSize;
    public int minSpeed;
    public int maxSpeed;
    public int maxEnemes;
    public int minTime;
    public int maxTime;
    public int minXP;
    public int maxXp;

    public ArrayList<Character>typesEnemy=new ArrayList<Character>();
}
