import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JPanel {
    private int x = 0;
    private int y = 0;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.RED);
        g2.fillOval(x, y, 20, 20);
    }

    public void move() {
        x = (int) (200 + 100 * Math.cos(Math.toRadians(angle)));
        y = (int) (200 + 100 * Math.sin(Math.toRadians(angle)));
        angle += 1.0;
        if (angle >= 360)
            angle = 0;
    }

    private double angle = 0;

//    public static void main(String[] args) throws InterruptedException {
//        JFrame frame = new JFrame("Circle Animation");
//        test circle = new test();
//        frame.add(circle);
//        frame.setSize(450, 450);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        while (true) {
//            circle.move();
//            circle.repaint();
//            Thread.sleep(10);
//        }
//    }
}