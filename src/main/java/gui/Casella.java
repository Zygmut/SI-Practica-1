package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
Classe que controla el funcionament d'una casella
 */
public class Casella extends JComponent {

    // Constants de la casella
    private final Color colorFonsBlanc = new Color(225, 225, 225);
    private final Color colorFonsAltre = new Color(30, 30, 30);

    // Atributs de la casella
    private int x;
    private int y;
    private int costat;
    private final boolean fons;
    private BufferedImage obstacleImage = null;
    private boolean isObstacle = true;
    

    // Constructor de la casella
    public Casella(int i, int j, int costat, boolean fons, int borde) {
        this.costat = costat;
        x = j * this.costat + borde;
        y = i * this.costat + borde;
        this.fons = fons;
        
        //Temporal
        try {
            obstacleImage = ImageIO.read(new File("images/box.png"));
        } catch (IOException ex) {
            Logger.getLogger(Casella.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
        }else{
            g2.fill3DRect(x, y, costat, costat, true);
            g2.setColor(Color.GRAY); 
        }
        g2.drawRect(x, y, costat, costat);
    }

}
