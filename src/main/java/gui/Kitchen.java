/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Kitchen extends JPanel implements MouseListener {


    // Atributs propis del tauler
    private Casella[][] caselles;
    private Color colorMarc = new Color(80, 80, 80);
    private Color colorBorde = new Color(200, 200, 200);
    private int costat;
    private int costatCasella;
    private int dimsBorde = 40;
    private int pixelsCostat = 600;
    private Dimension dimensions = new Dimension(pixelsCostat + (2 * dimsBorde) + 1,
            pixelsCostat + (2 * dimsBorde) + 1);

    // Constructor del tauler
    public Kitchen(int n) {
        this.setBackground(colorMarc);
        this.setBorder(BorderFactory.createLineBorder(colorBorde, 2));
        this.costat = n;
        this.costatCasella = pixelsCostat / costat;
        this.dimsBorde += (int) (((((float) pixelsCostat) / costat) - costatCasella) * costat / 2);
        this.caselles = new Casella[costat][costat];
        boolean fons = false;

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                caselles[i][j] = new Casella(i, j, costatCasella, fons, dimsBorde);
                fons = !fons;
            }
            if (this.costat % 2 == 0) {
                fons = !fons;
            }
        }
    }

    // Constructor del tauler
    public Kitchen(int n, int pixels, int borde) {
//        this.dialogTablero = dT;
        this.setBackground(colorMarc);
        this.setBorder(BorderFactory.createLineBorder(colorBorde, 2));
        this.pixelsCostat = pixels;
        this.dimsBorde = borde;
        this.costat = n;
        this.dimensions = new Dimension(pixelsCostat + (2 * dimsBorde) + 1,
                pixelsCostat + (2 * dimsBorde) + 1);
        this.costatCasella = pixelsCostat / costat;
        this.dimsBorde += (int) (((((float) pixelsCostat) / costat) - costatCasella) * costat / 2);
        this.caselles = new Casella[costat][costat];
        boolean fons = false;
        this.addMouseListener(this);

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                caselles[i][j] = new Casella(i, j, costatCasella, fons, dimsBorde);
                fons = !fons;
            }
            if (this.costat % 2 == 0) {
                fons = !fons;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return dimensions;
    }

    // Pinta el tauler
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colorBorde);
        g.drawRect(dimsBorde - 1, dimsBorde - 1, dimensions.width - (dimsBorde * 2) + 1, dimensions.height - (dimsBorde * 2) + 1);
        g.drawRect(dimsBorde - 2, dimsBorde - 2, dimensions.width - (dimsBorde * 2) + 3, dimensions.height - (dimsBorde * 2) + 3);

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                caselles[i][j].paintComponent(g);
            }
        }
        
    }

    public void reset() {
        revalidate();
        repaint();
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int i = getI(y);
        int j = getJ(x);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private int getI(int y) {
        return (y - dimsBorde) / this.costatCasella;
    }

    private int getJ(int x) {
        return (x - dimsBorde) / this.costatCasella;
    }

    private boolean comprobarValidez(int n) {
        return (n >= 0 && n < (this.pixelsCostat / this.costatCasella));
    }
//
//    public static ArrayList<Pieza> getPiezasFijas() {
//        return piezasADibujar;
//    }

}
