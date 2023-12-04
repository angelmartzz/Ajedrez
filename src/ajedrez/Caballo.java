package ajedrez;

public class Caballo extends Pieza {
	public Caballo(Color color) {
		super(color);
	}

	@Override
	public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        int filaDiferencia = Math.abs(filaFinal - filaInicial);
        int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

        // Movimiento válido si el caballo se mueve en forma de "L" (dos pasos en una dirección y uno en otra)
        return (filaDiferencia == 2 && columnaDiferencia == 1) || (filaDiferencia == 1 && columnaDiferencia == 2);
    }
}
