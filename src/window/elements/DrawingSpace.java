package window.elements;

import util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class DrawingSpace extends JPanel implements Runnable {

    private static final Color[][] colors = new Color[16][16];
    // Settings
    private static boolean grid = false;
    private Color currentColor = Color.BLACK;
    private final Thread drawingThread = new Thread(this);

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

    public static Color[][] getImage() {
        return colors;
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
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                timer = 0;
            }
        }
    }

    private void update() {
        currentColor = ColorPicker.getCurrentColor();
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

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }

    public void resetDrawingBoard() {
        for (int row = 0; row < colors.length; row++) {
            Arrays.fill(colors[row], Color.WHITE);
        }
    }
}
