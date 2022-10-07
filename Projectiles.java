import java.awt.*;
import java.lang.Math;
import java.util.Objects;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Projectiles {
    private Rectangle rect;
    private int v0;
    private double g = -1.8;
    private double theta;
    private int sx;
    private int sy;
    private double lastangle;
    private double lasttime;
    public boolean hitwall = false;
    public Projectiles(int x, int y, int w, int h, int xs, int angles) {
        rect = new Rectangle(x, y, w, h);
        v0 = xs;
        theta = angles;
        sx = x;
        sy = y;
        lastangle = theta;
    }
    public Color getColor() {
        return Color.ORANGE;
    }
    public Rectangle getRectangle() {
        return rect;
    }
    public boolean intersects(Rectangle p) {
        return rect.intersects(p);
    }
    public double[] calculate_xy(double t) {
        // double v0, double angle, int steps
        double v0x = v0 * Math.cos(Math.toRadians(theta));
        double v0y = v0 * Math.sin(Math.toRadians(theta));
        // double totalTime = -2.0 * v0y / g;
        double x = v0x * t;
        double y = displacement(v0y, t, g);
        // double t_flight = 2*Math.sin(theta)/g;
        // t = np.linspace(0, t_flight, 100); //timesteps
        // double x = (u*Math.cos(theta)*t);
        // double y = (u*Math.sin(theta)*t - 0.5*g*t*t);
        double[] abc = { x, y };
        return abc;
    }
    public void move(double t, Background a) { // time
        if (!a.intersects((rect))) {
            double[] abc = calculate_xy(t);
            rect.x = sx + (int) abc[0];
            rect.y = sy - (int) abc[1];
            if (Objects.isNull(lasttime)) {
                lasttime = t;
            } else {
                double[] abcd = calculate_xy(lasttime);
                double rotx = abc[0] - abcd[0];
                double roty = abc[1] - abcd[1];
                lastangle = -Math.atan(roty / rotx);
                lasttime = t;
            }
        }
        else {
            hitwall = true;
        }
    }
    public void draw(Graphics g) {
        g.setColor(getColor());
        Graphics2D g2 = (Graphics2D) g;
        if (hitwall == false) {
        Rectangle2D.Double rects = new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height);
        // AffineTransform old = g2d.getTransform();
        //
        rects.setRect(-rect.width / 2, -rect.height / 2, rect.width, rect.height);
        // g2d.setTransform(old);
        g2.rotate(lastangle, rect.x, rect.y);
        g2.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
    public double displacement(double v0, double t, double a) {
        return v0 * t + 0.5 * a * t * t;
    }

}