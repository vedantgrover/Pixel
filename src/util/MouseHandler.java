package util;

import window.elements.DrawingSpace;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private final DrawingSpace dp;

    public MouseHandler(DrawingSpace dp) {
        this.dp = dp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        dp.setColorOnGrid(e.getX() / (dp.getHeight() / 16), e.getY() / (dp.getHeight() / 16), dp.getCurrentColor());

        System.out.println("Current Square: (" + e.getX() / (dp.getHeight() / 16) + ", " + e.getY() / (dp.getHeight() / 16) + ")");
        System.out.println(dp.getWidth() / 16);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
