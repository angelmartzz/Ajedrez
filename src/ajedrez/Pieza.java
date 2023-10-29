package ajedrez;

public abstract class Pieza extends Tablero {

	private Color color;

	public Pieza(Color color) {
        this.color = color;
    }
	public Color getColor() {
		return color;
	}

    /**reglas*/

	/**Movimiento*/
	
    public abstract boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal);

	public boolean puedeMoverseAPosicion(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Pieza[][] tablero) {
		if (filaDestino < 0 || filaDestino >= 8 || columnaDestino < 0 || columnaDestino >= 8) {
			return false; /** Movimiento fuera del tablero*/
		}

		if (filaOrigen == filaDestino && columnaOrigen == columnaDestino) {
			return false; /** La pieza no se ha movido*/
		}

		if (tablero[filaDestino][columnaDestino] != null && tablero[filaDestino][columnaDestino].getColor() == this.getColor()) {
			return false; /** No puedes capturar una pieza de tu propio color*/
		}

		return esMovimientoValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
	}

}
