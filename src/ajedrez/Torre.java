package ajedrez;

public class Torre {
    int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y
    boolean primerMovimiento; // Variable para rastrear el primer movimiento de la torre

    public Torre(int x, int y) {
        this.x = x;
        this.y = y;
        this.primerMovimiento = true;
    }

    // Método para validar si un movimiento es válido para la torre
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);

        // La torre puede moverse vertical u horizontalmente, pero no en diagonal
        return (deltaX == 0 || deltaY == 0);
    }

    // Método para mover la torre a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY) {
        if (esMovimientoValido(nuevaX, nuevaY)) {
            x = nuevaX;
            y = nuevaY;
            primerMovimiento = false;
            System.out.println("La torre se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para la torre");
        }
    }

    // Método para verificar si la torre ha sido amenazada (simulado para este ejemplo)
    public boolean haSidoAmenazada() {
        // Simula la verificación de si la torre está en jaque
        return false;
    }

    // Método para verificar si es el primer movimiento de la torre
    public boolean esPrimerMovimiento() {
        return primerMovimiento;
    }

    // Método para obtener la posición en el eje X de la torre
    public int getX() {
        return x;
    }

    // Método para establecer la posición en el eje X de la torre
    public void setX(int x) {
        this.x = x;
    }
}
