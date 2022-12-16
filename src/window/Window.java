package window;

import util.MouseHandler;
import window.elements.Canvas;
import window.elements.DrawingSpace;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

public class Window extends JFrame {

    private static final Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Window() {
        this.setPreferredSize(windowSize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.setJMenuBar(createMenuBar());
        this.add(new Canvas());

        this.pack();

        this.setVisible(true);
    }

    public static Dimension getWindowSize() {
        return windowSize;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createToolsMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        return fileMenu;
    }

    private JMenu createToolsMenu() {
        JMenu toolsMenu = new JMenu("Tools");
        JCheckBoxMenuItem gridItem = new JCheckBoxMenuItem("Grid");
        gridItem.setMnemonic(KeyEvent.VK_G);
        gridItem.addActionListener(e -> {
            DrawingSpace.toggleGrid(gridItem.getState());
        });
        toolsMenu.add(gridItem);
        return toolsMenu;
    }
}
