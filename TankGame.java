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
    // private Rectangle player = new Rectangle(); //a rectangle that represents the player
    // private Rectangle goal = new Rectangle(); //a rectangle that represents the goal
    // private Enemy[] enemies = new Enemy[5]; //the array of Enemy objects
    // private Rectangle floor = new Rectangle();
    public Background bg;
    private boolean up, down, left, right; //booleans that track which keys are currently pressed
    private Timer timer; //the update timer
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int gameWidth = 1440; //the width of the game area
    private int gameHeight = (int) 810; //the height of the game area
    final int GRAVITY = 10;
    final int TERMINAL_VELOCITY = 300;
    public Goal b = new Goal();
    public Projectiles a;
    public double temptime = 0;
    private double speedTime = 1.25;
    public Tank player;
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
        
        frame.setResizable(false);
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
        bg = new Background();
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
            a = new Projectiles(100, 100, 50, 20, 30, 45);
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
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
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
        
        up = down = left = right = false;
    
    }
    public void update() {
        // int vertical_speed = 0;
        // int vertical_position;  
        if(up) {
            player.y-=3;
        }
        if(down) {
            player.y+=3;
        }
        if(left) {
            player.x-=3;
        }
        if(right) {
            player.x+=3;
        }
        
        if(player.x < 0) {
            player.x = 0;
            player.startT = (double) (System.currentTimeMillis() - startTime) /(speedTime*100);

        }
        else if(player.x + player.width > gameWidth) {
            player.x = gameWidth - player.width;
        }
        
        if(player.y < 0) {
            player.y = 0;
        }
        else if(player.y + player.height > gameHeight) {
            player.y = gameHeight - player.height;
        }
        // if(player.intersects(goal)) {
        //     JOptionPane.showMessageDialog(null, "You won!");
        //     setUpGame();
        // }
        
        // for(Enemy e: enemies) {
        //     if(e == null)
        //         continue;
        
        //     if(e.intersects(player)) {
        //         JOptionPane.showMessageDialog(null, "You lost");
        //         setUpGame();
        //     }
            
        //     e.move();
        // }
        
    }
    
    //The paint method does 3 things
    //1 - it draws a white background
    //2 - it draws the player in blue
    //3 - it draws the goal in green
    //4 - it draws all the Enemy objects
    
    public void paint(Graphics g) {
        /* 
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, gameWidth, gameHeight);
        g.setColor(Color.GRAY);
        Color tankColor = new Color(1, 50, 32);
        g.setColor(tankColor);
        g.fillRect(player.x, player.y + (player.height/3) + 3, player.width, player.height/3);
        g.fillRect(player.x + (player.width/4), player.y + 3, player.width/2, player.height/3);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(player.x, player.y  + (3*player.height/4), player.width, player.height/4);
        */
        player.move(bg,  (System.currentTimeMillis() - startTime) /(speedTime*100));
        player.draw(g);
        bg.draw(g);
        if (Objects.isNull(a) == false){
            a.move((double) (System.currentTimeMillis() - startTime) /(speedTime*100), bg);
            // System.out.println((System.currentTimeMillis() - startTime) / 100);
            a.draw(g);
        }
        else{
            startTime = System.currentTimeMillis();

        }
        // g.setColor(Color.GREEN);
        // g.fillRect(goal.x, goal.y, goal.width, goal.height);

        // g.fillRect(floor.x, floor.y, floor.width, floor.height);
        
        // for(Enemy e: enemies) {
        //     if(e == null)
        //         continue;
        //     e.draw(g);
        // }
    }

}

