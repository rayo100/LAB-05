package presentacion;
import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class SenkuGUI extends JFrame {
    private JPanel mainPanel;
    private ImageIcon IconSenku;
    private JPanel panelMenu;
    private static final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private static final int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private JMenuBar menuSalida;
    private JMenu configuraciones;
    private JMenu archivo;
    private JMenuItem color;
    private JMenuItem tamano;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JButton empezarJuego;
    private JButton cargarJuego;
    private JButton salirJuego;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JFileChooser fileChooser;
    private Tablero tablero;
    private JPanel panelConfiguracion;
    private JButton colorFondo;
    private JButton colorPaneles;
    private JButton colorBotones;
    private JButton salirConfiguracion;
    private int size = 7;

    public SenkuGUI() {
        prepareElementos();
        prepareAcciones();
    }
    public static void main(String[] args) {
        SenkuGUI senku = new SenkuGUI();
        senku.setVisible(true);
    }
    private void prepareElementos() {
        setTitle("SENKU GAME");
        setSize(new Dimension(ANCHO, ALTO));
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
    private void prepareAccionesMenu() {
        salir.addActionListener(e -> salga());
        salvar.addActionListener(e -> salvar());
        abrir.addActionListener(e -> abrir());
        nuevo.addActionListener(e -> prepareElementosTablero());
        empezarJuego.addActionListener(e -> prepareElementosTablero());
        cargarJuego.addActionListener(e -> abrir());
        salirJuego.addActionListener(e -> salga());
        tamano.addActionListener(e -> cambiarTamano());
    }
    private void prepareAccionesConfiguracion() {
        color.addActionListener(e -> configuracion());
        salirConfiguracion.addActionListener(e -> volverdeConfiguraciones());
        colorPaneles.addActionListener(e -> botonColorPaneles());
        colorFondo.addActionListener(e -> botonColorFondo());
        colorBotones.addActionListener(e -> botonColorBotones());
    }
    private void prepareElementosMenu() {
        menuSalida = new JMenuBar();
        archivo = new JMenu("Archivo");
        configuraciones = new JMenu("Configuraciones");
        menuSalida.add(archivo);
        menuSalida.add(configuraciones);
        color = new JMenuItem("Cambiar color");
        tamano = new JMenuItem("Cambiar Tama??o");
        nuevo = new JMenuItem("Nuevo");
        salvar = new JMenuItem("Guardar");
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
        setJMenuBar(menuSalida);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        add(mainPanel);
        IconSenku = new ImageIcon("senku2.jpg");
        JPanel Imagen = new JPanel();
        JLabel label1 = new JLabel(null,IconSenku,SwingConstants.CENTER);
        Imagen.add(label1);
        mainPanel.add(Imagen);
        panelMenu = new JPanel();
        mainPanel.add(panelMenu);
        panelMenu.setLayout(new GridLayout(3,1,1,10));
        empezarJuego = new JButton("Empezar Juego");
        cargarJuego = new JButton("Cargar Juego");
        salirJuego = new JButton("Salir");
        panelMenu.add(empezarJuego);
        panelMenu.add(cargarJuego);
        panelMenu.add(salirJuego);
    }
    private void prepareElementosChooser() {
        fileChooser = new JFileChooser();
    }

    private void salga() {
        if (JOptionPane.showConfirmDialog(rootPane, "Desea salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void salvar() {
        int action = fileChooser.showSaveDialog(salvar);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Archivo guardado: " + archivo.getName() + "\nFuncionalidad Salvar en construccion");
        }
    }

    private void abrir() {
        int action = fileChooser.showOpenDialog(abrir);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Archivo cargado: " + archivo.getName() + "\nFuncionalidad Salvar en construccion");
        }
    }

    private void prepareElementosTablero() {
        tablero.setVisible(true);
        add(tablero);
        mainPanel.setVisible(false);
    }
    private void prepareElementosConfiguracion(){
        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(null);
        colorFondo = new JButton("Cambiar color del fondo");
        colorFondo.setBounds((this.getWidth()/2)-140,80,280,30);
        colorPaneles = new JButton("Cambiar color de los bordes");
        colorPaneles.setBounds((this.getWidth()/2)-140,220,280,30);
        colorBotones = new JButton("Cambiar color de las fichas");
        colorBotones.setBounds((this.getWidth()/2)-140,150,280,30);
        salirConfiguracion = new JButton(" Volver");
        salirConfiguracion.setBounds(10,10,120,30);
        panelConfiguracion.add(colorPaneles);
        panelConfiguracion.add(colorFondo);
        panelConfiguracion.add(colorBotones);
        panelConfiguracion.add(salirConfiguracion);
    }
    private void configuracion(){
        panelConfiguracion.setVisible(true);
        panelConfiguracion.setBackground(Color.black);
        add(panelConfiguracion);
        tablero.setVisible(false);
    }
    private void volverdeConfiguraciones(){
        panelConfiguracion.setVisible(false);
        tablero.setVisible(true);
        tablero.refresque();
    }
    private void botonColorBotones(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        if (color.equals(Color.white)) {
            JOptionPane.showMessageDialog(null,"Escoja un color diferente al blanco para las fichas");
        }
        else tablero.setColor(color);
    }
    private void botonColorPaneles(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        tablero.setColorPaneles(color);
    }
    private void botonColorFondo(){
        Color color = JColorChooser.showDialog(panelConfiguracion, "Elija un color", Color.BLACK);
        tablero.setColorFondo(color);
    }
    private void cambiarTamano(){
        try{
            String newSize = JOptionPane.showInputDialog("Seleccione el nuevo tama??o");
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
    public void setSize(int size) {
        this.size = size;
    }
}