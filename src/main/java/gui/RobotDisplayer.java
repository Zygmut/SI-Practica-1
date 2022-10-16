/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import agent.Robot;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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

    private final Robot robot;
    private final int incX = 2;
    private final int incY = 2;
    private final BufferedImage image;
    private Point position;
    private MutableBoolean active;
    private int width = 50;
    private int costat = 1;
    private int borde = 1;

    public RobotDisplayer(Robot robot) {
        this.robot = robot;
        this.position = new Point();
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
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(RobotDisplayer.class.getName()).log(Level.SEVERE, null, ex);
            }

            kitchen.repaint();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(image, position.x, position.y, this.width, this.width, null);
    }

}
