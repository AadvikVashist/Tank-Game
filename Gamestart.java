 /*
 * A swing BoxLayout example with different Boxlayout axis parameters
 * BoxLayout.X_AXIS, BoxLayout.Y_AXIS respectively.
 * Also add spaces between components.
 */
 
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Insets;
import javax.swing.JPanel;
// import java.awt.*;
import java.awt.event.*;

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
        // Set the BoxLayout to be X_AXIS: from left to right
        // BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        // Set the Boxayout to be Y_AXIS from top to down
        //BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        // Set border for the panel
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        //panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));     
        // Define new buttons
        JButton jb1 = new JButton("Start", new ImageIcon("start.PNG") );
        JLabel jb2 = new JLabel("this game will be super cool");
        JLabel dieFace = new JLabel(new ImageIcon("image.PNG")); 
        dieFace.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        dieFace.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        panel.add(dieFace);
        // Add buttons to the frame (and spaces between buttons)
        panel.add(jb1);
        //panel.add(Box.createRigidArea(new Dimension(0, 60)));     
        panel.add(jb2);
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