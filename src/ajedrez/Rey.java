package ajedrez;

public class Rey {
    int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y
    boolean primerMovimiento; // Variable para rastrear el primer movimiento del rey

    public Rey(int x, int y) {
        this.x = x;
        this.y = y;
        this.primerMovimiento = true;
    }

    // Método para validar si un movimiento es válido para el rey
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);

        // El rey puede moverse una casilla en cualquier dirección (arriba, abajo, izquierda, derecha o diagonal)
        return (deltaX <= 1 && deltaY <= 1);
    }

    // Método para mover el rey a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY) {
        if (esMovimientoValido(nuevaX, nuevaY)) {
            x = nuevaX;
            y = nuevaY;
            primerMovimiento = false;
            System.out.println("El rey se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para el rey");
        }
    }

    // Método para realizar el enroque
    public void enrocar(Torre torre) {
        if (primerMovimiento && torre.esPrimerMovimiento() && !haSidoAmenazado() && !torre.haSidoAmenazada()) {
            // Verificar que no haya ninguna pieza entre el rey y la torre
            int direccion = Integer.compare(torre.getX() - x, 0); // Dirección de la torre en relación al rey
            int startX = x + direccion;
            int endX = torre.getX() - direccion;
            int y = this.y;
            boolean enroqueValido = true;

            for (int i = startX; i <= endX; i += direccion) {
                // Comprueba cada casilla en el camino del enroque
                // Si alguna casilla no está vacía, el enroque no es válido
                // También, verifica que ninguna casilla esté amenazada
                // Puedes implementar la lógica de verificación de amenazas según tus necesidades
                // Aquí asumimos que ninguna casilla está amenazada
                if (i != x && !casillaVacia(i, y)) {
                    enroqueValido = false;
                    break;
                }
            }

            if (enroqueValido) {
                x = torre.getX();
                torre.setX(startX);
                System.out.println("El rey ha realizado un enroque.");
            } else {
                System.out.println("El enroque no es válido debido a obstrucciones o jaque.");
            }
        } else {
            System.out.println("El enroque no es válido en estas condiciones.");
        }
    }

    // Método para verificar si una casilla está vacía (simulado para este ejemplo)
    private boolean casillaVacia(int x, int y) {
        // Simula la verificación de si la casilla está vacía
        return true;
    }

    // Método para verificar si el rey ha sido amenazado (simulado para este ejemplo)
    private boolean haSidoAmenazado() {
        // Simula la verificación de si el rey está en jaque
        return false;
    }
}
