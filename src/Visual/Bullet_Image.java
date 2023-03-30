package Visual;

import BusinessLogic.Bullet.Bullet;
import javax.swing.*;
import java.awt.*;

public class Bullet_Image extends JPanel {
    public Bullet_Image(Bullet bl)
    {
        setBackground(Color.YELLOW);
        setSize(bl.get_Size(),bl.get_Size());
        setLocation((int)bl.get_Pos().x,(int)bl.get_Pos().y);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void FUpdate(Bullet bl)
    {
        if (bl!=null) {
            setSize(bl.get_Size(),bl.get_Size());
            setLocation((int) bl.get_Pos().x, (int) bl.get_Pos().y);

            //System.out.println("Shoot2");
        }
    }
}
