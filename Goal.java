import java.awt.*;
public class Goal {

    private int centerX, centerY;
    private int radius;
    private double angle;
    private Rectangle rect;
    public Goal(){
        
    }
    public Goal(int cX, int cY, int w, int h, int r) {
        rect = new Rectangle(cX + r - w/2, cY - h/2, w, h);
        centerX = cX;
        centerY = cY;
        radius = r;
        angle = 0;
    }

    public Color getColor() {
        return new Color(randomColorNum(), randomColorNum(), randomColorNum());
    }
    
    public int randomColorNum() {
        return (int)(Math.random() * 256);
    }

    public void move() {
    
        angle += 0.1;
        
        Rectangle rect = getRectangle();
        rect.x = (int)(0.5*(centerX + radius*Math.cos(angle))) - rect.width/2;
        rect.y = (int)(centerY + radius*Math.sin(angle)) - rect.height/2;
    }
    
    public Rectangle getRectangle() {
        return rect;
    }
    
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(Color.BLACK);
        g.fillOval(centerX-5, centerY-5, 10, 10);
    
    }
}