package gui;

import environment.Environment;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.ImageLoader;

public class RobotGui extends JFrame {

    private final int INITIAL_SIZE = 10;
    
    private Kitchen kitchen;
    

    public RobotGui() {

        super("Robot de cocina");
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
        
        BufferedImage im = ImageLoader.loadImage("src/main/java/images/box.png");
        
        Environment env = new Environment(INITIAL_SIZE);
        
        
        kitchen = new Kitchen(INITIAL_SIZE, env);
        kitchen.setObstacleImage(im);
        this.add(kitchen);
    }

    public void setMap(Environment env) {
        this.kitchen.setMap(env);
    }

}
