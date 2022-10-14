import java.awt.*;
import java.lang.Math;
public class Tank {
    public Rectangle rect;
    private int v0;
    private double g = -1.8;
    private double theta;
    public int width;
    public int height;
    public double [] abc;
    public double startT;
    public int center;
    public double size;
    boolean dropping = true;
    public Tank(double s, int x, int y) {
        size = s;
        width = (int)(50*size);
        height = (int)(30*size);
        rect = new Rectangle(x, y, width, height);
        center = width/2;
        v0 = 0;
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
    public int calculate_xy(double t) {
        // double v0, double angle, int steps
        double v0y = v0 * Math.sin(Math.toRadians(theta));
        // double totalTime = -2.0 * v0y / g;
        int y = (int) displacement(v0y, t, g);
        return y;
    }
    public void moveX(int xa, Background a){
        if ((a.leftwallIntersects(rect) && xa < 0) || (a.rightwallIntersects(rect) && xa > 0)) return;
        rect.x += xa;
    }
    public boolean moveY(int ya, Background a){
        rect.y += ya;
        if (rect.y < a.gameHeight && rect.y >= 0){
            return true;
        }
        else if (rect.y > a.gameHeight-rect.height){
            rect.y = a.gameHeight-rect.height;
        }
        else if (rect.y < 0){
            rect.y = 0;
        }
        return false;
        
    }
    public void move(Background a, double t, double speedTime) { // time
        if (!a.groundIntersects((rect))  && dropping) {
            int abc = calculate_xy(t-startT);
            dropping = this.moveY(-abc, a);
        }
    }
    public void draw(Graphics g) {
        // Color tankColor = new Color(1, 50, 32);
        // g.setColor(tankColor);
        // // g.fillRect(rect.x,rect.y, width, height);
        // g.setColor(Color.WHITE);
        // g.fillRect(rect.x,rect.y,width/3,height/2);
        // g.fillRect(rect.x+2*width/3,rect.y,width/3,height/2);
        

    }
    public double displacement(double v0, double t, double a) {
        return v0 * t + 0.5 * a * t * t;
    }

}