import java.awt.*;
import java.awt.image.BufferedImage;

public interface Pattern {

    int WIDTH = 256;
    int HEIGHT = 192;

    BufferedImage getPattern(Color color);
    BufferedImage getBigPattern(Color color);

}
