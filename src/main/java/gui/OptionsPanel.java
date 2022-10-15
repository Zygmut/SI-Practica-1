package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import utils.ImageLoader;

public class OptionsPanel extends JPanel {

    private final int DIM_ICONO = 60;
    private final int ANCHO = 250;
    private final int ALTO = 20;

    private final String[] obstacleNames = new String[]{"box", "microwave", "oven", "table", "fruit"};
    private final String baseImagesPath = "src/main/java/images/";
    private final String extension = ".png";
    private final BufferedImage[] images;
    private final RobotGui gui;

    // private final Vista vista;
    private final Dimension dimensionInputs = new Dimension(145, 30);

    private ButtonGroup groupbtn;

    private ArrayList<JButton> botones = new ArrayList();
    private JFormattedTextField inputDimsTablero;
    private int dimsTableroPrevias;

    public OptionsPanel(int n, RobotGui gui) {

        this.gui = gui;
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        images = loadImages();
        
        this.initComponents();
        
        inputDimsTablero.setText(Integer.toString(n));
        dimsTableroPrevias = n;

    }

    private void initComponents() {
        groupbtn = new ButtonGroup();
        initInputDimTablero();
        //this.add(Box.createVerticalGlue());
        initInputs();
    }
    
    private BufferedImage[] loadImages(){
        BufferedImage[] images = new BufferedImage[obstacleNames.length];
        for(int i = 0; i < images.length; i++){
            images[i] = ImageLoader.loadImage(baseImagesPath + obstacleNames[i] + extension);
        }
        return images;
    }

    private void initInputDimTablero() {
        JRadioButton rbtn;
        int i = 0;
        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        panelBotones.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.black, 2),
                                "Resolución del entorno (NxN)"),
                        BorderFactory.createEmptyBorder(10, 5, 15, 5)));
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        panelBotones.add(crearInput("NxN", 2, -1));

        this.add(panelBotones);
    }

    private void initInputs() {

        JPanel entradas = new JPanel();

        entradas.setLayout(new BoxLayout(entradas, BoxLayout.Y_AXIS));

        entradas.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.black, 2),
                                "Obstáculos"),
                        BorderFactory.createEmptyBorder(10, 5, 15, 5)));

        int nPiezas = obstacleNames.length;
        for (int i = 0; i < nPiezas; i++) {
            String nombrePieza = obstacleNames[i];
            entradas.add(crearInput(nombrePieza, 2, i));
            if (i == nPiezas - 1) {
                entradas.add(Box.createVerticalGlue());
            } else {
                entradas.add(Box.createRigidArea(new Dimension(5, 20)));
            }
        }
        this.add(entradas);
    }

    private JPanel crearInput(String etiq, int lim, int pos) {
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.X_AXIS));

        if (pos >= 0) {
            panelEntrada.add(Box.createHorizontalGlue());
        }

        panelEntrada.add(CrearEtiq(etiq));
        
        panelEntrada.add(Box.createRigidArea(new Dimension(10, 5)));

        if (pos >= 0) {

            JRadioButton jr = new JRadioButton();
            jr.setIcon(new ImageIcon(new ImageIcon(baseImagesPath + "radioicon" + extension).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            jr.setRolloverIcon(new ImageIcon(new ImageIcon(baseImagesPath + "radiorollovericon" + extension).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            jr.setSelectedIcon(new ImageIcon(new ImageIcon(baseImagesPath + "radioselectedicon" + extension).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            jr.setRolloverSelectedIcon(new ImageIcon(new ImageIcon(baseImagesPath + "radiorolloverselectedicon" + extension).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            jr.setActionCommand(Integer.toString(pos));
            jr.addActionListener(getChangeObstacleActionListener());
            this.groupbtn.add(jr);
            panelEntrada.add(jr);
            
            if(pos == 0){
                jr.doClick();
            }
        }


        panelEntrada.add(Box.createHorizontalGlue());

        if (pos < 0) {
            JFormattedTextField entradaTexto = new JFormattedTextField();
            entradaTexto.setName(etiq);
            inputDimsTablero = entradaTexto;
            
            entradaTexto.addActionListener((ActionEvent) -> {
                this.changeKitchenSize();
            });

            TextPrompt placeholder = new TextPrompt(etiq, entradaTexto);

            entradaTexto.setPreferredSize(dimensionInputs);
            entradaTexto.setMaximumSize(dimensionInputs);
            entradaTexto.setMargin(new Insets(0, 5, 0, 0));

            entradaTexto.addKeyListener(new InputListener(entradaTexto, lim));
            entradaTexto.setActionCommand(etiq.toLowerCase());

            placeholder.changeAlpha(0.75f);
            placeholder.changeStyle(Font.ITALIC);
            panelEntrada.add(entradaTexto);
            
        }

        return panelEntrada;
    }

    private JLabel CrearEtiq(String str) {
        JLabel lab = new JLabel();
        ImageIcon baseImg = new ImageIcon(baseImagesPath + str.toLowerCase() + extension);
        lab.setIcon(new ImageIcon(baseImg.getImage().getScaledInstance(DIM_ICONO, DIM_ICONO, Image.SCALE_SMOOTH)));
        return lab;
    }

    // Cambiamos el tamaño que tendrá el panel cuando se aplique el pack
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ANCHO, ALTO);
    }

    class InputListener implements KeyListener {

        private JFormattedTextField input;
        private int lim;

        public InputListener(JFormattedTextField in, int lim) {
            this.input = in;
            this.lim = lim;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) || input.getText().length() == lim) {
                    e.consume();
                }
                
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    private void changeKitchenSize() {
        String text = inputDimsTablero.getText();
        if (!text.equals("")) {
            int n = Integer.parseInt(text);
            if (n > 0) {
                this.dimsTableroPrevias = n;
                this.gui.addNewKitchen(n);
            }
        }
    }

    public void bloquear() {
        this.bloqueoInterno(false);
    }

    public void desbloquear() {
        this.bloqueoInterno(true);
    }

    private void bloqueoInterno(boolean estado) {
        inputDimsTablero.setEnabled(estado);
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setEnabled(estado);
            
        }
    }

    private ActionListener getChangeObstacleActionListener() {
        return (ActionEvent e) -> {
            int index = Integer.parseInt(e.getActionCommand());
            Tile.setObstacleImage(images[index]);
        };
    }

}
