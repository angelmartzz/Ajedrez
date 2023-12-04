package ajedrez;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Representa un tablero de ajedrez con sus piezas y reglas asociadas.
 */
public class Tablero extends JFrame{


	private JPanel tableroPanel;

	public Tablero() { 

		super("Tablero de Ajedrez");
		initializeBoard();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);

		// Crear el panel del tablero
		tableroPanel = new JPanel(new GridLayout(8, 8));

		// Colores para los escaques
		java.awt.Color blanco = new java.awt.Color(250, 240, 230);
		java.awt.Color negro = new java.awt.Color(75, 75, 75);

		// Iterar a través de las filas y columnas para agregar los escaques al panel
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				JPanel escaque = crearEscaque(fila, columna);

				Pieza pieza = getPiece(fila, columna);
				if (pieza != null) {
					JLabel labelPieza = crearLabelPieza(pieza);
					escaque.add(labelPieza);
				}
				escaque.setBackground((fila + columna) % 2 == 0 ? blanco : negro);
				tableroPanel.add(escaque);
			}
		}

		// Agregar el panel del tablero a la ventana
		add(tableroPanel);

		// Hacer visible la ventana
		setVisible(true);
	}
	public Pieza[][] tablero;
	ArrayList<Pieza> almacen = new ArrayList<Pieza>(2);


	private Color turnoActual = Color.Blanco;
	private boolean enroqueBlancoCorto = true;
	private boolean enroqueBlancoLargo = true;
	private boolean enroqueNegroCorto = true;
	private boolean enroqueNegroLargo = true;

	private boolean enroqueCorto(Color color, Pieza rey, Pieza torre) {
		// Verificar si el enroque corto está permitido para el color especificado
		if (color == Color.Blanco && enroqueBlancoCorto) {
			setPiece(7, 4, null);
			setPiece(7, 0, null);
			setPiece(7, 5, torre);
			setPiece(7, 6, rey);

			enroqueBlancoCorto=false;
		} else if (color == Color.Negro && enroqueNegroCorto) {

			setPiece(0, 4, null);
			setPiece(0, 0, null);
			setPiece(0, 5, torre);
			setPiece(0, 6, rey);
			enroqueNegroCorto=false;

		}
		return true;
	}

	private boolean enroqueLargo(Color color, Pieza rey, Pieza torre) {
		// Verificar si el enroque largo está permitido para el color especificado
		if (color == Color.Blanco && enroqueBlancoLargo) {
			setPiece(7, 4, null);
			setPiece(7, 0, null);
			setPiece(7, 3, torre);
			setPiece(7, 2, rey);
			enroqueBlancoLargo=false;

		} else if (color == Color.Negro && enroqueNegroLargo) {
			setPiece(0, 4, null);
			setPiece(0, 0, null);
			setPiece(0, 3, torre);
			setPiece(0, 2, rey);
			enroqueNegroLargo=false;
		}
		return true;
	}

	/**
	 * Inicializa el tablero de ajedrez con las piezas en su posición inicial.
	 */
	private void initializeBoard() {
		tablero = new Pieza[8][8];


		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tablero[i][j]=null;
			}
		}

		tablero[0][0] = new Torre(Color.Negro);
		tablero[0][7] = new Torre(Color.Negro);
		tablero[7][0] = new Torre(Color.Blanco);
		tablero[7][7] = new Torre(Color.Blanco);

		tablero[0][1] = new Caballo(Color.Negro);
		tablero[0][6] = new Caballo(Color.Negro);
		tablero[7][1] = new Caballo(Color.Blanco);
		tablero[7][6] = new Caballo(Color.Blanco);

		tablero[0][2] = new Alfil(Color.Negro);
		tablero[0][5] = new Alfil(Color.Negro);
		tablero[7][2] = new Alfil(Color.Blanco);
		tablero[7][5] = new Alfil(Color.Blanco);

		tablero[0][3] = new Reina(Color.Negro);
		tablero[7][3] = new Reina(Color.Blanco);

		tablero[0][4] = new Rey(Color.Negro);
		tablero[7][4] = new Rey(Color.Blanco);

		for (int col = 0; col < 8; col++) {
			tablero[1][col] = new Peon(Color.Negro);
			tablero[6][col] = new Peon(Color.Blanco);
		}


	}


	/**
	 * Obtiene la pieza en una posición específica del tablero.
	 *
	 * @param fil Fila de la casilla.
	 * @param col Columna de la casilla.
	 * @return La pieza en la casilla o null si no hay ninguna pieza.
	 */
	public Pieza getPiece(int fil, int col) {
		return tablero[fil][col];

	}

	/**
	 * Coloca una pieza en una posición específica del tablero.
	 *
	 * @param fil   Fila de la casilla.
	 * @param col   Columna de la casilla.
	 * @param piece Pieza a colocar en la casilla.
	 */
	public void setPiece(int fil, int col, Pieza piece) {
		if (dentroTablero(fil, col)) {
			// Verificar si se trata de un peón que alcanza el extremo del tablero para promoverlo
			if (piece instanceof Peon && (fil == 0 || fil == 7)) {
				// Preguntar al usuario por la pieza de promoción
				String[] opciones = {"Reina", "Torre", "Caballo", "Alfil"};
				int seleccion = JOptionPane.showOptionDialog(null, "Seleccione la pieza de promoción:","Promoción de Peón", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

				// Crear la nueva pieza según la selección del usuario
				switch (seleccion) {
				case 0:
					piece = new Reina(piece.getColor());
					break;
				case 1:
					piece = new Torre(piece.getColor());
					break;
				case 2:
					piece = new Caballo(piece.getColor());
					break;
				case 3:
					piece = new Alfil(piece.getColor());
					break;
				}
			}
			tablero[fil][col] = piece;
		}
	}

	/**
	 * Coloca una pieza en una posición específica del tablero y verifica si la operación fue exitosa.
	 *
	 * @param fil   Fila de la casilla.
	 * @param col   Columna de la casilla.
	 * @param piece Pieza a colocar en la casilla.
	 * @return true si la pieza se colocó con éxito, false de lo contrario.
	 */

	public boolean setPieceAsk(int fil, int col, Pieza piece) {
		if (dentroTablero(fil, col)) {
			tablero[fil][col] = piece;
		}
		return (tablero[fil][col] == piece);
	}


	boolean intentarMoverPieza(int fila, int columna, int filaDestino, int columnaDestino) {
		Pieza pieza = getPiece(filaDestino, columnaDestino);
		Pieza piezaSeleccionada = getPiece(fila, columna);
		// Verificar si el movimiento es válido
		if (piezaSeleccionada == null) return false;
		if (piezaSeleccionada.puedeMoverseAPosicion(fila, columna, filaDestino, columnaDestino) && piezaSeleccionada.getColor() == turnoActual) {
			if (piezaSeleccionada instanceof Peon) {
				int filaDiferencia = Math.abs(filaDestino - fila);
				int columnaDiferencia = Math.abs(columnaDestino - columna);

				int direccion = (piezaSeleccionada.getColor() == Color.Blanco) ? -1 : 1;

				// Paso normal
				if (filaDiferencia == 1 && columnaDiferencia == 0 && getPiece(filaDestino, columnaDestino) == null) {
					setPiece(filaDestino, columnaDestino, piezaSeleccionada);
					setPiece(fila, columna, null);
					return true;
				}

				// Primer paso doble
				if (filaDiferencia == 2 && columnaDiferencia == 0 && fila == (piezaSeleccionada.getColor() == Color.Blanco ? 6 : 1)) {
					// Verificar que no haya piezas en las casillas intermedias
					if (getPiece(fila + direccion, columna) == null && getPiece(filaDestino, columnaDestino) == null) {
						setPiece(filaDestino, columnaDestino, piezaSeleccionada);
						setPiece(fila, columna, null);
						return true;
					}
				}
				//comer en diagonal
				else if (filaDiferencia == 1 && columnaDiferencia == 1) {
					// Verificar si hay una pieza en la casilla destino
					if (getPiece(filaDestino, columnaDestino) != null && getPiece(filaDestino, columnaDestino).getColor() != piezaSeleccionada.getColor()) {
						// Verificar si la pieza en la casilla destino es del oponente
						// Capturar la pieza del oponente
						setPiece(filaDestino, columnaDestino, null);
						setPiece(filaDestino, columnaDestino, piezaSeleccionada);
						setPiece(fila, columna, null);
						return true;

					}
					// Comer al paso: Verificar si la casilla adyacente en la misma fila contiene un peón del oponente
					else if (getPiece(fila, columnaDestino) instanceof Peon && getPiece(fila, columnaDestino).getColor() != piezaSeleccionada.getColor()) {
						// Eliminar el peón del oponente en la casilla adyacente
						setPiece(fila, columnaDestino, null);
						setPiece(filaDestino, columnaDestino, piezaSeleccionada);
						setPiece(fila, columna, null);
						return true;
					}
				}
				return false;
			}

			else if((piezaSeleccionada instanceof Rey && pieza instanceof Torre) && piezaSeleccionada.getColor() == pieza.getColor() ) {
				if (columnaDestino > columna) {
					if (piezaSeleccionada.getColor()==Color.Blanco) {
						if (getPiece(7,6) == null && getPiece(7,5) == null) {					
							return enroqueCorto(piezaSeleccionada.getColor(), piezaSeleccionada, pieza );
						}else return false;
					}else{
						if (getPiece(0,6) == null && getPiece(0,5) == null) {					
							return enroqueCorto(piezaSeleccionada.getColor(), piezaSeleccionada, pieza );
						}else return false;
					}
				} else {
					// Enroque largo
					if (piezaSeleccionada.getColor()==Color.Blanco) {
						if (getPiece(7,1) == null || getPiece(7,2) == null || getPiece(7,3)==null) {					
							return enroqueLargo(piezaSeleccionada.getColor(), piezaSeleccionada, pieza );
						}else return false;
					}else{
						if (getPiece(0,1) == null || getPiece(0,2) == null || getPiece(0,3)==null) {					
							return enroqueLargo(piezaSeleccionada.getColor(), piezaSeleccionada, pieza );
						}else return false;
					}
				}
			}

			if (pieza != null && pieza.getColor() != piezaSeleccionada.getColor()) {
				// Capturar la pieza del oponente
				tablero[filaDestino][columnaDestino] = null;
				setPiece(filaDestino, columnaDestino, piezaSeleccionada);
				setPiece(fila, columna, null);
				return true;
			}
			else if (piezaSeleccionada != null && pieza == null) {
				setPiece(filaDestino, columnaDestino, piezaSeleccionada);
				setPiece(fila, columna, null);
				return true;
			}


		}	


		return false;
	}


	/**
	 * Imprime el tablero en la consola para visualizarlo.
	 */
	public void printBoard() {
		for (int fil = 0; fil < 8; fil++) {
			for (int col = 0; col < 8; col++) {
				Pieza piece = getPiece(fil, col);
				if (piece != null) {
					System.out.print(piece.toString() + " ");
				} else {
					System.out.print("  .  ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Verifica si una posición está dentro de los límites del tablero.
	 *
	 * @param fil Fila de la casilla.
	 * @param col Columna de la casilla.
	 * @return true si la posición está dentro de los límites del tablero, false en caso contrario.
	 */
	private boolean dentroTablero(int fil, int col) {
		return fil >= 0 && fil < 8 && col >= 0 && col < 8;
	}

	private void cambiarTurno() {
		turnoActual = (turnoActual == Color.Blanco) ? Color.Negro : Color.Blanco;
	}



	/**
	 * Verifica si el rey de un color específico está en jaque.
	 *
	 * @param color Color del rey.
	 * @return true si el rey está en jaque, false en caso contrario.
	 */


	public boolean esJaque(Color color) {
		// Encuentra la posición del rey del color especificado
		int filaRey = -1;
		int columnaRey = -1;

		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza instanceof Rey && pieza.getColor() == color) {
					filaRey = fila;
					columnaRey = columna;
					break;
				}
			}
			if (filaRey != -1) {
				break;
			}
		}

		// Comprueba si alguna pieza del oponente puede moverse y atacar al rey
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza != null && pieza.getColor() != color) {
					if (pieza.esMovimientoValido(fila, columna, filaRey, columnaRey)) {
						return true; // El rey está en jaque
					}
				}
			}
		}

		return false; // El rey no está en jaque
	}

	/**
	 * Verifica si el rey de un color específico está en jaque mate.
	 *
	 * @param color Color del rey.
	 * @return true si el rey está en jaque mate, false en caso contrario.
	 */

	public boolean esJaqueMate(Color color) {
		// Verificar si el rey está en jaque
		if (!esJaque(color)) {
			return false; // El rey no está en jaque mate si no está en jaque
		}

		boolean jaqueTemporal;
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				Pieza pieza = tablero[fila][columna];
				if (pieza != null && pieza.getColor() == color) {
					for (int filaDestino = 0; filaDestino < 8; filaDestino++) {
						for (int columnaDestino = 0; columnaDestino < 8; columnaDestino++) {
							if (setPieceAsk(filaDestino, columnaDestino, pieza)) {
								setPiece(filaDestino, columnaDestino, pieza);
								jaqueTemporal = esJaque(color);

								deshacerMovimiento(fila, columna, filaDestino, columnaDestino);

								if (!jaqueTemporal) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true; // El rey está en jaque mate
	}

	/**
	 * Deshace un movimiento realizado en el tablero.
	 *
	 * @param filaInicial    Fila inicial del movimiento.
	 * @param columnaInicial Columna inicial del movimiento.
	 * @param filaFinal      Fila final del movimiento.
	 * @param columnaFinal   Columna final del movimiento.
	 */
	public void deshacerMovimiento(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
		Pieza piezaMovida = tablero[filaFinal][columnaFinal];
		tablero[filaFinal][columnaFinal] = null; // La casilla de destino se vuelve nula
		tablero[filaInicial][columnaInicial] = piezaMovida; // La pieza vuelve a su posición original
	}

	public int getFil(Pieza pieza) {
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				if (tablero[fila][columna] == pieza) {
					return fila;
				}
			}
		}
		return -1; // La pieza no se encuentra en el tablero
	}

	public int getCol(Pieza pieza) {
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				if (tablero[fila][columna] == pieza) {
					return columna;
				}
			}
		}
		return -1; // La pieza no se encuentra en el tablero
	}
	// interfaz


	/**
	 * Interfaz gráfica para los escaques del tablero.
	 */

	private JPanel crearEscaque(int fila, int columna) {
		JPanel escaque = new JPanel();
		escaque.setPreferredSize(new Dimension(50, 50));
		escaque.addMouseListener(new EscaqueMouseListener(fila, columna));
		return escaque;
	}

	/**
	 * Clase interna para manejar eventos del ratón en los escaques del tablero.
	 */
	private class EscaqueMouseListener extends MouseAdapter {
		private int fila;
		private int columna;

		public EscaqueMouseListener(int fila, int columna) {
			this.fila = fila;
			this.columna = columna;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Pieza piezaSeleccionada = getPiece(fila, columna);

			if (almacen.isEmpty()) {
				// Si el almacen está vacío, guardar las coordenadas de la casilla
				almacen.add(piezaSeleccionada);
			} else {
				Pieza piezaAlmacenada = almacen.get(0); // Siempre hay solo una pieza almacenada

				if (intentarMoverPieza(getFil(piezaAlmacenada), getCol(piezaAlmacenada), fila, columna)) {
					System.out.println("Movimiento exitoso");
					mostrarTablero();
					cambiarTurno();


					// Verificar si el rey está en jaque después del movimiento
					if (!esJaque(piezaAlmacenada.getColor())) {
			            // Cambiar el turno
			            mostrarTablero();
			        } else {
			            // Deshacer el movimiento si el rey está en jaque
			            deshacerMovimiento(getFil(piezaAlmacenada), getCol(piezaAlmacenada), fila, columna);
			        }
					
					if (esJaqueMate(turnoActual)) {
						System.out.println("Juego Terminado \n EL Ganador es:" + piezaAlmacenada.getColor());
					}
				}

				almacen.clear(); // Limpiar almacen, ya sea después de mover o no
			}
		}
	}



	/**
	 * Crea un JLabel para mostrar la imagen de una pieza.
	 *
	 * @param pieza Pieza de ajedrez.
	 * @return JLabel con la imagen de la pieza.
	 */
	private JLabel crearLabelPieza(Pieza pieza) {
		JLabel labelPieza = new JLabel();
		String rutaImagenes;
		String directorioProyecto = System.getProperty("user.dir");
		rutaImagenes = directorioProyecto + File.separator + "imagenes_piezas" + File.separator;
		try {
			String nombreArchivo = obtenerNombreArchivoImagen(pieza);
			String colour;
			if (pieza.getColor()==Color.Blanco) colour="b";
			else colour="n";			
			BufferedImage imagen = ImageIO.read(new File(rutaImagenes + nombreArchivo + colour + ".png"));
			ImageIcon icono = new ImageIcon(imagen);
			labelPieza.setIcon(icono);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return labelPieza;
	}

	/**
	 * Obtiene el nombre del archivo de imagen correspondiente a una pieza.
	 *
	 * @param pieza Pieza de ajedrez.
	 * @return Nombre del archivo de imagen.
	 */
	private String obtenerNombreArchivoImagen(Pieza pieza) {
		// Supongamos que las imágenes se llaman como las clases de piezas, en minúsculas.

		return pieza.getClass().getSimpleName().toLowerCase() ;
	}




	public void mostrarTablero() {
		SwingUtilities.invokeLater(() -> {
			// Obtener el número total de componentes en el panel
			int componentCount = tableroPanel.getComponentCount();

			java.awt.Color blanco = new java.awt.Color(250, 240, 230);
			java.awt.Color negro = new java.awt.Color(75, 75, 75);

			// Iterar a través de las filas y columnas para actualizar los escaques modificados
			for (int fila = 0; fila < 8; fila++) {
				for (int columna = 0; columna < 8; columna++) {
					int index = fila * 8 + columna; // Calcular el índice del componente en el panel
					JPanel escaque;

					// Verificar si el componente ya existe en el panel
					if (index < componentCount) {
						escaque = (JPanel) tableroPanel.getComponent(index);
						escaque.removeAll(); // Limpiar el escaque
					} else {
						// Si no existe, crear un nuevo escaque
						escaque = crearEscaque(fila, columna);
						tableroPanel.add(escaque);
					}

					Pieza pieza = getPiece(fila, columna);
					if (pieza != null) {
						JLabel labelPieza = crearLabelPieza(pieza);
						escaque.add(labelPieza);
					}
					escaque.setBackground((fila + columna) % 2 == 0 ? blanco : negro);
				}
			}

			tableroPanel.validate();
			tableroPanel.repaint();
		});
	}


	//bot
	public void realizarMovimientoExterno(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
		// Agrega lógica para validar y realizar el movimiento externo
		// Puedes reutilizar o llamar a tu método existente intentarMoverPieza o algo similar
		intentarMoverPieza(filaOrigen, colOrigen, filaDestino, colDestino);

		// Cambiar el turno después del movimiento externo
		cambiarTurno();
		mostrarTablero();
	}

}

