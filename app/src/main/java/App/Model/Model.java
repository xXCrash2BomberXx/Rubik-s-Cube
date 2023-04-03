package App.Model;

import App.Control.Control;
import App.View.J3D;
import App.View.ViewLayer;
//import App.View.ViewAddRem;
import App.View.View;
import App.View.JObject;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Model {
    protected Point lastPoint = null;
    protected View view;
    protected int sensitivity = 10;
    protected J3D[] front;
    protected J3D[] right;
    protected J3D[] back;
    protected J3D[] left;
    protected J3D[] top;
    protected J3D[] bottom;

    public Model(Control control) {front = new J3D[9];
        int f = 0;
        right = new J3D[9];
        int r = 0;
        back = new J3D[9];
        int ba = 0;
        left = new J3D[9];
        int l = 0;
        top = new J3D[9];
        int t = 0;
        bottom = new J3D[9];
        int bo = 0;
        J3D[] objs = new J3D[3 * 3 * 3 - 1];
        int i = 0;
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                for (int z = 0; z < 3; z++)
                    if (x == 1 && y == 1 && z == 1)
                        continue;  // Skip rendering middle cube since isn't visible
                    else {
                        objs[i] = new JObject("cube", 175 + x * 50, 175 + y * 50, z * 50, 50, 50, 50,
                                225, 225, 25, new Color[] {
                                    Color.RED, Color.WHITE, Color.GREEN,
                                    Color.YELLOW, Color.BLUE, Color.ORANGE
                                }, new Color[] {Color.BLACK});
                        if (z == 0)
                            front[f++] = objs[i];
                        else if (z == 2)
                            back[ba++] = objs[i];
                        if (x == 0)
                            left[l++] = objs[i];
                        else if (x == 2)
                            right[r++] = objs[i];
                        if (y == 0)
                            top[t++] = objs[i];
                        else if (y == 2)
                            bottom[bo++] = objs[i];
                        i++;
                    }
        view = new ViewLayer(control, objs);
    }

    public Model(Control control, J3D[] objs) {
        view = new ViewLayer(control, objs);
    }

    public Model(Control control, J3D[] objs, int width, int height) {
        view = new ViewLayer(control, objs, width, height);
    }

    public void update(MouseEvent e) {
        Point newPoint = e.getPoint();
        if (lastPoint != null)
            view.rotate(e.getY() - lastPoint.getY(), -e.getX() + lastPoint.getX(), 0);
        lastPoint = newPoint;
    }

    public void update(MouseWheelEvent e) {
        view.rotate(0, 0, e.getPreciseWheelRotation() * sensitivity);
    }

    public void update(KeyEvent e) {
        System.out.println(Integer.toString(e.getKeyCode()) + ": " + e.getKeyChar());
    }
}
