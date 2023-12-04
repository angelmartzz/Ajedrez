package ajedrez;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

class MenuInicio extends JFrame {

	public MenuInicio() {
        super("Bienvenido al Ajedrez");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear un panel con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Agregar un JLabel con la imagen al fondo
        ImageIcon imagenFondo = new ImageIcon("ajedrez_foto.png"); // Reemplaza con la ruta de tu imagen
        JLabel fondoLabel = new JLabel(imagenFondo);
        panel.add(fondoLabel, BorderLayout.CENTER);

        // Crear un panel para los botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false); // Hacer que el panel de los botones sea transparente

        // Configurar el resto del código para los botones (como en el ejemplo anterior)
        JLabel tituloLabel = new JLabel("Ajedrez Maestro");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
       // tituloLabel.setForeground(Color.WHITE); // Configurar el color del texto

        JButton nuevoJuegoButton = new JButton("Nuevo Juego");
        JButton salirButton = new JButton("Salir");

        nuevoJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new Tablero();
                });
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar componentes al panel de botones
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        botonesPanel.add(tituloLabel);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        botonesPanel.add(nuevoJuegoButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesPanel.add(salirButton);
        botonesPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Agregar el panel de botones al panel principal
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Agregar el panel principal al JFrame
        add(panel);

        // Hacer visible
        setVisible(true);
    }
}
