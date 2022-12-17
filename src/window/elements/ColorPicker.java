package window.elements;

import window.Window;

import javax.swing.*;
import java.awt.*;

public class ColorPicker extends JPanel implements Runnable {

    private static Color currentColor = new Color(0, 0, 0);

    private final Thread colorPickerThread;

    public ColorPicker() {
        colorPickerThread = new Thread(this);

        this.setBackground(Color.BLACK);
        this.setBounds(15, 15, (Window.getWindowSize().width / 6) - 30, (Window.getWindowSize().width / 6) - 30);

        JTextField rValue = new JTextField(3);
        rValue.setBounds((this.getWidth() / 2) - 50, 500, 30, 10);

        JTextField gValue = new JTextField(3);
        rValue.setBounds((this.getWidth() / 2) - 50, 500, 30, 10);

        JTextField bValue = new JTextField(3);
        rValue.setBounds((this.getWidth() / 2) - 50, 500, 30, 10);

        this.add(rValue);
        this.add(gValue);
        this.add(bValue);

        JButton submitButton = new JButton("Change Color");
        submitButton.setBounds((this.getWidth() / 2) - 50, 500, 60, 10);
        this.add(submitButton);
    }

    public void setCurrentColor(int r, int g, int b) {
        currentColor = new Color(r, g, b);
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    public static Color getCurrentColor() {
        return currentColor;
    }

    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (colorPickerThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                timer = 0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawRect((this.getWidth() / 2) - 50, 75, 100, 100);

        g.setColor(currentColor);
        g.fillRect((this.getWidth() / 2) - 50, 75, 100, 100);
    }
}
