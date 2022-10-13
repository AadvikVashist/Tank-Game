import java.awt.*;
import javax.swing.*;
import java.util.Objects;
import java.awt.event.*;
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
    public int angle = 45;
    public int velocity = 50;
    int space_counter = 0;

    //Sets up the basic GUI for the game
    public static void main(String[] args) {
        // GraphicsEnvironment graphics =
        // GraphicsEnvironment.getLocalGraphicsEnvironment();
        // GraphicsDevice device = graphics.getDefaultScreenDevice();
        JFrame frame = new JFrame();
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
        player = new Tank(1.0, 50, gameHeight-100);
        b = new Goal(500,500,80,80,200,200,60);
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
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
            space_counter += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
    
        timer = new Timer(1000 / 60, this); //roughly 30 frames per second
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
            if (velocity > 300)  velocity = 300;

        }
        if (speedown){
            velocity -=1;
            if (velocity < 0)  velocity = 0;

        }
        if (up){
            angle+=1;
        }
        if (down){
            angle -= 1;
        }
        if (space && space_counter == 0){
            a = new Projectiles(player.rect.x, player.rect.y, 50, 20, velocity, angle);
            space_counter+=1;
        }
        if (space && space_counter != 0 && a.hitwall == true){
            System.out.println("new projectile");
            a = new Projectiles(player.rect.x, player.rect.y, 50, 20, velocity,angle);
        }
        if(left) {
            player.moveX(-3,bg);
        }
        if(right) {
            player.moveX(3,bg);
        }
        

        
    }
    

    public void paint(Graphics g) {
        
        if (player.dropping) player.move(bg,  (System.currentTimeMillis() - startTime) /(speedTime*100), speedTime);
        player.draw(g);
        bg.draw(g);
        b.move((System.currentTimeMillis() - startTime) /(1000), bg);
        b.draw(g);
        if (Objects.isNull(a) == false && !a.hitwall){
            a.move((double) (System.currentTimeMillis() - startTimeT) /(100), bg);
            a.draw(g);
        }
        // else if(a.hitwall){
        //     a = null;
        // }
        else{
            startTimeT = System.currentTimeMillis();

        }
    }

}

