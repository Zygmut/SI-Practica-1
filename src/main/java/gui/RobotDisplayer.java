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
import javax.swing.JComponent;
import utils.ImageLoader;
import utils.MutableBoolean;

/**
 *
 * @author ccf20
 */
public class RobotDisplayer extends JComponent{
    
    private final Robot robot;
    private final BufferedImage image;
    private Point position;
    private MutableBoolean active;
    private int width = 50;
    
    
    public RobotDisplayer(Robot robot){
        this.robot = robot;
        this.position = new Point();
        this.active = new MutableBoolean(false);
        this.image = ImageLoader.loadImage("src/main/java/images/roomba128.png");
    }
    
    public boolean isActive(){
        return this.active.is();
    }
    
    public void setActive(boolean value){
        this.active.setValue(value);
    }
    
    public MutableBoolean getIsActiveReference(){
        return this.active;
    }
    
    public void setInitialTile(int i, int j, int costat, int borde){
        this.robot.setPosition(i, j);
        this.width = (int)(costat * 0.9);
        this.setPositionFromTileIndices(i, j, costat, borde);
    }
    
    public void setPositionFromTileIndices(int i, int j, int costat, int borde){
        position.x = (j * costat + borde) + (int)(costat * 0.05);
        position.y = (i * costat + borde)  + (int)(costat * 0.05);
    }
    
    public boolean isOn(int i, int j){
        Point robotTilesPosition = this.robot.getPosition();
        return robotTilesPosition.x == i && robotTilesPosition.y == j;
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.red);
        g.drawImage(image, position.x, position.y, this.width, this.width, null);
    }
    
    
    
}
