import java.awt.*;

public class Background {
    public Rectangle rect;
    public Background(){
        // return true if object is interesecting anything
        rect = new Rectangle(000, 800, 1000, 30);
        }
    public boolean intersects(Rectangle P){
    return rect.intersects(P);
    }
    public Color getColor(){
            return Color.GREEN;
        }
    public void draw(Graphics g){
        g.fillRect(rect.x,rect.y,rect.width,rect.height);
        g.setColor(getColor());
    }
}
