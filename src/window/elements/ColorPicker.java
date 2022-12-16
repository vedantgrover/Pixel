package window.elements;

import window.Window;

import javax.swing.*;
import java.awt.*;

public class ColorPicker extends JPanel {

    public ColorPicker() {
        this.setBackground(Color.BLACK);
        this.setBounds(15, 15, (Window.getWindowSize().width / 6) - 30, (Window.getWindowSize().width / 6) - 30);
    }
}
