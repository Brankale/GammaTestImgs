import java.awt.*;
import java.awt.image.BufferedImage;

public class PatternVertical implements Pattern {

    @Override
    public BufferedImage getPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int i = 0; i < WIDTH; ++i) {
            if (i % 2 == 0) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(i, 0, 1, HEIGHT);
            } else {
                g2d.setColor(color);
                g2d.fillRect(i, 0, 1, HEIGHT);
            }
        }
        g2d.dispose();
        return bi;
    }

    @Override
    public BufferedImage getBigPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int i = 0; i < WIDTH; ++i) {
            if (i % 2 == 0) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(i * 2, 0, 2, HEIGHT);
            } else {
                g2d.setColor(color);
                g2d.fillRect(i * 2, 0, 2, HEIGHT);
            }
        }
        g2d.dispose();
        return bi;
    }

}
