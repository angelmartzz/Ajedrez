package ajedrez;

public class Reina extends Pieza {
	public Reina(Color color) {
		super(color);
	}

	/*
	 * Movimiento válido si la reina se mueve horizontalmente, verticalmente o diagonalmente
	 */
	@Override
	public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
		int filaDiferencia = Math.abs(filaFinal - filaInicial);
        int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

        return filaDiferencia == 0 || columnaDiferencia == 0 || filaDiferencia == columnaDiferencia;
    }
}

