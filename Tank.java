import java.awt.*;
import java.lang.Math;
import java.util.Objects;

public class Tank {
    private Rectangle rect;
    private int v0;
    private double g = -1.8;
    private double theta;
    public int x;
    public int y;
    public int width;
    public int height;
    private int sx;
    private int sy;
    private double lastangle;
    private double lasttime;
    public double [] abc;
    public double startT;
    public Tank() {
        width = 50;
        height = 30;
        x = 0;
        y = 0;
        rect = new Rectangle(x, y, width, height);
        v0 = 0;
        theta = 0;
        sx = 0;
        sy = 0;
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
    public void move(Background a, double t) { // time

        if (!a.intersects((rect))) {
            abc = calculate_xy(t-startT);
            rect.x = sx + (int) abc[0];
            rect.y = sy - (int) abc[1];
            if (Objects.isNull(lasttime)) {
                lasttime = t;
                startT = t;
            } else {
                System.out.println(lastangle);
                double[] abcd = calculate_xy(lasttime);
                double rotx = abc[0] - abcd[0];
                double roty = abc[1] - abc[1];
                double currot = -Math.atan(roty / rotx);
                lastangle = currot;
                lasttime = t-startT;
            }
        }
    }
    public void draw(Graphics g) {
        g.setColor(getColor());
        // Graphics2D g2 = (Graphics2D) g;
        // Rectangle2D.Double rects = new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height);
        // // AffineTransform old = g2d.getTransform();
        // //
        // rects.setRect(-rect.width / 2, -rect.height / 2, rect.width, rect.height);
        // // g2d.setTransform(old);
        // g2.rotate(lastangle, rect.x, rect.y);
        // g2.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.GRAY);
        Color tankColor = new Color(1, 50, 32);
        g.setColor(tankColor);
        g.fillRect(x, y + (height/3) + 3, width, height/3);
        g.fillRect(x + (width/4), y + 3, width/2, height/3);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y  + (3*height/4), width, height/4);
        System.out.println("hi");
    }
    public double displacement(double v0, double t, double a) {
        return v0 * t + 0.5 * a * t * t;
    }

}

// constant for Earth's gravity acceleration in meters/second^2

// returns the displacement for a body under acceleration

// prints a table showing the trajectory of an object given
// its initial velocity v and angle and number of steps
// g.setColor(Color.WHITE);
// g.fillRect(0, 0, gameWidth, gameHeight);
// g.setColor(Color.GRAY);
// Color tankColor = new Color(1, 50, 32);
// g.setColor(tankColor);
// g.fillRect(player.x, player.y + (player.height/3) + 3, player.width, player.height/3);
// g.fillRect(player.x + (player.width/4), player.y + 3, player.width/2, player.height/3);
// g.setColor(Color.DARK_GRAY);
// g.fillRect(player.x, player.y  + (3*player.height/4), player.width, player.height/4);
