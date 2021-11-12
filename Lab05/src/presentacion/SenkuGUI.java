package presentacion;

import dominio.Senku;
import dominio.SenkuException;
import dominio.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SenkuGUI extends JFrame {
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu archivo;
    private JMenu configuraciones;
    private JMenuItem color;
    private JMenuItem tamano;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JButton play;
    private JButton load;
    private JButton salirJuego;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JFileChooser fileChooser;
    private JPanel panelConfiguracion;
    private JPanel panelMenu;
    private JButton fondoColor;
    private JButton panelColor;
    private JButton salirConfigurar;
    private ImageIcon senkuImg;
    private final int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private final int LARGO = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private Tablero tablero;
    private final int SIZE = 7;

    private SenkuGUI(){
        super("Senku");
        Senku senku = new Senku();	/*MODELO*/
        prepareElementos();			/*VISTA*/
        prepareAcciones();			/*CONTROLADOR*/
    }



    public static void main(String[] args){
        SenkuGUI gui= new SenkuGUI();
        gui.setVisible(true);
    }

    private void prepareElementos(){
        setLocationRelativeTo(null);
        prepareElementosMenu();
        prepareElementosEscoger();
        try {
            tablero = new Tablero(SIZE,mainPanel);
        }
        catch (SenkuException se){
            JOptionPane.showMessageDialog(null, se.getMessage());
        }
        prepareElementosConfig();
    }

    private void prepareElementosConfig() {

    }

    private void prepareElementosEscoger() {
        fileChooser = new JFileChooser();
    }

    private void prepareElementosMenu() {
        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        configuraciones = new JMenu("Configuracion");
        menuBar.add(archivo);
        menuBar.add(configuraciones);
        color = new JMenuItem("Cambiar color");
        tamano = new JMenuItem("Cambiar tamano");
        nuevo = new JMenuItem("Nuevo");
        salvar = new JMenuItem("Salvar");
        abrir = new JMenuItem("Abrir");
        salir = new JMenuItem("Salir");
        prepareElementosMenuP1();
        prepareElementosMenuP2();
    }
    private void prepareElementosMenuP1(){
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
    }
    private void prepareElementosMenuP2(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        add(mainPanel);
        senkuImg = new ImageIcon("senku2.png");
        JPanel Imagen = new JPanel();
        JLabel label1 = new JLabel(null,senkuImg,SwingConstants.CENTER);
        Imagen.add(label1);
        mainPanel.add(Imagen);
        panelMenu = new JPanel();
        mainPanel.add(panelMenu);
        panelMenu.setLayout(new GridLayout(3,1,1,10));
        play = new JButton("Play Game");
        load = new JButton("Load Game");
        salirJuego = new JButton("Salir");
        panelMenu.add(play);
        panelMenu.add(load);
        panelMenu.add(salirJuego);
    }
    private void prepareAcciones() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });
        prepareAccionesMenu();
        prepareAccionesConfiguracion();
    }

    private void prepareAccionesMenu() {
        salir.addActionListener(e -> salir());
        salvar.addActionListener(e -> salvar());
        abrir.addActionListener(e -> abrir());
        nuevo.addActionListener(e -> prepareElementosTablero());
        play.addActionListener(e -> prepareElementosTablero());
        load.addActionListener(e -> abrir());
        salirJuego.addActionListener(e -> salir());
        tamano.addActionListener(e -> cambiarTamano());
    }
    private void prepareAccionesConfiguracion() {
        color.addActionListener(e -> configuracion());
        salirConfigurar.addActionListener(e -> volverDeConfigurar());
    }

    private void volverDeConfigurar() {
    }

    private void configuracion() {
    }

    private void cambiarTamano() {
    }

    private void abrir() {
    }

    private void prepareElementosTablero() {
    }

    private void salvar() {
    }



    private void salir() {
    }
}
