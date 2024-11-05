package JTextPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MenuPane extends JMenuBar {

    private JTextPane textPane;
    private File archivoActual = null;
    private UndoManager undoManager;
    private JLabel barraEstado;

    public MenuPane(JTextPane textPane, JLabel barraEstado) {
        this.textPane = textPane;
        this.barraEstado=barraEstado;
        this.undoManager=new UndoManager();

        textPane.getDocument().addUndoableEditListener(new javax.swing.event.UndoableEditListener() {
            public void undoableEditHappened(javax.swing.event.UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });


        JMenu archivoMenu = new JMenu("Archivo");
        add(archivoMenu);

        JMenu edicionMenu = new JMenu("Edición");
        add(edicionMenu);

        JMenu opcionesMenu = new JMenu("Opciones");
        add(opcionesMenu);

        JMenu estiloMenu=new JMenu("Estilo");
        add(estiloMenu);

        JMenu fuenteMenu = new JMenu("Fuente");
        opcionesMenu.add(fuenteMenu);

        JCheckBoxMenuItem arial = new JCheckBoxMenuItem("Arial");
        JCheckBoxMenuItem courier = new JCheckBoxMenuItem("Courier New");
        JCheckBoxMenuItem predeterminada = new JCheckBoxMenuItem("Predeterminada");

        ButtonGroup fuenteGroup = new ButtonGroup();
        fuenteGroup.add(arial);
        fuenteGroup.add(courier);
        fuenteGroup.add(predeterminada);

        fuenteMenu.add(arial);
        fuenteMenu.add(courier);
        fuenteMenu.add(predeterminada);

        arial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(new Font("Arial", Font.PLAIN, 12));
            }
        });

        courier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(new Font("Courier New", Font.PLAIN, 12));
            }
        });

        predeterminada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(new Font("Serif", Font.PLAIN, 12));
            }
        });

        ImageIcon deshacerIcono = new ImageIcon(new ImageIcon("src/imagenes/deshacer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon rehacerIcono = new ImageIcon(new ImageIcon("src/imagenes/rehacer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JMenuItem deshacer = new JMenuItem("Deshacer",deshacerIcono);
        JMenuItem rehacer = new JMenuItem("Rehacer",rehacerIcono);

        //funcionalidad de deshacer
        deshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });

        // funcionalidad de rehacer
        rehacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });

        edicionMenu.add(deshacer);
        edicionMenu.add(rehacer);

        ImageIcon abrirIcono = new ImageIcon(new ImageIcon("src/imagenes/abrir.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon guardarComoIcono = new ImageIcon(new ImageIcon("src/imagenes/guardar_como.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon salirIcono = new ImageIcon(new ImageIcon("src/imagenes/salir.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JMenuItem abrir = new JMenuItem("Abrir Archivo",abrirIcono);
        JMenuItem guardar = new JMenuItem("Guardar Archivo",guardarComoIcono);
        JMenuItem salir = new JMenuItem("Salir",salirIcono);

        archivoMenu.add(abrir);
        archivoMenu.add(guardar);
        archivoMenu.add(salir);

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

        JMenu tamanoMenu = new JMenu("Tamaño");
        opcionesMenu.add(tamanoMenu);
        agregarTamanio(tamanoMenu);

        JMenuItem negrita= new JMenuItem("Negrita");
        JMenuItem cursiva= new JMenuItem("Cursiva");
        JMenuItem normal= new JMenuItem("Normal");

        estiloMenu.add(negrita);
        estiloMenu.add(cursiva);
        estiloMenu.add(normal);

        negrita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(textPane.getFont().deriveFont(Font.BOLD, textPane.getFont().getSize()));
            }
        });

        cursiva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(textPane.getFont().deriveFont(Font.ITALIC, textPane.getFont().getSize()));
            }
        });

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane.setFont(textPane.getFont().deriveFont(Font.PLAIN, textPane.getFont().getSize()));
            }
        });

        ////////bara de estado////////////////////////////////////////////////////////////////////
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

        archivoMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Archivo");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        edicionMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Edición");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        opcionesMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Opciones");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        deshacer.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Deshacer");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        rehacer.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Rehacer");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        fuenteMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Fuente");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        arial.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Fuente Arial");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        courier.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Fuente Courier New");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        predeterminada.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Fuente Predeterminada");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        tamanoMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Tamaño");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        estiloMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Menú Estilo");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        negrita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Estilo de texto: Negrita");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        cursiva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Estilo de texto: Cursiva");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });

        normal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                barraEstado.setText(" Estilo de texto: Normal");
            }
            public void mouseExited(MouseEvent e) {
                barraEstado.setText(" No se encuentra ninguna acción");
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////
    }

    private void agregarTamanio(JMenu tamanoMenu) {
        ButtonGroup tamanioGroup = new ButtonGroup();

        for (int i = 12; i <= 60; i += 4) {
            JRadioButtonMenuItem tamanioItem = new JRadioButtonMenuItem(String.valueOf(i));
            tamanoMenu.add(tamanioItem);
            tamanioGroup.add(tamanioItem);

            tamanioItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int tamano = Integer.parseInt(tamanioItem.getText());
                    textPane.setFont(textPane.getFont().deriveFont((float) tamano));
                }
            });

            // Selecciona el tamaño predeterminado en 12 puntos
            if (i == 12) {
                tamanioItem.setSelected(true);
                textPane.setFont(textPane.getFont().deriveFont(12f));
            }
        }
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
