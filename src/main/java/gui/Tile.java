package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/*
Classe que controla el funcionament d'una casella
 */
public class Tile extends JComponent {

    // Constants de la casella
    private final Color colorFonsBlanc = new Color(225, 225, 225);
    private final Color colorFonsAltre = new Color(30, 30, 30);

    // Atributs de la casella
    private int x;
    private int y;
    private int costat;
    private final boolean fons;
    private BufferedImage obstacleImage = null;
    private Boolean isObstacle = true;
    

    // Constructor de la casella
    public Tile(int i, int j, int costat, boolean fons, int borde) {
        this.costat = costat;
        x = j * this.costat + borde;
        y = i * this.costat + borde;
        this.fons = fons;
       
    }

    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }

    public void setObstacleImage(BufferedImage obstacleImage) {
        this.obstacleImage = obstacleImage;
    }

    public Boolean isObstacle() {
        return isObstacle;
    }

    public void setIsObstacle(Boolean isObstacle) {
        this.isObstacle = isObstacle;
    }
    
    

    // Mètode que pinta una casella
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        if (fons) {
            g2.setColor(colorFonsAltre);
        } else {
            g2.setColor(colorFonsBlanc);
        }
        if(isObstacle){
            g2.drawImage(obstacleImage, x, y, costat, costat, null);
        }else{
            g2.fill3DRect(x, y, costat, costat, true);
            g2.setColor(Color.GRAY); 
        }
        g2.drawRect(x, y, costat, costat);
    }

}
