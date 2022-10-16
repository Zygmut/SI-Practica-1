package gui;

import environment.Environment;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class Kitchen extends JPanel implements MouseListener, MouseMotionListener {

    private final DataFlavor SUPPORTED_DATA_FLAVOR = DataFlavor.imageFlavor;
    
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
    private int buttonPressed = -1;
    private RobotDisplayer robotDisplayer;
    private RobotGui gui;
    

    // Constructor del tauler
    public Kitchen(int n, RobotGui gui, Environment env, RobotDisplayer robotDisplayer) {
        this.setLayout(null);
        this.setBackground(colorMarc);
        this.setBorder(BorderFactory.createLineBorder(colorBorde, 2));
        this.costat = n;
        this.costatCasella = pixelsCostat / costat;
        this.dimsBorde += (int) (((((float) pixelsCostat) / costat) - costatCasella) * costat / 2);
        this.tiles = new Tile[costat][costat];
        boolean fons = false;
        this.robotDisplayer = robotDisplayer;
        this.add(this.robotDisplayer);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        
        //Set transfer handler to manage the drop on the panel for the robot
        this.setTransferHandler(new TransferHandler("icon"){
            
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return true; 
            }

            //Action to do when de robot is dropped here
            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(support.getDataFlavors()[0]);
                    Component component = support.getComponent();
                    accept = setRobotLocation(support.getDropLocation().getDropPoint());
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
                gui.setRobotActive(accept);
                
                robotDisplayer.temporalMoveRobot(-1,0);
                robotDisplayer.move(Kitchen.this);
                
                return accept;
            }
            
            
        });

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
    
    public boolean setRobotLocation(Point p){
        int x = p.x;
        int y = p.y;
        int i = getIndex(y);
        int j = getIndex(x);
        if(isValid(i) && isValid(j)){
            this.robotDisplayer.setActive(true);
            this.robotDisplayer.setInitialTile(i, j, this.costatCasella, this.dimsBorde);
            this.robotDisplayer.paintComponent(this.getGraphics());
            return true;
        }
        return false;
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
        
        if(robotDisplayer != null && robotDisplayer.isActive()){
            robotDisplayer.paintComponent(g);
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.buttonPressed = e.getButton();
        updateObstacles(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {;
        this.buttonPressed = -1;
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

    @Override
    public void mouseDragged(MouseEvent e) {
        updateObstacles(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
    private void updateObstacles(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int i = getIndex(y);
        int j = getIndex(x);
        
        if(!isValid(i) || !isValid(j)) return;
        if(this.robotDisplayer.isOnTile(i, j)) return;
        
        Tile tile = tiles[i][j];
        
        if(this.buttonPressed == MouseEvent.BUTTON1 && !tile.isObstacle()){
            tile.setIsObstacle(true);
            tile.paintComponent(this.getGraphics());  
            return;
        }
        
        if(this.buttonPressed == MouseEvent.BUTTON3 && tile.isObstacle()){
            tile.setIsObstacle(false);
            tile.paintComponent(this.getGraphics());
            return;
        }
    }
    



}
