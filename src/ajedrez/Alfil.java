package ajedrez;

public class Alfil {
	int x; // Posición actual en el eje X
    int y; // Posición actual en el eje Y
    
    public Alfil(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Método para validar si un movimiento es válido para el alfil
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Calcula la diferencia en las coordenadas X e Y
        int deltaX = Math.abs(nuevaX - x);
        int deltaY = Math.abs(nuevaY - y);
        
        // El movimiento es válido si la diferencia es la misma en ambos ejes y no es un movimiento nulo
        return deltaX == deltaY && deltaX != 0;
    }
    
    // Método para mover el alfil a una nueva posición si el movimiento es válido
    public void mover(int nuevaX, int nuevaY) {
        if (esMovimientoValido(nuevaX, nuevaY)) {
            x = nuevaX;
            y = nuevaY;
            System.out.println("El alfil se ha movido a la posición (" + x + ", " + y + ")");
        } else {
            System.out.println("Movimiento inválido para el alfil");
        }
    }
}
