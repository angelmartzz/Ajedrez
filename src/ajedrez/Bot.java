package ajedrez;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa a un bot que realiza movimientos en un juego de ajedrez.
 */
public class Bot {

    private Color color;

    /**
     * Constructor de la clase Bot.
     *
     * @param color Color del bot (Blanco o Negro).
     */
    public Bot(Color color) {
        this.color = color;
    }

    /**
     * Realiza un movimiento en el tablero.
     *
     * @param tablero Tablero de ajedrez en el que se realizará el movimiento.
     */
    public void realizarMovimiento(Tablero tablero) {
        // Obtener todas las piezas del bot en el tablero
        ArrayList<Pieza> piezasBot = obtenerPiezasBot(tablero);

        // Seleccionar aleatoriamente una pieza del bot
        Random random = new Random();
        Pieza piezaSeleccionada = piezasBot.get(random.nextInt(piezasBot.size()));

        // Obtener los movimientos posibles para la pieza seleccionada
        ArrayList<int[]> movimientosPosibles = obtenerMovimientosPosibles(tablero, piezaSeleccionada);

        // Filtrar movimientos para capturar piezas del oponente si es posible
        ArrayList<int[]> movimientosCaptura = filtrarMovimientosCaptura(tablero, piezaSeleccionada, movimientosPosibles);

        // Seleccionar aleatoriamente un movimiento de captura si existe
        if (!movimientosCaptura.isEmpty()) {
            int[] movimientoSeleccionado = movimientosCaptura.get(random.nextInt(movimientosCaptura.size()));
            tablero.intentarMoverPieza(tablero.getFil(piezaSeleccionada), tablero.getCol(piezaSeleccionada), movimientoSeleccionado[0], movimientoSeleccionado[1]);
        } else if (!movimientosPosibles.isEmpty()) {
            // Si no hay movimientos de captura, seleccionar aleatoriamente un movimiento posible
            int[] movimientoSeleccionado = movimientosPosibles.get(random.nextInt(movimientosPosibles.size()));
            tablero.intentarMoverPieza(tablero.getFil(piezaSeleccionada), tablero.getCol(piezaSeleccionada), movimientoSeleccionado[0], movimientoSeleccionado[1]);
        }

        // Mostrar el tablero después del movimiento
        tablero.mostrarTablero();
    }

    /**
     * Obtiene todas las piezas del bot en el tablero.
     *
     * @param tablero Tablero de ajedrez.
     * @return Lista de piezas del bot.
     */
    private ArrayList<Pieza> obtenerPiezasBot(Tablero tablero) {
        ArrayList<Pieza> piezasBot = new ArrayList<>();
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero.getPiece(fila, columna);
                if (pieza != null && pieza.getColor() == color) {
                    piezasBot.add(pieza);
                }
            }
        }
        return piezasBot;
    }

    /**
     * Obtiene los movimientos posibles para una pieza en el tablero.
     *
     * @param tablero Tablero de ajedrez.
     * @param pieza   Pieza seleccionada.
     * @return Lista de movimientos posibles para la pieza.
     */
    private ArrayList<int[]> obtenerMovimientosPosibles(Tablero tablero, Pieza pieza) {
        ArrayList<int[]> movimientosPosibles = new ArrayList<>();
        for (int filaDestino = 0; filaDestino < 8; filaDestino++) {
            for (int columnaDestino = 0; columnaDestino < 8; columnaDestino++) {
                if (tablero.intentarMoverPieza(
                        tablero.getFil(pieza),
                        tablero.getCol(pieza),
                        filaDestino,
                        columnaDestino
                )) {
                    movimientosPosibles.add(new int[]{filaDestino, columnaDestino});
                    // Deshacer el movimiento para evaluar otros movimientos posibles
                    tablero.deshacerMovimiento(
                            tablero.getFil(pieza),
                            tablero.getCol(pieza),
                            filaDestino,
                            columnaDestino
                    );
                }
            }
        }
        return movimientosPosibles;
    }

    /**
     * Filtra los movimientos posibles para capturar piezas del oponente.
     *
     * @param tablero             Tablero de ajedrez.
     * @param pieza               Pieza seleccionada.
     * @param movimientosPosibles Lista de movimientos posibles para la pieza.
     * @return Lista de movimientos que resultan en la captura de una pieza del oponente.
     */
    private ArrayList<int[]> filtrarMovimientosCaptura(Tablero tablero, Pieza pieza, ArrayList<int[]> movimientosPosibles) {
        ArrayList<int[]> movimientosCaptura = new ArrayList<>();
        for (int[] movimiento : movimientosPosibles) {
            int filaDestino = movimiento[0];
            int columnaDestino = movimiento[1];
            Pieza piezaEnDestino = tablero.getPiece(filaDestino, columnaDestino);
            // Filtrar movimientos que resultan en la captura de una pieza del oponente
            if (piezaEnDestino != null && piezaEnDestino.getColor() != color) {
                movimientosCaptura.add(new int[]{filaDestino, columnaDestino});
            }
        }
        return movimientosCaptura;
    }
}
v
