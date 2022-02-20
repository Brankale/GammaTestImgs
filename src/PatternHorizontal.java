import java.awt.*;
import java.awt.image.BufferedImage;

public class PatternHorizontal implements Pattern {

    @Override
    public BufferedImage getPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int i = 0; i < HEIGHT; ++i) {
            if (i % 2 == 0) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, i, WIDTH, 1);
            } else {
                g2d.setColor(color);
                g2d.fillRect(0, i, WIDTH, 1);
            }
        }
        g2d.dispose();
        return bi;
    }

    @Override
    public BufferedImage getBigPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int i = 1; i <= HEIGHT / 2; ++i) {
            if (i % 2 == 0) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, i * 2, WIDTH, 2);
            } else {
                g2d.setColor(color);
                g2d.fillRect(0, i * 2, WIDTH, 2);
            }
        }
        g2d.dispose();
        return bi;
    }

}
