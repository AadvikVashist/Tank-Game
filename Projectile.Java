import java.awt.*;
import java.lang.Math;
import javax.swing.*;
public class Projectile{
    private Rectangle rect;
    private int u;
    private double g = 9.8;
    private int theta;
    public Projectile(){

    }
    public Projectile(int x, int y, int w, int h, int xs, int angles) {
        Rectangle rect = new Rectangle(x, y, w, h);
        u = xs;
        theta = Math.toRadians(angles);
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
    public int[] calculate_xy(double t){
        double t_flight = 2*u*np.sin(theta)/g
        t = np.linspace(0, t_flight, 100) //timesteps

        int x = u*np.cos(theta)*t
        y = u*np.sin(theta)*t - 0.5*g*t**2
        int [] abc = {x,y};
        return abc
    }
    public void move(double t) { //time
        int [] abc = calculate_xy(t);
        rect.x = abc[0];
        rect.y = abc[1];
        
    }
    
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

}


