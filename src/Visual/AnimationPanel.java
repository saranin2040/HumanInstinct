package Visual;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimationPanel extends JPanel {



    public AnimationPanel() {
        setOpaque(false);
        images = new BufferedImage[3];

            try {
                images[0] = ImageIO.read(new File("C:\\All\\OOP\\animation\\1.png"));
                images[1] = ImageIO.read(new File("C:\\All\\OOP\\animation\\2.png"));
                images[2] = ImageIO.read(new File("C:\\All\\OOP\\animation\\3.png"));
            } catch (IOException e) {

            }
    }

    public void animate(double angle) {
        this.angle=angle;
        if (Math.abs(startTime-System.currentTimeMillis())>100) {

            startTime=System.currentTimeMillis();
            currentFrame = (currentFrame + 1) % images.length;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        AffineTransform transform = new AffineTransform();

        if (angle<30 && angle>-30)
        {
            angle1=0;
        }
//        else if (angle<60 && angle>=30){
//            angle=45;
//        }
        else if (angle<120 && angle>=60){
            angle1=90;
        }
//        else if (angle<150 && angle>=120){
//            angle=135;
//        }
        else if ((angle<-150 && angle>=-180) ||(angle>=150 &&angle<=180)){
            angle1=180;
        }
//        else if (angle>=-150 && angle<-120){
//            angle=-135;
//        }
        else if (angle>=-120 && angle<=-30){
            angle1=-90;
        }
//        else if (angle>=-60 && angle<=-30){
//            angle=-45;
//        }

        transform.rotate(Math.toRadians(angle1), (images[currentFrame].getWidth(this) / 2), (images[currentFrame].getHeight(this) / 2));
        //transform.scale(1.7,1.7);
        g2d.drawImage(images[currentFrame], transform, null);

        //super.paintComponent(g);

           // g.drawImage(images[currentFrame], 0, 0, null);

    }

    private double angle=0;
    private double angle1=0;
    private long startTime;
    private BufferedImage[] images;
    private int currentFrame = 0;
}