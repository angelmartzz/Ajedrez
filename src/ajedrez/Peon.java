package ajedrez;

public class Peon extends Pieza {

	
	    public Peon(Color color) {
	        super(color);
	    }

		/** Los peones solo se mueven hacia adelante (hacia el extremo del oponente) */
	    /** Las filas aumentan en dirección opuesta para los peones negros y blancos */
	    /**Movimiento de un paso hacia adelante*/
		/** Movimiento inicial de dos pasos hacia adelante*/
		/** Captura en diagonal*/
		 
	    @Override
	    public boolean esMovimientoValido(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
	    	int filaDiferencia = Math.abs(filaFinal - filaInicial);
	        int columnaDiferencia = Math.abs(columnaFinal - columnaInicial);

	        int direccion = (getColor() == Color.Blanco) ? -1 : 1;

	        if (filaDiferencia == 1 && columnaDiferencia == 0) {
	            return true;
	        }

	        /** Movimiento inicial de dos pasos hacia adelante */
	        if (filaDiferencia == 2 && columnaDiferencia == 0 && filaInicial == 1 && direccion == 1) {
	            return true;
	        }
	        if (filaDiferencia == 2 && columnaDiferencia == 0 && filaInicial == 6 && direccion == -1) {
	            return true;
	        }

	        if (filaDiferencia == 1 && columnaDiferencia == 1 && tablero[filaFinal][columnaFinal].) {
	            return true;
	        }

	        return false;
	    }
	}

