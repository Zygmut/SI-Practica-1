package gui;

import environment.Environment;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RobotGui extends JFrame {

    private final int INITIAL_SIZE = 15;
    
    private Kitchen kitchen;
    private OptionsPanel options;
    

    public RobotGui() {

        // Appereance
        super("Roomba Cleaner");
        this.setIconImage(new ImageIcon("src/main/java/images/roomba32.png").getImage());
        
        // Layout
        this.setLayout(new BorderLayout());
        
        // Functionality
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        initComponents();
    }

    public void showGui() {
        this.pack();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setMinimumSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        
        
        this.addNewKitchen(INITIAL_SIZE);
        
        this.options = new OptionsPanel(INITIAL_SIZE, this);
        this.add(this.options, BorderLayout.WEST);
    }

    public void setMap(Environment env) {
        this.kitchen.setMap(env);
    }
    
    public void addNewKitchen(int n){
        
        if(this.kitchen != null){
            this.remove(this.kitchen);
        }
        
        Environment env = new Environment(n);
        
        this.kitchen = new Kitchen(n, env);
        this.add(kitchen, BorderLayout.CENTER);
        
        this.revalidate();
        this.repaint();
    }

}
