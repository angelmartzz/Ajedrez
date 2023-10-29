package ajedrez;

public class Torre extends Pieza {

	public Torre(Color color) {
		super(color);
	}

	/** Movimiento válido si la torre se mueve solo horizontalmente o verticalmente*/
	@Override
	public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
		int filaDiferencia = Math.abs(filaFinal - filaInicial);
		int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

		return filaDiferencia == 0 || columnaDiferencia == 0;
	} 
}


