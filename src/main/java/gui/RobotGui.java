package gui;

import environment.Environment;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import agent.Robot;

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

}
