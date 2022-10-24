/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author ccf20
 */
public class MenuBar extends JMenuBar{
    
    public MenuBar(){
        initComponents();
    }

    private void initComponents() {
        
        
        
        JMenu files = new JMenu("Archivos");
        
        JMenuItem save = new JMenuItem("Guardar tablero");
        files.add(save);
        JMenuItem load = new JMenuItem("Cargar tablero");
        files.add(load);
        
        this.add(files);
        
        JMenu clean = new JMenu("Tablero");
        
        JMenuItem cleanItem = new JMenuItem("Limpiar tablero");
        clean.add(cleanItem);
        
        this.add(clean);
        
    }
    
}
