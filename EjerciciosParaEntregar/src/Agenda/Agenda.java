package Agenda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.*;

@SuppressWarnings("unused")
public class Agenda {

	private Map<String, String> agenda = new HashMap<String, String>();
	private String part1;
	private String part2;

	public void Introducir(String nombre, String numero) {

		if (!agenda.containsKey(nombre)) {
			agenda.put(nombre, numero);
			System.out.println("El Contacto se ha guardado en la Agenda Correctamente");
			System.out.println();
			nombre = null;
			numero = null;
		} else {
			agenda.put(nombre, numero);
			System.out.println("El Contacto ha sido Actualizado");
			System.out.println();
			nombre = null;
			numero = null;
		}
	}

	public void buscar(String bus, String nombre) {
		if (agenda.containsKey(nombre)) {
			System.out.println(nombre + " -> " + agenda.get(nombre));
			System.out.println();
			bus = null;
			nombre = null;
		} else if (!agenda.containsKey(nombre)) {
			System.out.println("El Contacto no Existe");
			System.out.println();
			nombre = null;
			bus = null;
		}
	}

	public void borrare(String borrar, String nombre) {
		String contactos;
		if (agenda.containsKey(nombre)) {
			contactos = agenda.get(nombre);
			agenda.remove(nombre);
			System.out.println("Contacto Eliminado:" + nombre + "->" + contactos);
			borrar = null;
			nombre = null;
		} else {
			System.out.println("El Contacto no se Encuentra en la Agenda");
			borrar = null;
			nombre = null;
		}
	}

	public void guardar(String guardar, String ruta) {
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"));
			for (Map.Entry<String, String> entry : agenda.entrySet()) {
				try {
					out.write(entry.getKey() + " - " + entry.getValue() + "\n");
					System.out.println(
							"El Contacto " + entry.getKey() + " - " + entry.getValue() + " ha sido Guardado con Exito");
					guardar = null;
					ruta = null;
				} catch (IOException ex) {
					System.out.println("Excepcion: " + ex.getMessage());
				}
			}
		} catch (UnsupportedEncodingException | FileNotFoundException ex2) {
			System.out.println("Error: " + ex2.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException ex3) {
				System.out.println("Error: " + ex3.getMessage());
			}
		}
	}

	public void cargar(String nombre, String ruta) {
		File fichero = new File("./fichero_Agenda.txt");
		Scanner s = null;
		int pregunta = 0;
		Scanner r = new Scanner(System.in);
		fichero = new File(ruta);
		nombre = null;
		ruta = null;
		try {
			s = new Scanner(fichero);
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				String[] partes2 = linea.split("-");
				String nombre2 = partes2[0].trim();
				String numero2 = partes2[1].trim();
				if (!agenda.containsKey(nombre2)) {
					agenda.put(nombre2, numero2);
					System.out.println("El Contacto se ha Cargado Correctamente en la Agenda Desde el Fichero");
					nombre2 = null;
					numero2 = null;
				} else {
					System.out.println("El Contacto ya Esta en la Agenda");
					System.out.println("¿Qué desea hacer? Escriba el Numero de la Orden");
					System.out.println("1. Desea guardar " + nombre2 + "-" + agenda.get(nombre2));
					System.out.println("2. Desea guardar " + nombre2 + "-" + numero2);
					System.out.print("> ");
					pregunta = r.nextInt();
					if (pregunta == 1) {
						System.out.println("El Contacto de la Agenda ha sido Guardado con Exito");
						nombre2 = null;
						numero2 = null;
					} else if (pregunta == 2) {
						agenda.put(nombre2, numero2);
						nombre2 = null;
						numero2 = null;
						System.out.println("Se ha Sobrescrito el Numero en la Agenda");
					} else {
						do {
							System.out.println("El Numero ya esta en la Agenda");
							System.out.println("¿Que desea hacer? Escriba el Numero de la Orden");
							System.out.println("1. Desea Guardar " + nombre2 + "-" + agenda.get(nombre2));
							System.out.println("2. Desea Guardar " + nombre2 + "-" + numero2);
							System.out.println("Se Esperaba un 1 o un 2");
							System.out.print("> ");
							pregunta = r.nextInt();
							if (pregunta == 1 || pregunta == 2) {
								break;
							}
						} while (pregunta != 2 || pregunta != 1);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
	}

}