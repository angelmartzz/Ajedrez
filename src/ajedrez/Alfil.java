package ajedrez;

public class Alfil extends Pieza {
    public Alfil(Color color) {
        super(color);
    }

    @Override
    public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
    	int filaDiferencia = Math.abs(filaFinal - filaInicial);
        int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

        /** Movimiento válido si la diferencia en filas es igual a la diferencia en columnas */
        return filaDiferencia == columnaDiferencia;
    }
}