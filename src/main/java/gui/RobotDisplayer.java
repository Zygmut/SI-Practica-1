/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import agent.Robot;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import utils.ImageLoader;
import utils.MutableBoolean;

/**
 *
 * @author ccf20
 */
public class RobotDisplayer extends JComponent {

    private final int sleep_millis = 10;
    private final Robot robot;
    private final int incX = 2;
    private final int incY = 2;
    private final int incAngle = 2;
    private int prevIncX = 0;
    private int prevIncY = -1 * incY;
    private final BufferedImage image;
    private Point position;
    private MutableBoolean active;
    private int width = 50;
    private int costat = 1;
    private int borde = 1;
    private int rotationAngle = 0;
    

    public RobotDisplayer(Robot robot) {
        this.robot = robot;
        this.position = new Point(-1, -1);
        this.active = new MutableBoolean(false);
        this.image = ImageLoader.loadImage("src/main/java/images/roomba128.png");
    }

    public boolean isActive() {
        return this.active.is();
    }

    public void setActive(boolean value) {
        this.active.setValue(value);
    }

    public MutableBoolean getIsActiveReference() {
        return this.active;
    }

    public void setInitialTile(int i, int j, int costat, int borde) {
        this.robot.setPosition(i, j);
        this.width = (int) (costat * 0.9);
        this.position = this.calculatePositionFromTileIndices(i, j, costat, borde);
        this.costat = costat;
        this.borde = borde;
    }

    public Point calculatePositionFromTileIndices(int i, int j, int costat, int borde) {
        Point p = new Point();
        p.x = (j * costat + borde) + (int) (costat * 0.05);
        p.y = (i * costat + borde) + (int) (costat * 0.05);
        return p;
    }

    public boolean isOnTile(int i, int j) {
        Point robotTilesPosition = this.robot.getPosition();
        return robotTilesPosition.x == i && robotTilesPosition.y == j;
    }
    
    public void temporalMoveRobot(int i, int j){
        this.robot.setPosition(this.robot.getPosition().x + i, this.robot.getPosition().y + j);
    }

    public void move(Kitchen kitchen) {
        Point robotCoordinates = calculatePositionFromTileIndices(this.robot.getPosition().x, this.robot.getPosition().y, this.costat, this.borde);
        int multiplierX = (int) Math.signum(Integer.compare(robotCoordinates.x, this.position.x));
        int multiplierY = (int) Math.signum(Integer.compare(robotCoordinates.y, this.position.y));
        int currentIncX = incX * multiplierX;
        int currentIncY = incY * multiplierY;
       
        this.rotate(kitchen, currentIncX, currentIncY);

        while (this.position.x != robotCoordinates.x || this.position.y != robotCoordinates.y) {
            this.position.x += currentIncX;
            this.position.y += currentIncY;

            //check if we have passed the target
            if (currentIncX > 0 && this.position.x > robotCoordinates.x
                    || currentIncX < 0 && this.position.x < robotCoordinates.x) {
                
                this.position.x = robotCoordinates.x;
            }
            
            //check if we have passed the target
            if (currentIncY > 0 && this.position.y > robotCoordinates.y
                    || currentIncY < 0 && this.position.y < robotCoordinates.y) {
                
                this.position.y = robotCoordinates.y;
            }
            
            try {
                Thread.sleep(sleep_millis);
            } catch (InterruptedException ex) {
                Logger.getLogger(RobotDisplayer.class.getName()).log(Level.SEVERE, null, ex);
            }

            kitchen.repaint();
        }

    }
    
    private void rotate(Kitchen kitchen, int currentIncX, int currentIncY){
        
        
        int finalAngle = getFinalAngle(currentIncX, currentIncY);
        
        this.prevIncX = currentIncX;
        this.prevIncY = currentIncY;
        
        int multiplier = this.rotationAngle < finalAngle ? 1 : -1;
        
        int currentIncAngle = multiplier * this.incAngle;
        
        while (this.rotationAngle != finalAngle) {
            this.rotationAngle += currentIncAngle;

            
            //check if we have passed the target
            if (currentIncAngle > 0 && this.rotationAngle > finalAngle
                    || currentIncAngle < 0 && this.rotationAngle < finalAngle) {
                
                this.rotationAngle = finalAngle;
            }
            
            try {
                Thread.sleep(sleep_millis);
            } catch (InterruptedException ex) {
                Logger.getLogger(RobotDisplayer.class.getName()).log(Level.SEVERE, null, ex);
            }

            kitchen.repaint();
        }
    }
    
    private int getFinalAngle(int currentIncX, int currentIncY){
        

        if(currentIncX < this.prevIncX){
            return 0;
        }
        
        if(currentIncX > this.prevIncX){
            return 90;
        }
        
        if(currentIncY < this.prevIncY){
            return 270;
        }
        
        if(currentIncY > this.prevIncY){
            return 180;
        }
        
        
        return 0;
        
    }

    @Override
    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(Color.red);

        
        //Draw rotated image
        AffineTransform backup = g.getTransform();
        
        AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), position.x + this.width/2, position.y + this.width/2);
        
        g.setTransform(a);
        
        g.drawImage(image, position.x, position.y, this.width, this.width, null);
        
        g.setTransform(backup);
    }

}