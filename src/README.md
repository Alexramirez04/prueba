# Editor de textos
He realizado dos tipos de ditores de texto, uno con JTextArea y otro con JTextpane

## JtextArea

Este proyecto es un editor de texto gráfico básico desarrollado en Java, utilizando la biblioteca Swing para la interfaz de usuario. El editor permite realizar operaciones estándar de edición de texto con JTextArea como abrir, guardar, copiar, cortar, pegar, y soporta personalización de fuente y tamaño de texto. Además, cuenta con una barra de herramientas y una barra de estado que ofrecen una experiencia de usuario amigable e intuitiva.

### Características

- **Abrir y Guardar archivos**: permite abrir y guardar documentos de texto plano (.txt).
- **Deshacer y Rehacer**: integra un manejador de acciones que permite deshacer y rehacer cambios en el texto.
- **Copiar, Cortar y Pegar**: accesos rápidos a funciones de edición en la barra de herramientas y en el menú.
- **Personalización de Fuente y Tamaño**: opciones para cambiar el tipo y tamaño de fuente del texto.
- **Barra de Estado Interactiva**: muestra mensajes informativos sobre la acción actual para mejorar la experiencia del usuario.
- **Interfaz Gráfica Intuitiva**: menús desplegables y botones con iconos hacen que el editor sea fácil de usar.

### Estructura del Proyecto

- **`Main.java`**: Clase principal que ejecuta la aplicación. Crea una instancia de la ventana `Ventana` y la muestra.
- **`Ventana.java`**: Configura la interfaz principal de la aplicación. Aquí se integra el área de texto, el menú, la barra de herramientas y la barra de estado.
- **`Menu.java`**: Define el menú de la aplicación, con opciones para Archivo, Edición y Configuración de Fuente y Tamaño. También maneja acciones de abrir, guardar, y salir, y proporciona mensajes en la barra de estado.
- **`ToolBar.java`**: Define una barra de herramientas con iconos que permiten el acceso rápido a funcionalidades como copiar, cortar, pegar, abrir, guardar, y salir.

### Menú Archivo

Contiene opciones relacionadas con el manejo de archivos de texto:

- **Abrir Archivo**: Abre un cuadro de diálogo para seleccionar y cargar un archivo de texto (.txt) en el área de edición.
- **Guardar Archivo**: Abre un cuadro de diálogo para guardar el contenido del área de texto en un archivo de texto.
- **Salir**: Cierra la aplicación.

### Menú Edición

Incluye opciones de edición básicas:

- **Deshacer**: Revierta el último cambio realizado en el texto.
- **Rehacer**: Restaura el cambio si se ha deshecho.

  Los botones de deshacer y rehacer están habilitados siempre que haya acciones pendientes en el historial de cambios.

### Menú Opciones

Permite personalizar la apariencia del texto en el área de edición:

- **Fuente**: Selecciona el tipo de fuente entre "Arial", "Courier New", o "Predeterminada".
- **Tamaño de Fuente**: Cambia el tamaño de la fuente del texto con opciones de 12 a 60 puntos.

### Barra de Herramientas

La barra de herramientas proporciona accesos rápidos a las funciones principales mediante botones con iconos:

- **Copiar**: Copia el texto seleccionado al portapapeles.
- **Cortar**: Corta el texto seleccionado y lo guarda en el portapapeles.
- **Pegar**: Pega el contenido del portapapeles en el área de texto.
- **Abrir Archivo**: Abre un archivo de texto.
- **Guardar Archivo**: Guarda el contenido en un archivo de texto.
- **Salir**: Cierra la aplicación.

### Barra de Estado

Muestra mensajes que describen las acciones actuales, como "Copiar", "Guardar Archivo", "Salir", etc. Cuando el cursor no está sobre ninguna acción, muestra el mensaje predeterminado: "No se encuentra ninguna acción".

## JTextPane

### Características Principales

- **Editor de Texto**: Un componente de texto enriquecido para permitir la entrada y edición de texto con soporte para múltiples formatos.
- **Interfaz Gráfica**: Diseñada utilizando **Swing**, que proporciona un entorno amigable para el usuario.
- **Barra de Menú**:
    - **Archivo**: Abrir y guardar archivos de texto.
    - **Editar**: Deshacer y rehacer acciones.
    - **Opciones**: Cambiar el tipo de fuente del texto.
- **Barra de Herramientas**: Contiene botones para acciones comunes como copiar, cortar, y pegar.
- **Barra de Estado**: Muestra mensajes sobre la acción actual del usuario y el estado de la aplicación.
- **Gestión de Archivos**: Soporta archivos con extensión `.txt`.
---

## Componentes

### Menú de estilo añadido

- **Negrita**: Establece el texto seleccionado en negrita.
- **Cursiva**: Establece el texto seleccionado en cursiva.
- **Normal**: Restablece el estilo del texto a normal (sin formato).

Los elementos del menú se añaden a una instancia de `JMenu` llamada `estiloMenu`. Este menú se puede agregar a un `JMenuBar` en la ventana principal de la aplicación.

### **Action Listeners**

Cada elemento del menú tiene un `ActionListener` que define la acción a realizar cuando se selecciona el elemento. Las acciones relevantes son las siguientes:

- **Negrita**:
    - Cuando el usuario selecciona "Negrita", la fuente del texto en el `textPane` cambia a negrita.
    - Fragmento de Código:
      ```java
      negrita.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              textPane.setFont(textPane.getFont().deriveFont(Font.BOLD, textPane.getFont().getSize()));
          }
      });
      ```

- **Cursiva**:
    - Cuando el usuario selecciona "Cursiva", la fuente del texto en el `textPane` cambia a cursiva.
    - Fragmento de Código:
      ```java
      cursiva.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              textPane.setFont(textPane.getFont().deriveFont(Font.ITALIC, textPane.getFont().getSize()));
          }
      });
      ```

- **Normal**:
    - Cuando el usuario selecciona "Normal", la fuente del texto en el `textPane` cambia a normal (sin formato).
    - Fragmento de Código:
      ```java
      normal.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              textPane.setFont(textPane.getFont().deriveFont(Font.PLAIN, textPane.getFont().getSize()));
          }
      });
      ```

## Ejemplo de Uso

Para utilizar esta funcionalidad, integra el siguiente fragmento de código en una aplicación Java Swing que incluya un `JTextPane` y una barra de menú:

```java
// Crear el text pane
JTextPane textPane = new JTextPane();
JMenuBar menuBar = new JMenuBar();
JMenu estiloMenu = new JMenu("Estilo");

// Crear los elementos del menú
JMenuItem negrita = new JMenuItem("Negrita");
JMenuItem cursiva = new JMenuItem("Cursiva");
JMenuItem normal = new JMenuItem("Normal");

// Añadir elementos del menú al menú estilo
estiloMenu.add(negrita);
estiloMenu.add(cursiva);
estiloMenu.add(normal);

// Añadir action listeners a los elementos del menú
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

// Añadir el menú estilo a la barra de menú
menuBar.add(estiloMenu);

// Configurar la ventana
JFrame frame = new JFrame("Estilizador de Texto");
frame.setJMenuBar(menuBar);
frame.add(new JScrollPane(textPane));
frame.setSize(400, 300);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
```