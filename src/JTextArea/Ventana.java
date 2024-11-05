package JTextArea;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private static final int ANCHO=700;
    private static final int LARGO=600;
    private JTextArea textArea;
    private JLabel barraEstado;

    public Ventana(){
        setTitle("Editor de texto");
        setSize(ANCHO,LARGO);
        setVisible(true);
        setLocationRelativeTo(null);
        ImageIcon icono= new ImageIcon("C:\\Users\\alexr\\IdeaProjects\\EditorTexto\\src\\imagenes\\logo1.png");
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        barraEstado=new JLabel(" Esperando acción");
        add(barraEstado,BorderLayout.SOUTH);

        Menu menu = new Menu(textArea,barraEstado);
        setJMenuBar(menu);

        ToolBar toolBar = new ToolBar(textArea,barraEstado);
        add(toolBar,BorderLayout.NORTH);

        setVisible(true);
    }
}