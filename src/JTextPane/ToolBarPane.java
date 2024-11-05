package JTextPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class ToolBarPane extends JToolBar {
    private JTextPane textPane = new JTextPane();
    private File archivoActual = null;
    private  JLabel barraEstado;


    public ToolBarPane(JTextPane textPane, JLabel barraEstado){
        this.textPane = textPane;
        this.barraEstado=barraEstado;
        ImageIcon copiarIcono = new ImageIcon(new ImageIcon("src/imagenes/copiar.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon cortarIcono = new ImageIcon(new ImageIcon("src/imagenes/cortar.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon pegarIcono = new ImageIcon(new ImageIcon("src/imagenes/pegar.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton copiar = new JButton(copiarIcono);
        copiar.setPreferredSize(new Dimension(30, 30));
        copiar.setToolTipText("Copiar");

        JButton cortar = new JButton(cortarIcono);
        cortar.setPreferredSize(new Dimension(30, 30));
        cortar.setToolTipText("Cortar");

        JButton pegar = new JButton(pegarIcono);
        pegar.setPreferredSize(new Dimension(30, 30));
        pegar.setToolTipText("Pegar");

        add(copiar);
        add(cortar);
        add(pegar);

        copiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.copy();
            }
        });

        cortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.cut();
            }
        });

        pegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.paste();
            }
        });

        ImageIcon abrirIcono = new ImageIcon(new ImageIcon("src/imagenes/abrir.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon guardarComoIcono = new ImageIcon(new ImageIcon("src/imagenes/guardar_como.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon salirIcono = new ImageIcon(new ImageIcon("src/imagenes/salir.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton abrir = new JButton(abrirIcono);
        JButton guardar = new JButton(guardarComoIcono);
        JButton salir = new JButton(salirIcono);

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionArchivo("abrir");
            }
        });

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionArchivo("guardar");
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionArchivo("salir");
            }
        });

        add(abrir);
        add(guardar);
        add(salir);

        ////////////////////BARRA DE ESTADO LISTENER////////////////////////////////////////////
        copiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Copiar");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        cortar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Cortar");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        pegar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Pegar");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        abrir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Abrir Archivo");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        guardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Guardar Archivo");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        salir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Salir");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////
    }

    private void funcionArchivo(String accion) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));

        try {
            if ("abrir".equals(accion) && fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                archivoActual = fileChooser.getSelectedFile();
                textPane.read(new BufferedReader(new FileReader(archivoActual)), null);
            }
            else if ("salir".equals(accion)) {
                System.exit(0);
            }
            else if ("guardarComo".equals(accion) || archivoActual == null) {
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    archivoActual = fileChooser.getSelectedFile();
                    textPane.write(new BufferedWriter(new FileWriter(archivoActual)));
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al " + accion + " el archivo.");
        }
    }
}
