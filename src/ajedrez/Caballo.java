package ajedrez;

public class Caballo extends Pieza {
	public Caballo(Color color) {
		super(color);
	}

	@Override
	public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
		/** Lógica para validar el movimiento de un caballo */
		/**Debes considerar el patrón en forma de "L". */
		/** Esta implementación es simplificada. */
		return true;
	}
}
