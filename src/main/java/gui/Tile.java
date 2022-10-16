package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import utils.MutableBoolean;

/*
Classe que controla el funcionament d'una casella
 */
public class Tile extends JComponent {

    // Variables estaticas
    private static BufferedImage currentImage;
    
    // Constants de la casella
    private final Color colorFonsBlanc = new Color(225, 225, 225);
    private final Color colorFonsAltre = new Color(30, 30, 30);

    // Atributs de la casella
    private int x;
    private int y;
    private int costat;
    private final boolean fons;
    private BufferedImage obstacleImage = null;
    private MutableBoolean isObstacle = new MutableBoolean(false);
    

    // Constructor de la casella
    public Tile(int i, int j, int costat, boolean fons, int borde) {
        this.costat = costat;
        x = j * this.costat + borde;
        y = i * this.costat + borde;
        this.fons = fons;
       
    }
    
    public static void setObstacleImage(BufferedImage obstacleImage){
        Tile.currentImage = obstacleImage;
    }

    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }

    public void setIsObstacle(boolean value) {
        this.isObstacle.setValue(value);
        this.obstacleImage = Tile.currentImage;
    }
    
    public void toggleIsObstacle(){
        this.isObstacle.toggle();
        if(this.isObstacle()){
            this.obstacleImage = Tile.currentImage;
        }
    }

    public boolean isObstacle() {
        return isObstacle.is();
    }

    public void setIsObstacleReference(MutableBoolean isObstacle) {
        this.isObstacle = isObstacle;
    }
    
    

    // Mètode que pinta una casella
    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        if (fons) {
            g2.setColor(colorFonsAltre);
        } else {
            g2.setColor(colorFonsBlanc);
        }
        
        g2.fill3DRect(x, y, costat, costat, true);
        g2.setColor(Color.GRAY); 
        g2.drawRect(x, y, costat, costat);
        
        if(isObstacle()){
            g2.drawImage(obstacleImage, x + (int)(costat*0.05), y + (int)(costat*0.05), (int)(costat*0.90), (int)(costat*0.90), null);
        }
    }

}
