import java.awt.*;
import java.awt.image.BufferedImage;

public class PatternChessBoard implements Pattern {

    static Color BLACK = Color.BLACK;

    @Override
    public BufferedImage getPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int j = 0; j < HEIGHT; ++j) {
            for (int i = 0; i < WIDTH; ++i) {
                if ((i + j) % 2 == 0) {
                    g2d.setColor(color);
                    g2d.fillRect(i, j, 1, 1);
                } else {
                    g2d.setColor(BLACK);
                    g2d.fillRect(i, j, 1, 1);
                }
            }
        }
        g2d.dispose();
        return bi;
    }

    @Override
    public BufferedImage getBigPattern(Color color) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int j = 0; j < HEIGHT; j += 2) {
            for (int i = 0; i < WIDTH; i += 2) {
                if ((i/2 + j/2) % 2 == 0) {
                    g2d.setColor(color);
                    g2d.fillRect(i , j, 2, 2);
                } else {
                    g2d.setColor(BLACK);
                    g2d.fillRect(i, j, 2, 2);
                }
            }
        }
        g2d.dispose();
        return bi;
    }

}
