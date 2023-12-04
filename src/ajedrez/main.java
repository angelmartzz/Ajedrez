package ajedrez;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase principal que inicia la aplicación de ajedrez.
 */
public class main {

    /**
     * Método principal que inicia la aplicación creando una instancia de la clase MenuInicio.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuInicio();
        });
    }
}
