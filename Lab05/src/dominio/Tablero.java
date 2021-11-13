package dominio;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Tablero extends JPanel {
    private final JPanel menuPrincipal;
    private int size;
    private Color colorFicha = Color.GREEN;
    private Color colorFondo = Color.BLACK;
    private Color colorPaneles = Color.WHITE;
    private JPanel tablero;
    private final JButton[][] botonesList;
    private JButton reiniciar;
    private JButton regresarMenu;
    private JPanel midPanel;
    private JPanel topPanel;
    private JLabel fichasCap;
    private JLabel moves;
    private int movimientos = 0;
    private int fichasCapturadas = 0;
    private int BottIi = -1;
    private int BottIj = -1;
    private int BottFi = -1;
    private int BottFj = -1;
    private Senku senku;

    public Tablero(int tamano, JPanel menu) throws SenkuException{
        if (tamano%2 == 1 && tamano <= 33) {
            this.size = tamano;
            menuPrincipal = menu;
            botonesList = new JButton[size][size];
            prepareElementos();
            prepareAcciones();
            senku = new Senku(botonesList);
        }
        else {
            throw new SenkuException(SenkuException.TAMANO_INVALIDO);
        }
    }

    private void prepareElementos(){
        this.setLayout(new BorderLayout(size,size));
        this.setBackground(colorFondo);
        this.setBorder(BorderFactory.createMatteBorder(4,4,4,4,colorFondo));
        prepareElementosTablero();
        prepareElementosOpciones();
        ordenarBotones();
    }
    private void prepareAcciones(){
        regresarMenu.addActionListener(e-> regresarAlMenu());
        for (int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                botonesList[i][j].addActionListener(e -> knowBotton(e));
            }
        }
        reiniciar.addActionListener(e-> reiniciar());
    }
    private void knowBotton(ActionEvent e) {
        if (BottIi == -1 || BottFi != -1) {
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    if (e.getSource() == botonesList[i][j]) {
                        BottIi = i;
                        BottIj = j;
                        BottFi = -1;
                        BottFj = -1;
                    }}}}
        else if(BottFi == -1){
            for(int i = 0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(e.getSource().equals(botonesList[i][j])){
                        BottFi = i;
                        BottFj = j;
                    }
                }}}
        if(BottIi != -1 && BottFi != -1){
            moverficha();
        }
    }
    public void moverficha() {
        boolean validate = false;
        try {
            validate = senku.movePieza(BottIi,BottIj,BottFi,BottFj);
        }
        catch (SenkuException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (validate) {
            botonesList[BottIi][BottIj].setBackground(Color.WHITE);
            botonesList[BottFi][BottFj].setBackground(colorFicha);
            if (BottIi == BottFi && BottIj-2 == BottFj) botonesList[BottIi][BottFj+1].setBackground(Color.WHITE);
            if (BottIi == BottFi && BottIj+2 == BottFj) botonesList[BottIi][BottFj-1].setBackground(Color.WHITE);
            if (BottIi-2 == BottFi && BottIj == BottFj) botonesList[BottIi-1][BottFj].setBackground(Color.WHITE);
            if (BottIj == BottFj && BottIi+2 == BottFi) botonesList[BottIi+1][BottFj].setBackground(Color.WHITE);
            movimientos += 1;
            fichasCapturadas += 1;
            updateMoves();
        }
        if (CheckWinner()) {
            JOptionPane.showMessageDialog(null, "Felicidades Ganaste");
        }
    }
    public boolean CheckWinner() {
        int cont = 0;
        boolean winner = false;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (botonesList[i][j].getBackground() == colorFicha) {
                    cont +=1;
                }
            }
        }
        if (cont == 1 && botonesList[size/2][size/2].getBackground() == colorFicha) {
            winner = true;
        }
        return winner;
    }
    private void prepareElementosTablero(){
        midPanel = new JPanel();
        midPanel.setBorder(new LineBorder(colorFondo,3));
        midPanel.setLayout(new FlowLayout(4,4,4));
        midPanel.setBackground(colorPaneles);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2,1,5,5));
        gridPanel.setBorder(new LineBorder(colorFondo,3));
        gridPanel.setBackground(Color.WHITE);
        JLabel textMovimientos = new JLabel("MOVIMIENTOS");
        JLabel textFichas = new JLabel("FICHAS CAPTURADAS");
        textMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        textFichas.setHorizontalAlignment(SwingConstants.CENTER);
        moves = new JLabel(Integer.toString(movimientos));
        fichasCap = new JLabel(Integer.toString(fichasCapturadas));
        gridPanel.add(textMovimientos);
        gridPanel.add(moves);
        gridPanel.add(textFichas);
        gridPanel.add(fichasCap);
        midPanel.add(gridPanel, BorderLayout.SOUTH);
        add(midPanel, BorderLayout.WEST);
        tablero = new JPanel();
        tablero.setBorder(BorderFactory.createLineBorder(colorFondo));
        tablero.setLayout(new GridLayout(size,size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton ficha = new JButton();
                ficha.setBorder(BorderFactory.createLineBorder(colorFondo));
                botonesList[i][j] = ficha;
                botonesList[i][j].setBorder(new LineBorder(colorFondo, 4));
                tablero.add(ficha);
            }
        }
        add(tablero);
        tablero.setBackground(colorFondo);
    }
    private void prepareElementosOpciones(){
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(colorFondo,3));
        topPanel.setBackground(colorPaneles);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(topPanel, BorderLayout.SOUTH);
        reiniciar = new JButton("Reiniciar");
        regresarMenu = new JButton("Regresar Al Menu");
        topPanel.add(reiniciar);
        topPanel.add(regresarMenu);
    }
    private void regresarAlMenu(){
        if (JOptionPane.showConfirmDialog(this.getRootPane(), "¿Desea regresar al menú? Perderá los datos de esta partida",
                "Regresar al menú", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            menuPrincipal.setVisible(true);
            reiniciar();
        }
    }
    private void reiniciar(){
        this.movimientos = 0;
        this.fichasCapturadas = 0;
        updateMoves();
        ordenarBotones();
        senku.inicializarMatriz(botonesList);
    }
    private void updateMoves(){
        moves.setText(Integer.toString(movimientos));
        fichasCap.setText(Integer.toString(fichasCapturadas));
    }
    public void refresque() {
        midPanel.setBackground(colorPaneles);
        topPanel.setBackground(colorPaneles);
        this.setBackground(colorFondo);
        this.setBorder(BorderFactory.createMatteBorder(4,4,4,4,colorFondo));
        midPanel.setBorder(new LineBorder(colorFondo,3));
        topPanel.setBorder(new LineBorder(colorFondo,3));
        tablero.setBackground(colorFondo);
        for(JButton[] p:botonesList){
            for(JButton boton:p){
                boton.setBorder(new LineBorder(colorFondo, 4));
                if (!(boton.getBackground().equals(Color.white))) {
                    boton.setBackground(colorFicha);
                }
            }
        }
    }
    public void ordenarBotones(){
        int cont = 0;
        int mitad = (size/2);
        for(JButton[] b:botonesList){
            for(JButton button:b){
                button.setBackground(Color.gray);
            }
        }
        for(int i=0; i<mitad;i++) {
            if (cont == 0) {
                botonesList[i][mitad + 1].setBackground(Color.white);
                botonesList[i][mitad - 1].setBackground(Color.white);
                botonesList[botonesList.length - i - 1][mitad + 1].setBackground(Color.white);
                botonesList[botonesList.length - i - 1][mitad - 1].setBackground(Color.white);
                botonesList[mitad + 1][i].setBackground(Color.white);
                botonesList[mitad - 1][i].setBackground(Color.white);
                botonesList[mitad + 1][botonesList.length - i - 1].setBackground(Color.white);
                botonesList[mitad - 1][botonesList.length - i - 1].setBackground(Color.white);
            }
            if (cont != mitad) {
                botonesList[i][mitad].setBackground(colorFicha);
                botonesList[botonesList.length-i-1][mitad].setBackground(colorFicha);
            }
            for(int k = 1;k<cont+1;k++){
                botonesList[i][mitad+k].setBackground(colorFicha);
                botonesList[i][mitad-k].setBackground(colorFicha);
            }
            for(int k = 1; k< cont+1;k++){
                botonesList[botonesList.length-i-1][mitad+k].setBackground(colorFicha);
                botonesList[botonesList.length-i-1][mitad-k].setBackground(colorFicha);
            }
            for(int k=0;k<size;k++){
                botonesList[mitad][k].setBackground(colorFicha);
            }
            botonesList[mitad][mitad].setBackground(Color.WHITE);
            cont++;
        }
        borrarGrises();
    }
    public void borrarGrises(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if (botonesList[i][j].getBackground().equals(Color.gray)){
                    botonesList[i][j].setVisible(false);
                }
            }
        }
    }
    public void setColor(Color color){
        this.colorFicha = color;
    }
    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }
    public void setColorPaneles(Color colorPaneles) {
        this.colorPaneles = colorPaneles;
    }
    public Color getColorFicha() {
        return colorFicha;
    }
    public Color getColorFondo() {
        return colorFondo;
    }
    public Color getColorPaneles() {
        return colorPaneles;
    }
    public JButton[][] getBotonesList() {
        return botonesList;
    }
}