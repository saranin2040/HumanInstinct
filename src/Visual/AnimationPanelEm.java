package Visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class AnimationPanelEm extends JPanel {


    public AnimationPanelEm() {
        setOpaque(false);
        images = new BufferedImage[4];

            try {
                images[0] = ImageIO.read(new File("C:\\All\\OOP\\animation\\em1.png"));
                images[1] = ImageIO.read(new File("C:\\All\\OOP\\animation\\em2.png"));
                images[2] = ImageIO.read(new File("C:\\All\\OOP\\animation\\em3.png"));
                images[3] = ImageIO.read(new File("C:\\All\\OOP\\animation\\em4.png"));
            } catch (IOException e) {

            }
    }

    public void animate(double angle,double size) {
        this.angle=angle;
        this.size=size/main;

        //System.out.println(this.angle+" ");

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
        transform.rotate(Math.toRadians(angle), (images[currentFrame].getWidth(this) / 2)*size, (images[currentFrame].getHeight(this) / 2)*size);
        transform.scale(size,size);
        g2d.drawImage(images[currentFrame], transform, null);

    }

    private double size=1;

    final private double main=117.0;
    private double angle=0;
    private long startTime;
    private BufferedImage[] images;
    private int currentFrame = 0;
}