package ajedrez;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuInicio();
        });
        
    }
}
