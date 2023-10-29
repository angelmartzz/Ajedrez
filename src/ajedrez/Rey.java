package ajedrez;

public class Rey extends Pieza {
	public Rey(Color color) {
        super(color);
    }

	/*
	 * Movimiento válido si el rey se mueve horizontalmente, verticalmente o diagonalmente una casilla
	 */
    @Override
    public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
    	int filaDiferencia = Math.abs(filaFinal - filaInicial);
        int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

        return filaDiferencia <= 1 && columnaDiferencia <= 1;
    }
}
