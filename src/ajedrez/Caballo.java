package ajedrez;

public class Caballo {
    int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y

    public Caballo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para validar si un movimiento es válido para el caballo
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);

        // El caballo se mueve en forma de "L": dos casillas en una dirección y una casilla en otra dirección
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }

    // Método para mover el caballo a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY) {
        if (esMovimientoValido(nuevaX, nuevaY)) {
            x = nuevaX;
            y = nuevaY;
            System.out.println("El caballo se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para el caballo");
        }
    }
}
