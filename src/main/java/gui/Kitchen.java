package gui;

import agent.Robot;
import environment.Environment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class Kitchen extends JPanel implements MouseListener, MouseMotionListener {


    // Atributs propis del tauler
    private Tile[][] tiles;
    private Color colorBorde = new Color(200, 200, 200);
    private int costat;
    private int costatCasella;
    private final int dimsBordeBase = 40;
    private int dimsBorde = dimsBordeBase;
    private int pixelsCostat = 800;
    private Dimension dimensions = new Dimension(pixelsCostat + (2 * dimsBorde) + 1,
            pixelsCostat + (2 * dimsBorde) + 1);
    private int buttonPressed = -1;
    private RobotDisplayer robotDisplayer;
    private BufferedImage starterImage = null;
    private Graphics2D gAux = null;
    private BufferedImage biAux = null;

    // Constructor del tauler
    public Kitchen(int n, RobotGui gui, Environment<Robot> env, RobotDisplayer robotDisplayer) {
        this.setLayout(null);
        // this.setBorder(BorderFactory.createLineBorder(colorBorde, 2));
        this.costat = n;
        this.costatCasella = pixelsCostat / costat;
        this.dimsBorde += (int) (((((float) pixelsCostat) / costat) - costatCasella) * costat / 2);
        this.tiles = new Tile[costat][costat];
        boolean fons = false;
        this.robotDisplayer = robotDisplayer;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // ****************************** TEST ********************************
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (!robotDisplayer.isActive())
                    return;

                ExecutorService executor = Executors.newSingleThreadExecutor();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        executor.execute(() -> {
                            robotDisplayer.manualMoveRobot(0, -1, Kitchen.this); // WEAST
                        });

                    }
                    case KeyEvent.VK_RIGHT -> {
                        executor.execute(() -> {
                            robotDisplayer.manualMoveRobot(0, 1, Kitchen.this); // EAST
                        });

                    }
                    case KeyEvent.VK_UP -> {
                        executor.execute(() -> {
                            robotDisplayer.manualMoveRobot(-1, 0, Kitchen.this); // NORTH
                        });

                    }
                    case KeyEvent.VK_DOWN -> {
                        executor.execute(() -> {
                            robotDisplayer.manualMoveRobot(1, 0, Kitchen.this); // SOUTH
                        });

                    }
                    case KeyEvent.VK_SPACE -> {
                        executor.execute(() -> {
                            robotDisplayer.manualMoveRobot(-1, 0, Kitchen.this); // NORTH

                            robotDisplayer.manualMoveRobot(-1, 0, Kitchen.this); // NORTH

                            robotDisplayer.manualMoveRobot(0, -1, Kitchen.this); // WEAST

                            robotDisplayer.manualMoveRobot(0, -1, Kitchen.this); // WEAST

                            robotDisplayer.manualMoveRobot(1, 0, Kitchen.this); // SOUTH

                            robotDisplayer.manualMoveRobot(1, 0, Kitchen.this); // SOUTH

                            robotDisplayer.manualMoveRobot(0, 1, Kitchen.this); // EAST

                            robotDisplayer.manualMoveRobot(0, 1, Kitchen.this); // EAST
                        });

                    }
                }

            }
        });

        // ****************************** TEST ********************************
        // Set transfer handler to manage the drop on the panel for the robot
        this.setTransferHandler(new TransferHandler("icon") {

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                Point point = support.getDropLocation().getDropPoint();
                int i = getIndex(point.y);
                int j = getIndex(point.x);

                if (!isValid(i) || !isValid(j) || tiles[i][j].isObstacle()) {
                    return false;
                }
                return true;
            }

            // Action to do when de robot is dropped here
            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                boolean accept = false;
                if (canImport(support)) {
                    try {
                        Transferable t = support.getTransferable();
                        accept = setRobotLocation(support.getDropLocation().getDropPoint());
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    Kitchen.this.requestFocus();
                }
                gui.setRobotActive(accept);
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

    public boolean setRobotLocation(Point p) {
        int x = p.x;
        int y = p.y;
        int i = getIndex(y);
        int j = getIndex(x);
        if (isValid(i) && isValid(j)) {
            this.robotDisplayer.setActive(true);
            this.robotDisplayer.setInitialTile(i, j, this.costatCasella, this.dimsBorde);
            this.repaint();
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

        if (starterImage == null) {
            starterImage = new BufferedImage(dimensions.width, dimensions.height, BufferedImage.TYPE_INT_ARGB);
            gAux = (Graphics2D) starterImage.getGraphics();
            gAux.setColor(colorBorde);
            gAux.drawRect(dimsBorde - 1, dimsBorde - 1, dimensions.width - (dimsBorde * 2) + 1,
                    dimensions.height - (dimsBorde * 2) + 1);
            gAux.drawRect(dimsBorde - 2, dimsBorde - 2, dimensions.width - (dimsBorde * 2) + 3,
                    dimensions.height - (dimsBorde * 2) + 3);

        }

        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                tiles[i][j].paintComponent(gAux);
            }
        }

        biAux = clone(starterImage);
        Graphics2D gbiAux = (Graphics2D) biAux.getGraphics();

        if (robotDisplayer != null && robotDisplayer.isActive()) {
            robotDisplayer.paintComponent(gbiAux);
        }

        gbiAux.dispose();
        g.drawImage(biAux, 0, 0, this);

    }

    // public void setEnv(Environment<Robot> env) {
    //
    // // Map creation
    // for (int i = 0; i < tiles.length; i++) {
    // for (int j = 0; j < tiles[0].length; j++) {
    // tiles[i][j].setIsObstacleReference(env.getIsObstacleReference(i, j));
    // }
    // }
    // }

    public void setObstacleImage(BufferedImage im) {
        Tile.setObstacleImage(im);
    }

    public void setRobotDisplayerSpeedFactor(double speedFactor) {
        this.robotDisplayer.setSpeedFactor(speedFactor);
    }

    // public void refresh() {
    // revalidate();
    // repaint();
    // }
    // @Override
    // public void repaint(){
    // if(this.getGraphics() != null){
    // this.update(this.getGraphics());
    //
    // }
    // }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.requestFocus();
        this.buttonPressed = e.getButton();
        updateObstacles(e);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void mouseReleased(MouseEvent e) {
        ;
        this.buttonPressed = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private int getIndex(int x) {
        float val = (float) (x - dimsBorde) / this.costatCasella;
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

    private void updateObstacles(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int i = getIndex(y);
        int j = getIndex(x);

        if (!isValid(i) || !isValid(j)) {
            return;
        }
        if (this.robotDisplayer.isActive() && this.robotDisplayer.isOnTile(i, j)) {
            return;
        }

        Tile tile = tiles[i][j];

        if (this.buttonPressed == MouseEvent.BUTTON1 && !tile.isObstacle()) {
            tile.setIsObstacle(true);
            tile.notifyChange();
        }

        if (this.buttonPressed == MouseEvent.BUTTON3 && tile.isObstacle()) {
            tile.setIsObstacle(false);
            tile.notifyChange();

        }
        this.repaint();
    }

    public void repaintRobotAndTiles() {
        Point center = this.robotDisplayer.getTileIndices();
        int[][] offset = {
                { 0, 0 },
                { 1, 0 },
                { 0, 1 },
                { -1, 0 },
                { 0, -1 }
        };

        for (int i = 0; i < offset.length; i++) {
            int x = center.x + offset[i][0];
            int y = center.y + offset[i][1];
            if (!isValid(x) || !isValid(y)) {
                continue;
            }
            this.tiles[x][y].notifyChange();
        }

        this.repaint();
    }

    private BufferedImage clone(BufferedImage image) {
        BufferedImage clon = new BufferedImage(image.getWidth(),
                image.getHeight(), image.getType());
        Graphics2D g2d = clon.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return clon;
    }

    public void paintAll() {
        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                tiles[i][j].notifyChange();

                if (tiles[i][j].isObstacle()) {
                    tiles[i][j].setIsObstacle(true);
                }

            }
        }

        this.repaint();
    }
    
    public void resetAll(){
        for (int i = 0; i < costat; i++) {
            for (int j = 0; j < costat; j++) {
                tiles[i][j].setIsObstacle(false);
                tiles[i][j].notifyChange();

            }
        }
        this.repaint();
    }
    
    public Boolean moveRobot(){
        this.robotDisplayer.move(this);
        return this.robotDisplayer.isActive();
    }
    
    
}
