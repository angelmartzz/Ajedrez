package ajedrez;

public class Reina {
    int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y

    public Reina(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para validar si un movimiento es válido para la reina
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);

        // La reina puede moverse en línea recta (horizontal o vertical) o en diagonal
        return (deltaX == deltaY || x == nuevaX || y == nuevaY);
    }

    // Método para mover la reina a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY) {
        if (esMovimientoValido(nuevaX, nuevaY)) {
            x = nuevaX;
            y = nuevaY;
            System.out.println("La reina se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para la reina");
        }
    }
}
