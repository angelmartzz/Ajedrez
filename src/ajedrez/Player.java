package ajedrez;

import java.util.Scanner;


public class Player {
	private String name_player="";
	private Color color;
	/*
	 * constructor, te pregunta el nombre y color
	 */
	public Player () {
		String tmp;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre del jugador: ");
		this.name_player= scanner.nextLine();

		do {
			System.out.println("Blancas o negras (b/n): ");
			tmp=new String(scanner.next());
			if ((tmp=="b")||(tmp=="B")) this.color=color.Blanco;
			else this.color=color.Negro;
		}while(!((tmp=="b")||(tmp=="B")||(tmp=="n")||(tmp=="N")));

	}
}
