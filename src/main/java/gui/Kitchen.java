package gui;

import environment.Environment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Kitchen extends JPanel implements MouseListener {

    // Atributs propis del tauler
    private Tile[][] tiles;
    private Color colorMarc = new Color(80, 80, 80);
    private Color colorBorde = new Color(200, 200, 200);
    private int costat;
    private int costatCasella;
    private int dimsBorde = 40;
    private int pixelsCostat = 800;
    private Dimension dimensions = new Dimension(pixelsCostat + (2 * dimsBorde) + 1,
            pixelsCostat + (2 * dimsBorde) + 1);

    // Constructor del tauler
    public Kitchen(int n, Environment env) {
        this.setBackground(colorMarc);
        this.setBorder(BorderFactory.createLineBorder(colorBorde, 2));
        this.costat = n;
        this.costatCasella = pixelsCostat / costat;
        this.dimsBorde += (int) (((((float) pixelsCostat) / costat) - costatCasella) * costat / 2);
        this.tiles = new Tile[costat][costat];
        boolean fons = false;
        this.addMouseListener(this);

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                tiles[i][j] = new Tile(i, j, costatCasella, fons, dimsBorde);
                tiles[i][j].setIsObstacleReference(env.getIsObstacleReference(i, j));
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
        g.drawRect(dimsBorde - 1, dimsBorde - 1, dimensions.width - (dimsBorde * 2) + 1,
                dimensions.height - (dimsBorde * 2) + 1);
        g.drawRect(dimsBorde - 2, dimsBorde - 2, dimensions.width - (dimsBorde * 2) + 3,
                dimensions.height - (dimsBorde * 2) + 3);

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                tiles[i][j].paintComponent(g);
            }
        }

    }

    
    public void setMap(Environment env) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].setIsObstacleReference(env.getIsObstacleReference(i, j));
            }
            
        }
    }
    
    public void setObstacleImage(BufferedImage im){
        Tile.setObstacleImage(im);
    }
    
    public void refresh() {
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
        int i = getIndex(y);
        int j = getIndex(x);

        if(isValid(i) && isValid(j)){
            tiles[i][j].toggleIsObstacle();
            tiles[i][j].paintComponent(this.getGraphics());
        }
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

    private int getIndex(int x) {
        float val = (float)(x - dimsBorde) / this.costatCasella;
        return val < 0 ? -1 : (int) val;
    }

    private boolean isValid(int n) {
        return (n >= 0 && n < (this.pixelsCostat / this.costatCasella));
    }



}
