package presentacion;
import dominio.SenkuException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Write a description of class SenkuGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SenkuGUI extends JFrame {
    //Carta menu principal
    private JPanel mainPanel;
    private ImageIcon IconSenku;

    //Ciclo0
    private JPanel panelMenu;
    private static final int ancho = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private static final int alto = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

    //Ciclo1
    private JMenuBar menuBar;
    private JMenu configuraciones;
    private JMenu archivo;
    private JMenuItem color;
    private JMenuItem tamano;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JButton playGame;

    private JButton LoadGame;
    private JButton SalirGame;

    //Ciclo2
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JFileChooser fileChooser;

    //Ciclo3
    private Tablero tablero;

    //Ciclo4
    private JPanel panelConfiguracion;
    private JButton colorFondo;
    private JButton colorPaneles;
    private JButton colorBotones;
    private JButton salirConfiguracion;

    //ciclo8
    private int size = 7;

    /**
     * Constructor de la interfaz grafica
     */
    public SenkuGUI() {
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Metodo principal del GUI
     */
    public static void main(String[] args) {
        SenkuGUI senku = new SenkuGUI();
        senku.setVisible(true);
    }

    /**
     * Prepara los elementos uno por uno para la correcta visualizacion de la interfaz
     */
    private void prepareElementos() {
        //Datos Pantalla
        setTitle("SENKU");
        setSize(new Dimension(ancho, alto));
        setLocationRelativeTo(null);
        prepareElementosMenu();
        prepareElementosChooser();
        try {
            tablero = new Tablero(size, mainPanel);
        }
        catch (SenkuException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        prepareElementosConfiguracion();
    }

    /**
     * Prepara las acciones que se ejecutaran al oprimir las cosas, por ejemplo iniciar el juego, escribir el tamaño del
     * tablero, entre otras cosas
     */
    private void prepareAcciones() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                salga();
            }
        });
        prepareAccionesMenu();
        prepareAccionesConfiguracion();
    }

    /**
     * prepareAccionesMenu
     */
    private void prepareAccionesMenu() {
        salir.addActionListener(e -> salga());
        salvar.addActionListener(e -> salvar());
        abrir.addActionListener(e -> abrir());
        nuevo.addActionListener(e -> prepareElementosTablero());
        playGame.addActionListener(e -> prepareElementosTablero());
        LoadGame.addActionListener(e -> abrir());
        SalirGame.addActionListener(e -> salga());
        tamano.addActionListener(e -> cambiarTamano());
    }

    /**
     * prepareAccionesConfiguracion
     */
    private void prepareAccionesConfiguracion() {
        color.addActionListener(e -> configuracion());
        salirConfiguracion.addActionListener(e -> volverdeConfiguraciones());
        colorPaneles.addActionListener(e -> botonColorPaneles());
        colorFondo.addActionListener(e -> botonColorFondo());
        colorBotones.addActionListener(e -> botonColorBotones());
    }

    /**
     * prepareElementosMenu
     */
    private void prepareElementosMenu() {
        //Barra Herramientas
        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        configuraciones = new JMenu("Configuraciones");
        menuBar.add(archivo);
        menuBar.add(configuraciones);

        color = new JMenuItem("Cambiar color");
        tamano = new JMenuItem("Cambiar Tamaño");
        nuevo = new JMenuItem("Nuevo");
        salvar = new JMenuItem("Salvar");
        abrir = new JMenuItem("Abrir");
        salir = new JMenuItem("Salir");

        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.addSeparator();
        archivo.add(salvar);
        archivo.addSeparator();
        archivo.add(salir);
        configuraciones.add(tamano);
        configuraciones.addSeparator();
        configuraciones.add(color);
        setJMenuBar(menuBar);

        //Botones Main
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        add(mainPanel);

        IconSenku = new ImageIcon("senku2.png");
        JPanel Imagen = new JPanel();
        JLabel label1 = new JLabel(null,IconSenku,SwingConstants.CENTER);
        Imagen.add(label1);
        mainPanel.add(Imagen);

        panelMenu = new JPanel();
        mainPanel.add(panelMenu);

        panelMenu.setLayout(new GridLayout(3,1,1,10));
        playGame = new JButton("Play Game");
        LoadGame = new JButton("Load Game");
        SalirGame = new JButton("Salir");
        panelMenu.add(playGame);
        panelMenu.add(LoadGame);
        panelMenu.add(SalirGame);
    }

    /**
     * Inicializa el explorador de archivos y el escogedor de colpr
     */
    private void prepareElementosChooser() {
        fileChooser = new JFileChooser();
    }

    /**
     * Metodo para cerrar el JFrame correctamente, antes de cerrarlo le pregunta al usuario si esta seguro de su eleccion
     */
    private void salga() {
        if (JOptionPane.showConfirmDialog(rootPane, "Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Metodo para abrir un nuevo archivo
     */
    private void abrir() {
        int action = fileChooser.showOpenDialog(abrir);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Archivo cargado: " + archivo.getName() + "\nFuncionalidad Salvar en construccion");
        }
    }

    /**
     * Abre el explorador de archivos para salvar el archivo
     */
    private void salvar() {
        int action = fileChooser.showSaveDialog(salvar);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Archivo guardado: " + archivo.getName() + "\nFuncionalidad Salvar en construccion");
        }
    }

    /**
     * Inicia el juego, oculta la ventana principal y deja ver la ventana del juego
     */
    private void prepareElementosTablero() {
        tablero.setVisible(true);
        add(tablero);
        mainPanel.setVisible(false);
    }

    /**
     * Prepara los botones usados en configuracion
     */
    private void prepareElementosConfiguracion(){
        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(null);
        colorFondo = new JButton("Cambiar color del fondo");
        colorFondo.setBounds((this.getWidth()/2)-140,80,280,30);
        colorPaneles = new JButton("Cambiar color de los bordes");
        colorPaneles.setBounds((this.getWidth()/2)-140,220,280,30);
        colorBotones = new JButton("Cambiar color de las fichas");
        colorBotones.setBounds((this.getWidth()/2)-140,150,280,30);
        salirConfiguracion = new JButton(" <-- Volver");
        salirConfiguracion.setBounds(10,10,120,30);
        panelConfiguracion.add(colorFondo);
        panelConfiguracion.add(colorBotones);
        panelConfiguracion.add(colorPaneles);
        panelConfiguracion.add(salirConfiguracion);
    }

    /**
     * Funcionalidad Para configuracion
     */
    private void configuracion(){
        panelConfiguracion.setVisible(true);
        panelConfiguracion.setBackground(Color.black);
        add(panelConfiguracion);
        tablero.setVisible(false);
    }

    /**
     * Funcionalidad Para volver de configuracion
     */
    private void volverdeConfiguraciones(){
        panelConfiguracion.setVisible(false);
        tablero.setVisible(true);
        tablero.refresque();
    }

    /**
     * Funcionalidad Para cambiar color de Botones
     */
    private void botonColorBotones(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        if (color.equals(Color.white)) {
            JOptionPane.showMessageDialog(null,"Escoja un color diferente al balnco para las fichas");
        }
        else tablero.setColor(color);
    }

    /**
     * Funcionalidad Para cambiar color de paneles
     */
    private void botonColorPaneles(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        tablero.setColorPaneles(color);
    }

    /**
     * Funcionalidad Para cambiar color de fondo
     */
    private void botonColorFondo(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        tablero.setColorFondo(color);
    }

    /**
     * Funcion que cambia el tamaño del tablero
     */
    private void cambiarTamano(){
        try{
            String newSize = JOptionPane.showInputDialog("Seleccione el nuevo tamaño(Solo numeros impares)");
            setSize(Integer.parseInt(newSize));
            Color colorFichas = tablero.getColorFicha();
            Color colorFondo = tablero.getColorFondo();
            Color colorPaneles = tablero.getColorPaneles();
            tablero.setVisible(false);
            tablero = new Tablero(size, mainPanel);
            tablero.setColorFondo(colorFondo);
            tablero.setColor(colorFichas);
            tablero.setColorPaneles(colorPaneles);
            add(tablero);
            tablero.refresque();
            tablero.setVisible(true);
        }catch(SenkuException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Digite un Entero");
        }
    }

    /**
     * Metodo para conocer el tamaño del tablero de la anterior partida
     * @param size Tamaño del tablero
     */
    public void setSize(int size) {
        this.size = size;
    }
}