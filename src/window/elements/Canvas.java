package window.elements;

import window.Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Canvas extends JPanel {
    public Canvas() {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        this.setBounds(Window.getWindowSize().width / 6, 0, (Window.getWindowSize().width * 5)/6, Window.getWindowSize().height);
        this.add(new DrawingSpace(this.getHeight()), new GridBagConstraints());
    }
}
