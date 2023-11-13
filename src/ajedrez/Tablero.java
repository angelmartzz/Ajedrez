package ajedrez;

import javax.swing.*;
import java.awt.*;


public class Tablero extends JFrame{

	public Tablero() { 
		super("Tablero de Ajedrez");
		initializeBoard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Crear el panel del tablero
        JPanel tableroPanel = new JPanel(new GridLayout(8, 8));

        // Colores para los escaques
        Color blanco = new Color(255, 255, 255);
        Color negro = new Color(0, 0, 0);

        // Iterar a través de las filas y columnas para agregar los escaques al panel
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                JPanel escaque = new JPanel();
                escaque.setPreferredSize(new Dimension(50, 50));
                escaque.setBackground((fila + columna) % 2 == 0 ? blanco : negro);
                tableroPanel.add(escaque);
            }
        }

        // Agregar el panel del tablero a la ventana
        add(tableroPanel);

        // Hacer visible la ventana
        setVisible(true);
	}
	public Pieza[][] tablero;
	public Pieza[][] tableroAux;

	private Color turnoActual;
	private boolean enroqueBlancoCorto;
	private boolean enroqueBlancoLargo;
	private boolean enroqueNegroCorto;
	private boolean enroqueNegroLargo;


	/**
	 * Inicializa el tablero de ajedrez con las piezas en su posición inicial.
	 */
	private void initializeBoard() {
		tablero = new Pieza[7][7];

		tablero[0][0] = new Torre(Color.Blanco);
		tablero[0][7] = new Torre(Color.Blanco);
		tablero[7][0] = new Torre(Color.Negro);
		tablero[7][7] = new Torre(Color.Negro);

		tablero[0][1] = new Caballo(Color.Blanco);
		tablero[0][6] = new Caballo(Color.Blanco);
		tablero[7][1] = new Caballo(Color.Negro);
		tablero[7][7] = new Caballo(Color.Negro);

		tablero[0][2] = new Alfil(Color.Blanco);
		tablero[0][5] = new Alfil(Color.Blanco);
		tablero[7][2] = new Alfil(Color.Negro);
		tablero[7][5] = new Alfil(Color.Negro);

		tablero[0][3] = new Reina(Color.Blanco);
		tablero[7][3] = new Reina(Color.Negro);

		tablero[0][4] = new Rey(Color.Blanco);
		tablero[7][4] = new Rey(Color.Negro);

		for (int col = 0; col < 8; col++) {
			tablero[1][col] = new Peon(Color.Blanco);
			tablero[6][col] = new Peon(Color.Negro);
		}
	}


	/**
	 * Obtiene la pieza en una posición específica del tablero.
	 *
	 * @param fil Fila de la casilla.
	 * @param col Columna de la casilla.
	 * @return La pieza en la casilla o null si no hay ninguna pieza.
	 */
	public Pieza getPiece(int fil, int col) {
		if (dentroTablero(fil, col)) {
			return tablero[fil][col];
		}
		return null;
	}

	/**
	 * Coloca una pieza en una posición específica del tablero.
	 *
	 * @param fil   Fila de la casilla.
	 * @param col   Columna de la casilla.
	 * @param piece Pieza a colocar en la casilla.
	 */
	public void setPiece(int fil, int col, Pieza piece) {
		if (dentroTablero(fil, col)) {
			tablero[fil][col] = piece;
		}
	}
	
	 /**
     * Coloca una pieza en una posición específica del tablero y verifica si la operación fue exitosa.
     *
     * @param fil   Fila de la casilla.
     * @param col   Columna de la casilla.
     * @param piece Pieza a colocar en la casilla.
     * @return true si la pieza se colocó con éxito, false de lo contrario.
     */
	
	public boolean setPieceAsk(int fil, int col, Pieza piece) {
		if (dentroTablero(fil, col)) {
			tablero[fil][col] = piece;
		}
		return (tablero[fil][col] == piece);
	}

	/**
	 * Imprime el tablero en la consola para visualizarlo.
	 */
	public void printBoard() {
		for (int fil = 0; fil < 8; fil++) {
			for (int col = 0; col < 8; col++) {
				Pieza piece = getPiece(fil, col);
				if (piece != null) {
					System.out.print(piece.toString() + " ");
				} else {
					System.out.print("  .  ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Verifica si una posición está dentro de los límites del tablero.
	 *
	 * @param fil Fila de la casilla.
	 * @param col Columna de la casilla.
	 * @return true si la posición está dentro de los límites del tablero, false en caso contrario.
	 */
	private boolean dentroTablero(int fil, int col) {
		return fil >= 0 && fil < 8 && col >= 0 && col < 8;
	}

	 /**
     * Verifica si el rey de un color específico está en jaque.
     *
     * @param color Color del rey.
     * @return true si el rey está en jaque, false en caso contrario.
     */
	
	
	  /**
     * Verifica si el rey de un color específico está en jaque mate.
     *
     * @param color Color del rey.
     * @return true si el rey está en jaque mate, false en caso contrario.
     */
	public boolean esJaque(Color color) {
		// Encuentra la posición del rey del color especificado
		int filaRey = -1;
		int columnaRey = -1;

		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza instanceof Rey && pieza.getColor() == color) {
					filaRey = fila;
					columnaRey = columna;
					break;
				}
			}
			if (filaRey != -1) {
				break;
			}
		}

		// Comprueba si alguna pieza del oponente puede moverse y atacar al rey
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza != null && pieza.getColor() != color) {
					if (pieza.esMovimientoValido(fila, columna, filaRey, columnaRey)) {
						return true; // El rey está en jaque
					}
				}
			}
		}

		return false; // El rey no está en jaque
	}
	
	
	public boolean esJaqueMate(Color color) {
		// Verificar si el rey está en jaque
		if (!esJaque(color)) {
			return false; // El rey no está en jaque mate si no está en jaque
		}


		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza != null && pieza.getColor() == color) {
					for (int filaDestino = 0; filaDestino < 8; filaDestino++) {
						for (int columnaDestino = 0; columnaDestino < 8; columnaDestino++) {
							if (setPieceAsk(filaDestino, columnaDestino, pieza)) {
								setPiece(filaDestino, columnaDestino, pieza);
								boolean jaqueTemporal = esJaque(color);

								deshacerMovimiento(fila, columna, filaDestino, columnaDestino);

								if (!jaqueTemporal) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true; // El rey está en jaque mate
	}
	
	 /**
     * Deshace un movimiento realizado en el tablero.
     *
     * @param filaInicial    Fila inicial del movimiento.
     * @param columnaInicial Columna inicial del movimiento.
     * @param filaFinal      Fila final del movimiento.
     * @param columnaFinal   Columna final del movimiento.
     */
	 public void deshacerMovimiento(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
	        Pieza piezaMovida = tablero[filaFinal][columnaFinal];
	        tablero[filaFinal][columnaFinal] = null; // La casilla de destino se vuelve nula
	        tablero[filaInicial][columnaInicial] = piezaMovida; // La pieza vuelve a su posición original
	    }
}


