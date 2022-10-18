import java.awt.*;
import javax.swing.*;
import java.util.Objects;
import java.awt.event.*;
// import java.awt.Image.BufferedImage;
// import java.util.concurrent.TimeUnit;

/*
    Class:      ObstacleGame
    Purpose:    Creates a simple obstacle course for a player to navigate
    Author:     ___________________
    Date:       ___________________
*/


public class TankGame extends JPanel implements ActionListener, KeyListener {
    private long startTime;
    private long startTimeT;
    // private Rectangle player = new Rectangle(); //a rectangle that represents the player
    // private Rectangle goal = new Rectangle(); //a rectangle that represents the goal
    // private Enemy[] enemies = new Enemy[5]; //the array of Enemy objects
    // private Rectangle floor = new Rectangle();
    public Background bg;
    private boolean left, right, space, up, down, speedup, speedown; //booleans that track which keys are currently pressed
    private Timer timer; //the update timer
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int gameWidth = 1440; //the width of the game area
    private int gameHeight = (int) 810; //the height of the game area
    final int GRAVITY = 10;
    final int TERMINAL_VELOCITY = 300;
    public Goal b;
    public Projectiles a;
    public double temptime = 0;
    private double speedTime = 1.25;   
    public Tank player;
    public int angle = 0;
    public int velocity = 50;
    int space_counter = 0;
    public static int score = 0;
    static JFrame frame;
    private int xoffset;  private int yoffset;
    private JLabel scores;
    public int abcdefg = 0;
    //Sets up the basic GUI for the game
    public static void main(String[] args) {
        // GraphicsEnvironment graphics =
        // GraphicsEnvironment.getLocalGraphicsEnvironment();
        // GraphicsDevice device = graphics.getDefaultScreenDevice();
        frame = new JFrame();
        System.out.println("hi");
        frame.setTitle("Tank Game");
        frame.setLayout(new BorderLayout());

        TankGame game = new TankGame();
        
        frame.add(game, BorderLayout.CENTER);
        game.addKeyListener(game);
        frame.addKeyListener(game);

        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // frame.setUndecorated(false);
        frame.pack();
        frame.setState(Frame.NORMAL);
        // device.setFullScreenWindow(frame);
        game.setUpGame();
    }
    
    //Constructor for the game panel
    public TankGame() {
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        bg = new Background(gameWidth, gameHeight);
        player = new Tank(4.0, 100, gameHeight-100);
        b = new Goal(100,60,120,80,100,50,60);
        startTime = System.currentTimeMillis();
    }
    
    //Method that is called by the timer 30 times per second (roughly)
    //Most games go through states - updating objects, then drawing them
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    
    //Called every time a key is pressed
    //Stores the down state for use in the update method
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            space = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            down = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_W) {
            speedup = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            speedown = true;
        }

    }
    
    //Called every time a key is released
    //Stores the down state for use in the update method
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            down = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
            space_counter += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_W) {
            speedup = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            speedown = false;
        }
    }


    //Called every time a key is typed
    public void keyTyped(KeyEvent e) {
    }
    
    //Sets the initial state of the game
    //Could be modified to allow for multiple levels
    public void setUpGame() {
// ...
        
        if(timer != null) {
            timer.stop();
        }
    
        timer = new Timer(1000 / 120, this); //roughly 30 frames per second
        timer.start();
        
        left = right = false;
    
    }
    public void update() {
        // int vertical_speed = 0;
        // int vertical_position;  
        // if(up) {
        //     player.moveY(-3,bg);
        // }
        // if(down) {
        //     player.moveY(3,bg);
        // }
        if (speedup){
            velocity +=1;
            if (velocity > 100)  velocity = 100;

        }
        if (speedown){
            velocity -=1;
            if (velocity < 0)  velocity = 0;

        }
        if (up){
            angle+=1;
            if (angle >180) angle = 180;

        }
        if (down){
            angle -= 1;
            if (angle < 0) angle = 0;
        }
        if (space && space_counter == 0){
            a = new Projectiles(player.rect.x+xoffset, player.rect.y+yoffset, 50, 10, velocity, angle);
            space_counter+=1;
        }
        if (space && space_counter != 0 && a.hitwall == true){
            a = new Projectiles(player.rect.x+xoffset, player.rect.y+yoffset, 50, 10, velocity,angle);
        }
        if(left) {
            player.moveX(-3,bg);
        }
        if(right) {
            player.moveX(3,bg);
        }
        

        
    }
    

    public void paint(Graphics g) {
        final Image images = new ImageIcon("tankBg.png").getImage(); //You need this
        Graphics2D g2 = (Graphics2D) g;
        

        g2.drawImage(images, 0, 0, gameWidth,gameHeight, frame); //to this
        if (player.dropping) player.move(bg,  (System.currentTimeMillis() - startTime) /(speedTime*100), speedTime);
        player.draw(g);
        Image bottom;
        Image top;
        Image turret = new ImageIcon("tankTurret.png").getImage();
        if (player.last_move_right){
            bottom = new ImageIcon("tankBodyRight.png").getImage(); //You need this
            g2.drawImage(bottom, player.rect.x, player.rect.y, player.rect.width, player.rect.height, frame); //to this
        }
        else{
            bottom = new ImageIcon("tankBodyLeft.png").getImage(); //You need this
            g2.drawImage(bottom, player.rect.x, player.rect.y, player.rect.width, player.rect.height, frame); //to this
        }
        int w = (int)((double)turret.getWidth(frame) / (double)bottom.getWidth(frame)*(double)player.rect.width);
        int h = (int)((double)turret.getHeight(frame) / (double)bottom.getHeight(frame)*(double)player.rect.height);
        Graphics copy = g.create();
        int xoff; int yoff;
        if (angle < 90){
            top = new ImageIcon("tankTopRight.png").getImage(); //You need this
            g2.drawImage(top, player.rect.x, player.rect.y, player.rect.width, player.rect.height, frame); //to this
            Graphics2D g3 = (Graphics2D) copy;
            xoff = (int)((double)player.rect.width*0.68);
            yoff = (int)((double)player.rect.height*0.1);
            g3.rotate(Math.toRadians(-angle),  player.rect.x+ xoff, player.rect.y+ yoff+ (double)turret.getHeight(frame)/2);
            g3.drawImage(turret,player.rect.x+ xoff, player.rect.y+ yoff, w, h, frame);
        }
        else{
            top = new ImageIcon("tankTopLeft.png").getImage(); //You need this
            g2.drawImage(top, player.rect.x, player.rect.y, player.rect.width, player.rect.height, frame); //to this
            Graphics2D g3 = (Graphics2D) copy;
            xoff = (int)((double)player.rect.width/3.4);
            yoff = (int)((double)player.rect.height*0.1+turret.getHeight(frame)*0.5);
            g3.rotate(Math.toRadians(-angle),  player.rect.x+ xoff, player.rect.y+ yoff+ (double)turret.getHeight(frame)/2);
            g3.drawImage(turret,player.rect.x+ xoff, player.rect.y+ yoff, w, h, frame);
        }
        xoffset = xoff + (int)(turret.getWidth(frame)*Math.acos(Math.toRadians(angle))*1.5);
        yoffset = yoff + (int)(turret.getWidth(frame)*Math.asin(Math.toRadians(angle))*1.5);
        copy.dispose();
        // if (angle < 90){
        //     image = new ImageIcon("tankBodyRight.png").getImage(); //You need this
        //     int w = (int)((double)top.getWidth(frame) / (double)image.getWidth(frame)*(double)player.rect.width);
        //     int h = (int)((double)top.getHeight(frame) / (double)image.getHeight(frame)*(double)player.rect.height);
        //     g3.rotate(Math.toRadians(-angle),  player.rect.x+ player.rect.width/2+40, player.rect.y+10+h/2);
        //     g3.drawImage(top, player.rect.x+ player.rect.width/2+40,  player.rect.y+10, w, h, frame); //to this
        // }
        // else{
        //     image = new ImageIcon("tankBodyLeft.png").getImage(); //You need thisddddd
        //     int w = (int)((double)top.getWidth(frame) / (double)image.getWidth(frame)*(double)player.rect.width);
        //     int h = (int)((double)top.getHeight(frame) / (double)image.getHeight(frame)*(double)player.rect.height);
        //     g3.rotate(Math.toRadians(-angle),  player.rect.x+40, player.rect.y+10+h/2);
        //     g3.drawImage(top, player.rect.x +40,  player.rect.y+10, w, h, frame); //to this
        // }
        bg.draw(g);
        b.move((System.currentTimeMillis() - startTime) /(1000), bg);
        b.draw(g);
        final Image imager = new ImageIcon("Hoop.png").getImage(); //You need this
        g2.drawImage(imager, b.rect.x+10, b.rect.y-10, b.rect.width-20,b.rect.height-20, frame); //to this
        // boolean argser = true;
        if (Objects.isNull(scores)){
            scores = new JLabel("Score : " + 0 + "\n                 Time = 0");
            frame.add(scores, BorderLayout.NORTH);
        }
        if (Objects.isNull(a) == false && !a.hitwall){
            if (b.intersectsGoal(a.rect)){
                a.hitwall = true;
                // argser = false;
                frame.add(scores, BorderLayout.NORTH);
                score +=1;
                scores.setText("Score : " + score + "\n                 Time = " +(int) ((System.currentTimeMillis() - startTime) / 1000));

            }
            a.move((double) (System.currentTimeMillis() - startTimeT) /(100), bg);
            a.draw(g);
            if (abcdefg < ((System.currentTimeMillis() - startTime) / 1000)){
                abcdefg +=1;
                frame.add(scores, BorderLayout.NORTH);
                scores.setText("Score : " + score + "\n                 Time = " +(int) ((System.currentTimeMillis() - startTime) / 1000));
            }
        }
        else if (abcdefg < ((System.currentTimeMillis() - startTime) / 1000)){
            abcdefg +=1;
            frame.add(scores, BorderLayout.NORTH);
            scores.setText("Score : " + score + "\n                 Time = " +(int) ((System.currentTimeMillis() - startTime) / 1000));
        }

        // else if(a.hitwall){
        //     a = null;
        // }
        else{
            startTimeT = System.currentTimeMillis();
        }
        // argser = false;
        if ((System.currentTimeMillis() - startTime) / 1000 > 40){
            frame.setVisible(false);
            frame.dispose();
            Gameend a = new Gameend(score,(int) (System.currentTimeMillis() - startTime) / 1000); //funsies
            a.hi();
        } 
    }

}

