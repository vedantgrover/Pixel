package window.elements;

import window.Window;

import javax.swing.*;
import java.awt.*;

public class SideBar extends JPanel {
    public SideBar() {
        this.setBackground(new Color(50, 50, 50));
        this.setLayout(null);
        this.setBounds(0, 0, window.Window.getWindowSize().width / 6, Window.getWindowSize().height);
        this.add(new ColorPicker());
    }
}
