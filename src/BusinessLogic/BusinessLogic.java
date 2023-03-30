package BusinessLogic;

import BusinessLogic.Enemes.Enemy;
import BusinessLogic.Enemes.Enemy_Default;
import BusinessLogic.Enemes.Factory_Enemes;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BusinessLogic {

    public BusinessLogic()
    {
        player=new Player();
        sse = new SettingsSpawnEnemes();
        factoryEnemes = new Factory_Enemes();

        //enemes.add(new Enemy_Test(100,500,100,100,2));
        //enemes.add(new Enemy_Test(500,500,100,100,2));
        //player.shoot();
        //enemes.add(new Enemy_Default(1300,300,350,350,3));
        //enemes.add(new Enemy_Default(100,100,100,100,5));
        //enemes.add(new Enemy_Default(1000,500,150,150,3));
        //enemes.add(new Enemy_Default(1500,900,30,30,7));
        //enemes.add(new Enemy_Default(500,900,30,30,9));
    }
    public void moveEnemes()
    {
        for (int i=0; i<enemes.size();i++)
        {
            enemes.get(i).movePos(player.Get_Pos().x,player.Get_Pos().y);
        }
    }

    public Enemy get_EnemyByInd(int i)
    {
        return enemes.get(i);
    }

    public int get_EnemesSize()
    {
        return enemes.size();
    }

    public void startGame()
    {
        bGamePlay=true;

        player.movePos(960,540);

        sse.setDefault();

        countKiils=0;

        startTime=System.currentTimeMillis();
    }

    public void deleteBullet(int i)
    {
        player.deleteBullet(i);
    }

    public void spawnRandomEnemy()
    {
        if (enemes.size()<sse.maxEnemes)
        {
            if (spawnEn) {
                randomTime = ThreadLocalRandom.current().nextInt(sse.minTime, sse.maxTime + 1);
                lastEnemyTime=System.currentTimeMillis();
                spawnEn=false;
            }

            if (Math.abs(System.currentTimeMillis()-lastEnemyTime)>randomTime)
            {
                if (sse.typesEnemy.size()<1) {
                    throw new RuntimeException("types enemy is zero");
                }

                int randomEnemy = ThreadLocalRandom.current().nextInt(0, sse.typesEnemy.size());
                enemes.add(factoryEnemes.createEnemy(sse.typesEnemy.get(randomEnemy)));
                int randomSpeed = ThreadLocalRandom.current().nextInt(sse.minSpeed, sse.maxSpeed + 1);
                int randomSizeX = ThreadLocalRandom.current().nextInt(sse.minSize, sse.maxSize + 1);
                int randomSizeY = ThreadLocalRandom.current().nextInt(sse.minSize, sse.maxSize + 1);
                int randomXp = ThreadLocalRandom.current().nextInt(sse.minXP, sse.maxXp + 1);

                int randomDirect = ThreadLocalRandom.current().nextInt(0, 4 + 1);

                if (randomDirect==0)
                {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 1080 + 1);
                    enemes.get(enemes.size()-1).initEnemy(-200,randomY,randomSizeX,randomSizeY,randomSpeed,randomXp);
                }
                else if (randomDirect==1)
                {
                    int randomX = ThreadLocalRandom.current().nextInt(0, 1920 + 1);
                    enemes.get(enemes.size()-1).initEnemy(randomX,-200,randomSizeX,randomSizeY,randomSpeed,randomXp);
                }
                else if (randomDirect==2)
                {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 1080 + 1);
                    enemes.get(enemes.size()-1).initEnemy(2020,randomY,randomSizeX,randomSizeY,randomSpeed,randomXp);
                }
                else
                {
                    int randomX = ThreadLocalRandom.current().nextInt(0, 1920 + 1);
                    enemes.get(enemes.size()-1).initEnemy(randomX,1180,randomSizeX,randomSizeY,randomSpeed,randomXp);
                }

                spawnEn=true;
            }
        }
    }

    public void changeGan(TypeGan typeGan)
    {
        player.changeGan(typeGan);
    }

    public void movePlayer(double x, double y)
    {
        player.movePos(x,y);
        player.tryShoot();
    }

    public void deleteEnemy(int i)
    {
        enemes.remove(i);
    }

    public boolean attack(int enemy,int bullet)
    {

        if (enemes.get(enemy).damage(player.get_BulletsByInd(bullet).get_ImpactForce())) {
            deleteEnemy(enemy);
            player.deleteBullet(bullet);
            countKiils++;

            switch (countKiils) {
                case 2: {
                    sse.setSettings(170, 200, 1, 2, 1000, 3000, 2);
                    break;
                }
                case 3: {
                    sse.setSettings(150, 200, 1, 3, 1000, 3000, 2);
                    break;
                }
                case 5: {
                    sse.setSettings(130, 200, 1, 4, 1000, 2000, 3);
                    sse.setXp(4,6);
                    sse.addEnemy('4');
                    break;
                }

                case 10: {
                    sse.setSettings(100, 200, 2, 5, 500, 1500, 4);
                    sse.setXp(5,7);
                    sse.addEnemy('4');
                    sse.addEnemy('5');
                    break;
                }
                case 15: {
                    sse.setSettings(100, 150, 3, 5, 500, 1000, 5);
                    sse.setXp(6,9);
                    break;
                }
                case 20: {
                    sse.setSettings(50, 130, 4, 6, 400, 1000, 5);
                    sse.setXp(7,10);
                    break;
                }
                case 25: {
                    sse.setSettings(30, 100, 4, 7, 400, 1000, 6);
                    sse.setXp(8,11);
                    break;
                }
                case 30: {
                    sse.setSettings(30, 100, 5, 8, 300, 700, 7);
                    sse.setXp(8,12);
                    break;
                }
                case 35: {
                    sse.setSettings(20, 100, 6, 9, 1000, 1500, 9);
                    sse.setXp(9,13);
                    break;
                }
                case 40: {
                    sse.setSettings(20, 100, 7, 9, 100, 1500, 30);
                    sse.setXp(10,15);
                    break;
                }
            }

            return  true;
        }
        player.deleteBullet(bullet);
        return false;
    }

    public int get_CountKiils()
    {
        return countKiils;
    }

    public void gameOver()
    {
        enemes.clear();
        player.deleteBullets();


        bGamePlay=false;

        endTime=System.currentTimeMillis();

        timeHeldOut=endTime-startTime;


    }

    public void actionPerformed()
    {
        if (bGamePlay) {
            spawnRandomEnemy();
            moveEnemes();
            player.moveBullets();
        }
    }

    public long get_timeHeldOut()
    {

        return timeHeldOut;

    }




    public Player player;
    private ArrayList<Enemy> enemes =new ArrayList<Enemy>();
    private long lastEnemyTime=0;
    private boolean spawnEn=true;
    private int randomTime;

    private int countKiils=0;
    private boolean bGamePlay=true;

    private int maxSpeed=1;
    private int maxEnemes=1;
    private int minSize=200;

    private long startTime=0;
    private long endTime=0;
    private long timeHeldOut=0;
    private Factory_Enemes factoryEnemes;

    private SettingsSpawnEnemes sse;


}
