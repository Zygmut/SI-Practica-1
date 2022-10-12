/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ccf20
 */
public class RobotGui extends JFrame {
    
    private Kitchen kitchen;
    
    public RobotGui(){
        
        super("Robot de cocina");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        
    }
    
    public void showGui(){
        this.pack();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
    }
    
    private void initComponents(){
        kitchen = new Kitchen(10);
        this.add(kitchen);
    }
    
}
