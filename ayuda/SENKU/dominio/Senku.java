package dominio;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Senku {
    //Variables
    private int[][] matrizPiezas;
    private int size;
    private final int vacia = 0;
    private final int ocupada = 1;
    private final int nulla = 2;

    /**
     * Constructor de la clase Senku
     *
     * @param matrizButton
     */
    public Senku(JButton[][] matrizButton) {
        this.size = matrizButton.length;
        this.matrizPiezas = new int[size][size];
        inicializarMatriz(matrizButton);
    }

    /**
     * Metodo para inicializar Matriz del tablero
     *
     * @param matrizButton
     */
    public void inicializarMatriz(JButton[][] matrizButton) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrizButton[i][j].getBackground().equals(Color.white)) {
                    matrizPiezas[i][j] = vacia;
                } else if (matrizButton[i][j].getBackground().equals(Color.blue)) {
                    matrizPiezas[i][j] = ocupada;
                } else {
                    matrizPiezas[i][j] = nulla;
                }
            }
        }
    }

    /**
     * Metodo que mueve una pieza
     *
     * @param pos1x Posicion x del Boton 1
     * @param pos1y Posicion y del Boton 1
     * @param pos2x Posicion x del Boton 2
     * @param pos2y Posicion y del Boton 2
     * @return true si se puede mover la ficha, false de lo contrario
     * @throws SenkuException
     */
    public boolean movePieza(int pos1x, int pos1y, int pos2x, int pos2y) throws SenkuException {
        boolean move = moveEste(pos1x,pos1y,pos2x,pos2y) || moveOeste(pos1x,pos1y,pos2x,pos2y) || moveNorte(pos1x,pos1y,pos2x,pos2y) || moveSur(pos1x,pos1y,pos2x,pos2y);
        if (move && matrizPiezas[pos1x][pos1y] == 1) {
            matrizPiezas[pos2x][pos2y] = ocupada;
            matrizPiezas[pos1x][pos1y] = vacia;
            return move;
        } else {
            throw new SenkuException(SenkuException.MOVIMIENTO_INVALIDO);
        }
    }

    /**
     * Verifica si el movimiento se puede hacer al Norte
     * @param pos1x Posicion x del Boton 1
     * @param pos1y Posicion y del Boton 1
     * @param pos2x Posicion x del Boton 2
     * @param pos2y Posicion y del Boton 2
     * @return true si se puede mover, false de lo contrario
     */
    public boolean moveNorte(int pos1x, int pos1y, int pos2x, int pos2y) {
        boolean move = false;
        if ((pos1x - 2 == pos2x) && (pos1y == pos2y)) {
            if ((matrizPiezas[pos1x - 1][pos1y] == ocupada) && (matrizPiezas[pos2x][pos2y] == vacia)) {
                move = true;
                matrizPiezas[pos1x - 1][pos1y] = vacia;
            }
        }
        return move;
    }

    /**
     * Verifica si el movimiento se puede hacer al Sur
     * @param pos1x Posicion x del Boton 1
     * @param pos1y Posicion y del Boton 1
     * @param pos2x Posicion x del Boton 2
     * @param pos2y Posicion y del Boton 2
     * @return true si se puede mover, false de lo contrario
     */
    public boolean moveSur(int pos1x, int pos1y, int pos2x, int pos2y) {
        boolean move = false;
        if ((pos1x + 2 == pos2x) && (pos1y == pos2y)) {
            if ((matrizPiezas[pos1x + 1][pos1y] == ocupada) && (matrizPiezas[pos2x][pos2y] == vacia)) {
                move = true;
                matrizPiezas[pos1x + 1][pos1y] = vacia;
            }
        }
        return move;
    }

    /**
     * Verifica si el movimiento se puede hacer al Este
     * @param pos1x Posicion x del Boton 1
     * @param pos1y Posicion y del Boton 1
     * @param pos2x Posicion x del Boton 2
     * @param pos2y Posicion y del Boton 2
     * @return true si se puede mover, false de lo contrario
     */
    public boolean moveEste(int pos1x, int pos1y, int pos2x, int pos2y) {
        boolean move = false;
        if ((pos1x == pos2x) && (pos1y + 2 == pos2y)) {
            if ((matrizPiezas[pos1x][pos1y + 1] == ocupada) && (matrizPiezas[pos2x][pos2y] == vacia)) {
                move = true;
                matrizPiezas[pos1x][pos1y + 1] = vacia;
            }
        }
        return move;
    }

    /**
     * Verifica si el movimiento se puede hacer al Oeste
     * @param pos1x Posicion x del Boton 1
     * @param pos1y Posicion y del Boton 1
     * @param pos2x Posicion x del Boton 2
     * @param pos2y Posicion y del Boton 2
     * @return true si se puede mover, false de lo contrario
     */
    public boolean moveOeste(int pos1x, int pos1y, int pos2x, int pos2y) {
        boolean move = false;
        if ((pos1x == pos2x) && (pos1y - 2 == pos2y)) {
            if ((matrizPiezas[pos1x][pos1y - 1] == ocupada) && (matrizPiezas[pos2x][pos2y] == vacia)) {
                move = true;
                matrizPiezas[pos1x][pos1y - 1] = vacia;
            }
        }
        return move;
    }

    /**
     * Retorna la matriz de las piezas del tablero
     * @return Matriz de piezas
     */
    public String getMatrizPiezas() {
        return Arrays.deepToString(matrizPiezas);
    }
}