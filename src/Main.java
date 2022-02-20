import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    enum Channel {
        RED, GREEN, BLUE, GREY
    }

    private static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }

    private static final int WIDTH = 256;
    private static final int HEIGHT = 192;

    private static final String OUTPUT = "C:\\Users\\Utente\\Desktop\\gamma\\";
    private static File IMG_OUTPUT;

    public static void main(String[] args) throws IOException {
        createGammaTestImages(Channel.GREY);
    }

    public static void createGammaTestImages(Channel channel) throws IOException {
        for (int i = 0; i < 256; i += 4) {
            StringBuilder folderName = new StringBuilder("" + i);
            while (folderName.length() != 3)
                folderName.insert(0, "0");

            File file = new File(OUTPUT + "\\" + channel.toString() + "\\" + folderName + "\\");
            if (file.mkdirs()) {
                IMG_OUTPUT = file;
                createGammaTestImages(channel, i);
            }

        }
    }

    private static void createGammaTestImages(Channel channel, int channelValue) throws IOException {
        int r = 0;
        int g = 0;
        int b = 0;

        switch (channel) {
            case RED -> r = channelValue;
            case GREEN -> g = channelValue;
            case BLUE -> b = channelValue;
            case GREY -> {
                r = channelValue;
                g = channelValue;
                b = channelValue;
            }
        }

        Color centeredSquareColor = new Color(r, g, b);

        for (double gamma = 1.0; gamma <= 2.6; gamma += 0.1) {
            Pair<Integer, Integer> backgroundColors = getBackgroundColorValue(channelValue, gamma);
            Color light = Color.CYAN;
            Color dark = Color.CYAN;
            switch (channel) {
                case RED -> {
                    light = new Color(backgroundColors.first, 0, 0);
                    dark = new Color(backgroundColors.second, 0, 0);
                }
                case GREEN -> {
                    light = new Color(0, backgroundColors.first, 0);
                    dark = new Color(0, backgroundColors.second, 0);
                }
                case BLUE -> {
                    light = new Color(0, 0, backgroundColors.first);
                    dark = new Color(0, 0, backgroundColors.second);
                }
                case GREY -> {
                    light = new Color(backgroundColors.first, backgroundColors.first, backgroundColors.first);
                    dark = new Color(backgroundColors.second, backgroundColors.second, backgroundColors.second);
                }
            }
            createGammaTestImage(centeredSquareColor, light, dark, gamma);
        }
    }

    private static void createGammaTestImage(Color centeredSquareColor, Color light, Color dark, double gamma)
            throws IOException {

        BufferedImage pattern = getChessBoardPattern(light, dark);
        BufferedImage[] patterns = new BufferedImage[] { pattern };
        BufferedImage gammaImage = getGammaImage(patterns, centeredSquareColor);

        Graphics2D g2d = gammaImage.createGraphics();
        BufferedImage viewingAngletest = getViewingAngleTest();
        g2d.drawImage(viewingAngletest, null, 0, 0);
        g2d.dispose();

        ImageIO.write(gammaImage, "png", new File(IMG_OUTPUT.getAbsolutePath() + "\\" + (int) (gamma * 100) + ".png"));
    }

    private static Pair<Integer, Integer> getBackgroundColorValue(double value, double gamma) {
        int tmp = 0;
        double colorValue =  Math.pow(2 * Math.pow(value, gamma) - Math.pow(tmp, gamma), 1.0 / gamma);
        while (colorValue < 0.0 || colorValue > 255.0) {
            tmp++;
            Color dark = new Color(tmp, tmp, tmp);
            colorValue =  Math.pow(2 * Math.pow(value, gamma) - Math.pow(tmp, gamma), 1.0 / gamma);
        }
        return new Pair<>((int)colorValue, tmp);
    }

    private static BufferedImage getChessBoardPattern(Color light, Color dark) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        for (int j = 0; j < HEIGHT; ++j) {
            for (int i = 0; i < WIDTH; ++i) {
                if ((i + j) % 2 == 0) {
                    g2d.setColor(light);
                    g2d.fillRect(i, j, 1, 1);
                } else {
                    g2d.setColor(dark);
                    g2d.fillRect(i, j, 1, 1);
                }
            }
        }
        g2d.dispose();
        return bi;
    }

    public static BufferedImage getGammaImage(BufferedImage[] patterns, Color color) {
        BufferedImage buf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buf.createGraphics();

        int counter = 0;
        for (int j = 0; j < HEIGHT; j++) {
            switch (j % 3) {
                case 0: counter = 0; break;
                case 1: counter = 2; break;
                case 2: counter = 4; break;
            }
            for (int i = 0; i < WIDTH; i++) {
                g2d.drawImage(patterns[counter % patterns.length], null, i * Pattern.WIDTH, j * Pattern.HEIGHT);
                counter++;
            }
            counter = 0;
        }

        g2d.setColor(color);
        int raggio = HEIGHT / 2;
        g2d.fillRect((WIDTH - raggio) / 2 , (HEIGHT - raggio) / 2, raggio, raggio);

        g2d.dispose();
        return buf;
    }

    public static BufferedImage getViewingAngleTest() {
        BufferedImage buf = new BufferedImage(50, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buf.createGraphics();

        Color lime = new Color(186, 255, 0);
        Color purple = new Color(186, 0, 255);
        Color grey = new Color(186, 186, 186);

        for (int i = 0; i < HEIGHT; ++i) {
            if (i % 2 == 0)
                g2d.setColor(lime);
            else
                g2d.setColor(purple);

            g2d.fillRect(0, i, 25, 1);
        }

        g2d.setColor(grey);
        g2d.fillRect(25, 0, 25, HEIGHT);

        g2d.dispose();

        return buf;
    }

}
