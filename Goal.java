import java.awt.*;
public class Goal {

    private int centerX, centerY;
    public Rectangle rect;
    public int second;
    private int fps;
    public int movex;
    public int movey;
    public double pathx;
    public double pathy;
    public int angle;
    public boolean count;
    public Color color;
    public Goal(){
        
    }
    public Goal(int cX, int cY, int w, int h, int movx, int movy, int fp) {
        rect = new Rectangle(cX, cY,w,h);
        centerX = cX;
        centerY = cY;
        movey = movy;
        movex = movx;
        fps = fp;
        angle = 0;
    }

    public Color getColor() {
        if (count) {
            color =  new Color(randomColorNum(), randomColorNum(), randomColorNum());
        }
        count = !count;
        return color;
    }
    
    public int randomColorNum() {
        return (int)(Math.random() * 256);
    }
    public void movex(int x, Background a){
        rect.x+=x;
        if(a.intersectsGoal(rect)) rect.x-=x;
    
    }
    public void movey(int y, Background a){
        rect.y+=y;
        if(a.intersectsGoal(rect)) rect.y-=y;
        
    }
    public boolean intersectsGoal(Rectangle rects){
        return rect.intersects(rects);
    }
    public void move(double t, Background a) {
        if ((int) t > second){
            second += 1;
            pathx = ((Math.random()/2+1)*movex/fps);
            pathy = ((Math.random()/2+1)*movey/fps);
            if (Math.random() < 0.5) pathx *= -1;
            if (Math.random() < 0.5) pathy *= -1;
        }
        this.movex((int)pathx, a);
        this.movey((int)pathy, a);
    }
    
    public Rectangle getRectangle() {
        return rect;
    }
    
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(rect.x+10, rect.y-10, rect.width-20, rect.height-20);
        // g.fillOval(centerX-5, centerY-5, 10, 10);
    
    }
}