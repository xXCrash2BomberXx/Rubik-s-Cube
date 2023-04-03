package App.Control;

import App.Model.Model;
import App.View.J3D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class Control implements MouseMotionListener, MouseWheelListener, KeyListener {
    protected Model model;

    public Control() {
        model = new Model(this);
    }

    public Control(J3D[] objs) {
        model = new Model(this, objs);
    }

    public Control(J3D[] objs, int width, int height) {
        model = new Model(this, objs, width, height);
    }

    public void mouseMoved(MouseEvent e) {
        model.update(e);
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        model.update(e);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        model.update(e);
    }

    public void keyReleased(KeyEvent e) {}
}
