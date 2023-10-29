package ajedrez;

public class Tablero {


	public Pieza[][] tablero;
	private Color turnoActual;
    private boolean enroqueBlancoCorto;
    private boolean enroqueBlancoLargo;
    private boolean enroqueNegroCorto;
    private boolean enroqueNegroLargo;

	public Tablero() { initializeBoard(); }

	/** Inicializa el tablero de ajedrez con las piezas en su posición inicial. */
	
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


	/**Obtiene la pieza en una posición específica del tablero.*/
	 /**@param fil Fila de la casilla. */
	 /** @param col Columna de la casilla.*/
	 /** @return La pieza en la casilla o null si no hay ninguna pieza.*/
	
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
}


