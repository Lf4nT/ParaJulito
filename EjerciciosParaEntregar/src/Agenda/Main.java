package Agenda;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String teclado;
		Scanner r = new Scanner(System.in);
		String[] partes = new String[1];
		Agenda agenda = new Agenda();
		do {
			System.out.println("Para Introducir Nombre -> nombre-telefono");
			System.out.println("Para Buscar un Numero -> buscar:nombre");
			System.out.println("Para Borrar un Numero -> borrar:numero");
			System.out.println("Para Guardar la Agenda -> guardar:ruta");
			System.out.println("Para Cargar la Agenda -> cargar:ruta");
			System.out.println("Para Salir de la Agenda -> fin");

			System.out.print("> ");
			teclado = r.next();

			if (teclado.contains("-")) {
				partes = teclado.split("-");
				agenda.Introducir(partes[0], partes[1]);
				partes = null;

			} else if (teclado.contains("buscar:")) {
				partes = teclado.split(":");
				agenda.buscar(partes[0], partes[1]);
				partes = null;

			} else if (teclado.contains("borrar:")) {
				partes = teclado.split(":");
				agenda.borrare(partes[0], partes[1]);
				partes = null;

			} else if (teclado.contains("guardar:")) {
				partes = teclado.split(":");
				agenda.guardar(partes[0], partes[1]);
				partes = null;

			} else if (teclado.contains("cargar:")) {
				partes = teclado.split(":");
				agenda.cargar(partes[0], partes[1]);
				partes = null;

			} else if (!teclado.contains("fin")) {
				System.out.println("Accion Incorrecta");

			}

		} while (!teclado.equals("fin"));
	}

}