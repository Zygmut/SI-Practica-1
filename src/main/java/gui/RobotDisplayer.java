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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import utils.ImageLoader;
import utils.MutableBoolean;

/**
 *
 * @author ccf20
 */
public class RobotDisplayer extends JComponent {
    
    class Timer {
            private static final ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(10);
    
            private static void doPause(int ms) {
                try {
                    scheduledThreadPoolExecutor.schedule(() -> {
                    }, ms, TimeUnit.MILLISECONDS).get();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }

    private final int sleep_millis = 10;
    private final Robot robot;
    private final int incX = 2;
    private final int incY = 2;
    private final int incAngle = 2;
    private int prevPosX;
    private int prevPosY;
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
        this.prevPosX = this.position.x; 
        this.prevPosY = this.position.y;
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
        this.prevPosX = this.position.x + 1; 
        this.prevPosY = this.position.y;
        this.costat = costat;
        this.borde = borde;
    }
    
    public Point getTileIndices(){
        return this.robot.getPosition();
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
       
        this.rotate(kitchen, this.robot.getPosition().x, this.robot.getPosition().y);

        this.prevPosX = this.robot.getPosition().x;
        this.prevPosY = this.robot.getPosition().y;
        
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
            
            Timer.doPause(sleep_millis);

            kitchen.repaintRobotAndTiles();
        }

    }
    
    private void rotate(Kitchen kitchen, int targetPosX, int targetPosY){
        
        
        int finalAngle = getFinalAngle(targetPosX, targetPosY);
        
        int negFinalAngle = finalAngle - 360;
        
        finalAngle = Math.abs(finalAngle - this.rotationAngle) <= Math.abs(negFinalAngle - this.rotationAngle)
                ? finalAngle
                : negFinalAngle;
        
        if(finalAngle == 0 && this.rotationAngle > 180){
            finalAngle = 360;
        }
        
        int multiplier = this.rotationAngle <= finalAngle ? 1 : -1;
        
        int currentIncAngle = multiplier * this.incAngle;
        
        while (this.rotationAngle != finalAngle) {
            this.rotationAngle += currentIncAngle;

            
            //check if we have passed the target
            if (currentIncAngle > 0 && this.rotationAngle > finalAngle
                    || currentIncAngle < 0 && this.rotationAngle < finalAngle) {
                
                this.rotationAngle = finalAngle;
            }
            
            Timer.doPause(sleep_millis);

            kitchen.repaintRobotAndTiles();
        }
        
        if(this.rotationAngle < 0){
            this.rotationAngle += 360;
        }
        if(this.rotationAngle >= 360){
            this.rotationAngle -= 360;
        }
    }
    
    private int getFinalAngle(int targetPosX, int targetPosY){
        
        if(targetPosX < this.prevPosX){
            return 0;
        }
        
        if(targetPosX > this.prevPosX){
            return 180;
        }
        
        if(targetPosY < this.prevPosY){
            return 270;
        }
        
        if(targetPosY > this.prevPosY){
            return 90;
        }
        
        
        return this.rotationAngle;
        
    }

    @Override
    public void paintComponent(Graphics g1) {
        //super.paintComponent(g1);
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
