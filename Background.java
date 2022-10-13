import java.awt.*;
import javax.swing.*;
import java.util.Objects;
import java.awt.event.*;
public class Background {
    public Rectangle ground;
    public Rectangle leftwall;
    public Rectangle rightwall;
    public Rectangle topwall;
    public int gameWidth;
    public int gameHeight;
    public Background(int gamewidth, int gameheight){
        // return true if object is interesecting anything
        ground = new Rectangle(100, gameheight, 10, 300);
        leftwall = new  Rectangle(-10, 0,10, gameheight);
        rightwall = new  Rectangle(gamewidth, 0,10, gameheight);
        topwall = new  Rectangle(0, -10, gamewidth, 10);
        gameWidth = gamewidth;
        gameHeight = gameheight;
        }
    public boolean intersects(Rectangle P){
    if (ground.intersects(P) || leftwall.intersects(P) || rightwall.intersects(P) || topwall.intersects(P)) return true;
    else if(P.x < 0 || P.y < 0 || P.x+P.width > gameWidth || P.y+P.height > gameHeight) return true;
    else return false;
    }

    public boolean intersectsGoal(Rectangle P){
        if (ground.intersects(P) || leftwall.intersects(P) || rightwall.intersects(P) || topwall.intersects(P)) return true;
        else if(P.x < 0 || P.y < 0 || P.x+P.width > gameWidth || P.y+P.height > gameHeight-200) return true;
        else return false;
        }
    public boolean groundIntersects(Rectangle P){
        if (leftwall.intersects(P) || rightwall.intersects(P) || topwall.intersects(P)) return true;
        else return false;
        }
    public boolean leftwallIntersects(Rectangle P){
        return leftwall.intersects(P);
    }
    public boolean rightwallIntersects(Rectangle P){
        return rightwall.intersects(P);
    }
    public Color getColor(){
            return Color.GREEN;
        }
    public void draw(Graphics g){
        g.drawImage(new ImageIcon("tankBg.png"),0,0, null);

        // ground
        g.fillRect(ground.x,ground.y,ground.width,ground.height);
        g.fillRect(leftwall.x,leftwall.y,leftwall.width,leftwall.height);
        g.fillRect(rightwall.x,rightwall.y,rightwall.width,rightwall.height);
        g.fillRect(topwall.x,topwall.y,topwall.width,topwall.height);
        g.setColor(getColor());
        //walls
        //sky
    }

}
