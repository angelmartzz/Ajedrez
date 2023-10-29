package ajedrez;

public class Peon {
    int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y
    
    public Peon(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Método para validar si un movimiento es válido para el peón
    public boolean esMovimientoValido(int nuevaX, int nuevaY, boolean primerMovimiento) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = nuevaY - y;
        
        // Si es el primer movimiento, el peón puede avanzar 1 o 2 casillas hacia adelante
        if (primerMovimiento) {
            return (deltaX == 0 && (deltaY == 1 || deltaY == 2));
        }
        
        // En movimientos subsiguientes, el peón solo puede avanzar 1 casilla hacia adelante
        return (deltaX == 0 && deltaY == 1);
    }
    
    // Método para mover el peón a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY, boolean primerMovimiento) {
        if (esMovimientoValido(nuevaX, nuevaY, primerMovimiento)) {
            x = nuevaX;
            y = nuevaY;
            System.out.println("El peón se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para el peón");
        }
    }
    
    // Método para realizar el movimiento especial del peón al paso
    public void moverAlPaso(int nuevaX, int nuevaY) {
        if (nuevaY == y + 1 && Math.abs(nuevaX - x) == 1) {
            x = nuevaX;
            y = nuevaY;
            System.out.println("El peón ha realizado el movimiento al paso a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento al paso inválido para el peón");
        }
    }
}
