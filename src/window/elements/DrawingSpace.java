package window.elements;

import util.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DrawingSpace extends JPanel implements Runnable {

    private static final Color[][] colors = new Color[16][16];
    // Settings
    private static boolean grid = false;
    private final Thread drawingThread = new Thread(this);
    private final Color currentColor = Color.BLACK;

    public DrawingSpace(int canvasHeight) {
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(canvasHeight / 2, canvasHeight / 2));

        MouseHandler mouseHandler = new MouseHandler(this);
        this.addMouseListener(mouseHandler);
        resetDrawingBoard();

        drawingThread.start();
    }

    public static void toggleGrid(boolean newGrid) {
        grid = newGrid;
    }

    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (drawingThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < colors.length; row++) {
            for (int col = 0; col < colors[row].length; col++) {
                int gridSquareSize = this.getHeight() / 16;
                g.setColor(colors[row][col]);
                g.fillRect(row * gridSquareSize, col * gridSquareSize, gridSquareSize, gridSquareSize);

                if (grid) {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, col * gridSquareSize, getWidth(), col * gridSquareSize);
                    g.drawLine(row * gridSquareSize, 0, row * gridSquareSize, getHeight());
                }
            }
        }
    }

    public void setColorOnGrid(int row, int col, Color color) {
        colors[row][col] = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void resetDrawingBoard() {
        for (Color[] color : colors) {
            Arrays.fill(color, Color.WHITE);
        }
    }

    public static void writeImage(String name) {
        String path = "res/images/" + name + ".png";
        BufferedImage image = new BufferedImage(colors.length, colors[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[x].length; y++) {
                image.setRGB(x, y, colors[x][y].getRGB());
            }
        }

        File ImageFile = new File(path);
        try {
            ImageIO.write(image, "png", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
