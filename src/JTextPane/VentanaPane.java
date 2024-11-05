package JTextPane;

import javax.swing.*;
import java.awt.*;

public class VentanaPane extends JFrame {
    private static final int ANCHO=700;
    private static final int LARGO=600;
    private JTextPane textPane;
    private JLabel barraEstado;

    public VentanaPane(){
        setTitle("Editor de texto");
        setSize(ANCHO,LARGO);
        setVisible(true);
        setLocationRelativeTo(null);
        ImageIcon icono= new ImageIcon("src\\imagenes\\logo1.png");
        setIconImage(icono.getImage());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane=new JTextPane();
        add(new JScrollPane(textPane), BorderLayout.CENTER);

        barraEstado=new JLabel(" Esperando acción");
        add(barraEstado,BorderLayout.SOUTH);

        MenuPane menu = new MenuPane(textPane,barraEstado);
        setJMenuBar(menu);

        ToolBarPane toolBar = new ToolBarPane(textPane,barraEstado);
        add(toolBar,BorderLayout.NORTH);

        setVisible(true);

    }
}
