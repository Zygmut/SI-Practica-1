/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
/*
Classe que controla el funcionament d'una casella
 */
public class Casella extends JComponent {

    //Constants de la casella
    private final Color colorFonsBlanc = new Color(225,225,225);
    private final Color colorFonsAltre = new Color(30,30,30);

    //Atributs de la casella
    private int x;
    private int y;
    private int costat;
    private boolean fons;

    //Constructor de la casella
    public Casella(int i, int j, int costat, boolean fons, int borde) {
        this.costat = costat;
        x = j * this.costat + borde;
        y = i * this.costat + borde;
        this.fons = fons;
    }

    //Mètode que pinta una casella
    @Override
    public void paintComponent(Graphics g) {
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
    }

}
