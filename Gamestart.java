 /*
 * A swing BoxLayout example with different Boxlayout axis parameters
 * BoxLayout.X_AXIS, BoxLayout.Y_AXIS respectively.
 * Also add spaces between components.
 */
 
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;


public class Gamestart {
    public static void main(String[] args) {
        // ImageIcon background = new ImageIcon("Images/Startup/tank 2.jpeg");
        // ImageIcon backgrounds = new ImageIcon("Images/Startup/tank 1.jpeg");
        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Tank Game 45");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the panel to add buttons
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        // Set the BoxLayout to be X_AXIS: from left to right
        // BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        // Set the Boxayout to be Y_AXIS from top to down
        //BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        // Set border for the panel
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 300, 200)));
        //panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));     
        // Define new buttons
        JButton jb1 = new JButton("<html> <strong> START MISSION </strong> </html>", new ImageIcon("start.PNG") );
        jb1.setPreferredSize(new Dimension(800, 100));

        //Text
        JLabel controls = new JLabel("<html> <strong> Controls: </strong> <br/> A --> Move Left <br/> D --> Move Right <br/> W --> Increase shot power <br/> S --> Decrease shot power <br/> Left Arrow --> Rotate turret left <br/> Right Arrow --> Rotate turret right <br/> Space --> FIRE </html>");
        JLabel instructions = new JLabel("<html> <strong> Mission: </strong> <br/> You're the final tank in your squadron. Shoot the enemy helicopter out of the air! <br/><br/><br/><br/> <br/> </html>");

        //Set size and colors
        Border border = BorderFactory.createLineBorder(Color.black, 5);

        instructions.setPreferredSize(new Dimension(00, 200));
        // instructions.setForeground(Color. RED);
        instructions.setBackground(Color.lightGray);
        instructions.setOpaque(true);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        // instructions.setBorder(new EmptyBorder(10,10,10,10));//top,left,bottom,right
        instructions.setAlignmentX(5);
        instructions.setBorder(border);

        controls.setPreferredSize(new Dimension(400, 200));
        // instructions.setForeground(Color. RED);
        controls.setBackground(Color.lightGray);
        controls.setOpaque(true);
        controls.setBorder(border);

        JLabel tankOne = new JLabel(new ImageIcon("tank.png")); 
        tankOne.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tankOne.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        JLabel tankTwo = new JLabel(new ImageIcon("tank.png")); 
        tankTwo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tankTwo.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        // Add buttons to the frame (and spaces between buttons)
        //panel.add(Box.createRigidArea(new Dimension(0, 60)));   
        panel.add(tankOne);  
        panel.add(instructions);
        panel.add(controls);
        panel.add(tankTwo);
        panel.add(jb1);
        //panel.add(Box.createRigidArea(new Dimension(0, 60)));
        // Set size for the frame
        frame.setSize(300, 300);
        // Set the window to be visible as the default to be false
        frame.add(panel);
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jb1.setText("clicked");
                //         frame.setVisible(true);
                frame.setVisible(false);
                TankGame.main(args); //funsies
                

            }
        });
        frame.pack();
        frame.setVisible(true);
        
    }
    
}