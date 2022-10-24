package gui;

import agent.Robot;
import environment.Environment;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RobotGui extends JFrame {

    private final int INITIAL_SIZE = 15;

    private Kitchen kitchen;
    private OptionsPanel options;
    private Environment<Robot> env;

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

        this.options = new OptionsPanel(INITIAL_SIZE, this);
        this.add(this.options, BorderLayout.WEST);

        this.addNewKitchen(INITIAL_SIZE);
        
        this.setJMenuBar(new MenuBar(this));

    }

    public void setEnv(Environment<Robot> env) {
        addNewKitchen(env);
        options.setText(Integer.toString(env.getSize()));
        this.kitchen.paintAll();
    }

    public void addNewKitchen(Environment<Robot> env) {

        if (this.kitchen != null) {
            this.remove(this.kitchen);
        }

        this.env = env;
        RobotDisplayer robotDisplayer = new RobotDisplayer((Robot) env.getAgent());

        this.options.setRobotDisplayerActiveReference(robotDisplayer.getIsActiveReference());

        this.options.setRobotActive(false);

        this.kitchen = new Kitchen(this.env.getSize(), this, env, robotDisplayer);
        this.add(kitchen, BorderLayout.CENTER);

        this.options.updateRobotSpeedFactor();

        this.revalidate();
        this.repaint();

    }

    public void addNewKitchen(int n) {

        if (this.kitchen != null) {
            this.remove(this.kitchen);
        }

        this.env = new Environment<>(n);
        this.env.setAgent(new Robot());
        RobotDisplayer robotDisplayer = new RobotDisplayer((Robot) env.getAgent());

        this.options.setRobotDisplayerActiveReference(robotDisplayer.getIsActiveReference());

        this.options.setRobotActive(false);

        this.kitchen = new Kitchen(n, this, env, robotDisplayer);
        this.add(kitchen, BorderLayout.CENTER);

        this.options.updateRobotSpeedFactor();

        this.revalidate();
        this.repaint();

    }

    public void setRobotActive(boolean value) {
        this.options.setRobotActive(value);
    }

    public void setRobotDisplayerSpeedFactor(double speedFactor) {
        this.kitchen.setRobotDisplayerSpeedFactor(speedFactor);
    }
    
    public void saveMap(){
        String value = JOptionPane.showInputDialog(this, "Selecciona el nombre del archivo", "Guardar archivo", JOptionPane.QUESTION_MESSAGE);
        if(value == null || value.equals("")) return;
        this.env.saveMap(value);
    }
    
    public void loadMap(){
        
        JFileChooser fileChooser = new JFileChooser("test_maps/");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Maps of kitchen", "map"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int value = fileChooser.showOpenDialog(this);
        if(value == JFileChooser.APPROVE_OPTION){
            Environment<Robot> env = new Environment<>(Environment.useMap(fileChooser.getSelectedFile().getName()));
            Robot r = new Robot();
            env.setAgent(r);
            this.setEnv(env);
        }
        
    }
    
    public void resetKitchen(){
        this.kitchen.resetAll();
    }

}
