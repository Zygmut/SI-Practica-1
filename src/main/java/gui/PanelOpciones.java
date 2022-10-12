package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PanelOpciones extends JPanel {

    private final int DIM_ICONO = 30;
    private final int ANCHO = 250;
    private final int ALTO = 20;

    // private final Vista vista;

    private final Dimension dimensionInputs = new Dimension(145, 30);

    private ButtonGroup groupbtn;

    private JFormattedTextField[] inputsPiezas;
    private ArrayList<JButton> botones = new ArrayList();
    private JFormattedTextField inputDimsTablero;
    private int dimsTableroPrevias;
    private HashMap<String, ArrayList<Point>> posicionesIniciales = new HashMap();

    public PanelOpciones(int n) {

        // this.vista = v;
        // this.setBorder(BorderFactory.createCompoundBorder(
        // BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black),
        // BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //// this.initComponents();
        // inputDimsTablero.setText(Integer.toString(n));
        // dimsTableroPrevias = n;
        //
        // inputDimsTablero.getDocument().addDocumentListener(new DocumentListener() {
        // @Override
        // public void insertUpdate(DocumentEvent e) {
        // cambiarDimsTablero();
        // }
        //
        // @Override
        // public void removeUpdate(DocumentEvent e) {
        // cambiarDimsTablero();
        // }
        //
        // @Override
        // public void changedUpdate(DocumentEvent e) {
        // //Do nothing
        // }
        // });

    }

    // private void initComponents() {
    // groupbtn = new ButtonGroup();
    // initInputDimTablero();
    // //this.add(Box.createVerticalGlue());
    // initInputs();
    // }

    // private void initInputDimTablero() {
    // JRadioButton rbtn;
    // int i = 0;
    // JPanel panelBotones = new JPanel();
    //
    // panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    //
    // panelBotones.setBorder(
    // BorderFactory.createCompoundBorder(
    // BorderFactory.createTitledBorder(
    // BorderFactory.createLineBorder(Color.black, 2),
    // "Dimensiones del tablero (NxN)"),
    // BorderFactory.createEmptyBorder(10, 5, 15, 5)));
    // panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    //
    // panelBotones.add(crearInput("NxN", 2, -1));
    //
    // this.add(panelBotones);
    // }

    // private void initInputs() {
    //
    // JPanel entradas = new JPanel();
    //
    // entradas.setLayout(new BoxLayout(entradas, BoxLayout.Y_AXIS));
    //
    // ArrayList<String> nombreClasesPieza = Datos.getClasesPiezas();
    //
    // entradas.setBorder(
    // BorderFactory.createCompoundBorder(
    // BorderFactory.createTitledBorder(
    // BorderFactory.createLineBorder(Color.black, 2),
    // "Piezas"),
    // BorderFactory.createEmptyBorder(10, 5, 15, 5)));
    //
    // int nPiezas = nombreClasesPieza.size();
    // inputsPiezas = new JFormattedTextField[nPiezas];
    // for (int i = 0; i < nPiezas; i++) {
    // String nombrePieza = nombreClasesPieza.get(i);
    // posicionesIniciales.put(nombrePieza, new ArrayList());
    // entradas.add(crearInput(nombrePieza, 2, i));
    // if (i == nPiezas - 1) {
    // entradas.add(Box.createVerticalGlue());
    // } else {
    // entradas.add(Box.createRigidArea(new Dimension(5, 20)));
    // }
    // }
    // this.add(entradas);
    // }

    // public JSONObject obtenerValor() {
    // JSONObject miJSON = new JSONObject();
    // miJSON.put("n", this.dimsTableroPrevias);
    // for (JFormattedTextField input : inputsPiezas) {
    // miJSON.put(input.getName(), posicionesIniciales.get(input.getName()));
    // }
    // return miJSON;
    // }

    // private JPanel crearInput(String etiq, int lim, int pos) {
    // JPanel panelEntrada = new JPanel();
    // panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.X_AXIS));
    // JFormattedTextField entradaTexto = new JFormattedTextField();
    // entradaTexto.setName(etiq);
    // entradaTexto.setEditable(pos < 0);
    // if (pos >= 0) {
    // inputsPiezas[pos] = (entradaTexto);
    // } else {
    // inputDimsTablero = entradaTexto;
    // }
    // TextPrompt placeholder = new TextPrompt(etiq, entradaTexto);
    //
    // panelEntrada.add(CrearEtiq(etiq));
    //
    // panelEntrada.add(Box.createHorizontalGlue());
    //
    // entradaTexto.setPreferredSize(dimensionInputs);
    // entradaTexto.setMaximumSize(dimensionInputs);
    // entradaTexto.setMargin(new Insets(0, 5, 0, 0));
    //
    // entradaTexto.addKeyListener(new InputListener(entradaTexto, lim));
    // entradaTexto.setActionCommand(etiq.toLowerCase());
    //
    // placeholder.changeAlpha(0.75f);
    // placeholder.changeStyle(Font.ITALIC);
    // panelEntrada.add(entradaTexto);
    //
    // if (pos >= 0) {
    // ActionListener accionPonerQuitarPieza = ponerQuitarPieza();
    // panelEntrada.add(Box.createRigidArea(new Dimension(20, 1)));
    // JButton botonAdd = new JButton("+");
    // botonAdd.setBackground(Color.white);
    // botonAdd.addActionListener(accionPonerQuitarPieza);
    // panelEntrada.add(botonAdd);
    // botones.add(botonAdd);
    //
    // panelEntrada.add(Box.createRigidArea(new Dimension(5, 1)));
    // JButton botonRemove = new JButton("-");
    // botonRemove.setBackground(new Color(227, 170, 170));
    // botonRemove.addActionListener(accionPonerQuitarPieza);
    // botones.add(botonRemove);
    // panelEntrada.add(botonRemove);
    // }
    // return panelEntrada;
    // }

    private JLabel CrearEtiq(String str) {
        JLabel lab = new JLabel();
        ImageIcon baseImg = new ImageIcon("src/caminoeuleriano/imagenes/" + str.toLowerCase() + ".png");
        lab.setIcon(new ImageIcon(baseImg.getImage().getScaledInstance(DIM_ICONO, DIM_ICONO, Image.SCALE_SMOOTH)));
        // lab.setBackground(Color.lightGray);
        // lab.setFont(new Font("Calibri", Font.BOLD, 14));
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

    private void cambiarDimsTablero() {
        String text = inputDimsTablero.getText();
        if (!text.equals("")) {
            int n = Integer.parseInt(text);
            if (n > 0) {
                this.dimsTableroPrevias = n;
                // Cambiar tamaño tablero
                // this.vista.setDimsTablero(n);
                this.reset();
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

    public void reset() {
        for (JFormattedTextField input : this.inputsPiezas) {
            input.setText("");
        }
        for (ArrayList<Point> posiciones : this.posicionesIniciales.values()) {
            posiciones.clear();
        }
        this.desbloquear();
    }

    // private ActionListener ponerQuitarPieza() {
    // return (ActionEvent e) -> {
    // JButton boton = (JButton) e.getSource();
    // String text = boton.getText();
    // JFormattedTextField input = obtenerInput(boton);
    // String inputText = input.getText();
    // String inputName = input.getName();
    // int npiezas;
    // if (inputText.equals("")) {
    // npiezas = 0;
    // } else {
    // npiezas = Integer.parseInt(inputText);
    // }
    // switch (text) {
    // case "+" -> {
    // // Aqui hacer que seleccione la posicion donde lo quiere
    // // añadir
    // // Elegir posicion
    // DialogTablero dialogSeleccion = new DialogTablero(vista,
    // this.dimsTableroPrevias);
    // dialogSeleccion.setVisible(true);
    // Point seleccion = dialogSeleccion.obtenerPunto();
    // if (seleccion != null) {
    // try {
    // Class clasePieza = Class.forName("caminoeuleriano.Modelo.piezas." +
    // inputName);
    // Pieza instancia;
    // // Si tiene constructor con parámetro lo llamamos, si no
    // // llamamos al que no los tiene
    // try {
    // instancia = (Pieza)
    // clasePieza.getConstructor(int.class).newInstance(this.dimsTableroPrevias);
    // } catch (NoSuchMethodException ex) {
    // instancia = (Pieza) clasePieza.getConstructor().newInstance();
    // }
    // instancia.setX(seleccion.x);
    // instancia.setY(seleccion.y);
    // posicionesIniciales.get(inputName).add(seleccion);
    // vista.addPiezaFijaTablero(instancia);
    //
    // input.setText(++npiezas + "");
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // }
    //
    // }
    //
    // }
    // case "-" -> {
    // if (npiezas > 0) {
    // //Cambiar valor inputText
    // if (npiezas > 1) {
    // input.setText(--npiezas + "");
    // } else {
    // input.setText("");
    // }
    // //Eliminar la ultima pieza colocada de ese tipo en el tablero
    // vista.eliminarPiezaTipo(inputName);
    // ArrayList<Point> pos = posicionesIniciales.get(inputName);
    // pos.remove(pos.size() - 1);
    // }
    // }
    // }
    // };
    // }

    // Obtiene el primer input "hermano" del boton
    private JFormattedTextField obtenerInput(JButton boton) {
        Component[] componentes = boton.getParent().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JFormattedTextField input) {
                return input;
            }
        }
        return null;
    }

}
