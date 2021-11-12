package dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentacion.Tablero;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class SenkuTest {
    Tablero t,t1,t2;
    JButton[][] botonesList,botonesList1,botonesList2;
    Senku tablero,tablero1,tablero2;
    String  matriz1,matriz2,matriz3;
    String matriz7 = "[[2, 2, 0, 1, 0, 2, 2], [2, 2, 1, 1, 1, 2, 2], [0, 1, 1, 1, 1, 1, 0], [1, 1, 1, 0, 1, 1, 1], [0, 1, 1, 1, 1, 1, 0], [2, 2, 1, 1, 1, 2, 2], [2, 2, 0, 1, 0, 2, 2]]";
    String matriz9 = "[[2, 2, 2, 0, 1, 0, 2, 2, 2], [2, 2, 2, 1, 1, 1, 2, 2, 2], [2, 2, 1, 1, 1, 1, 1, 2, 2], [0, 1, 1, 1, 1, 1, 1, 1, 0], [1, 1, 1, 1, 0, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1, 1, 1, 0], [2, 2, 1, 1, 1, 1, 1, 2, 2], [2, 2, 2, 1, 1, 1, 2, 2, 2], [2, 2, 2, 0, 1, 0, 2, 2, 2]]";
    String matriz11 ="[[2, 2, 2, 2, 0, 1, 0, 2, 2, 2, 2], [2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2], [2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2], [2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2], [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0], [1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0], [2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2], [2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2], [2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2], [2, 2, 2, 2, 0, 1, 0, 2, 2, 2, 2]]";
    Boolean move1,move2,move4,move6;
    @BeforeEach
    void setUp() throws SenkuException {

        //para 7
        t = new Tablero(7,null);
        botonesList = t.getBotonesList();
        tablero = new Senku(botonesList);
        matriz1 = tablero.getMatrizPiezas();
        move1 = tablero.movePieza(1,3,3,3);
        move2 = tablero.movePieza(4,3,2,3);

        // Para 9
        t1 = new Tablero(9,null);
        botonesList1 = t1.getBotonesList();
        tablero1 = new Senku(botonesList1);
        matriz2 = tablero1.getMatrizPiezas();
        //para 11
        t2 = new Tablero(11,null);
        botonesList2 = t2.getBotonesList();
        tablero2 = new Senku(botonesList2);
        matriz3 = tablero2.getMatrizPiezas();
    }

    @Test
    void inicializarMatriz(){
        assertEquals(matriz1,matriz7);
        assertEquals(matriz2,matriz9);
        assertEquals(matriz3,matriz11);
    }

    @Test
    void movePieza() {
        assertEquals(move1,true);
        assertEquals(move2,true);
        try {
            move2 = tablero.movePieza(1,3,0,3);
            move4 = tablero.movePieza(2,2,2,2);
            move6 = tablero.movePieza(6,6,2,2);
            fail("No lanzo excepcion");
        }catch(SenkuException e){
            assertEquals(SenkuException.MOVIMIENTO_INVALIDO, e.getMessage());
        }
    }

}