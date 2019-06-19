package Ahorcado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class Juego extends JPanel implements ActionListener {

	private Lienzo lienzo;
	private static String letras = "abcdefghijklmn�opqrstuvwxyz";
	private static Font font;

	// Botones
	JButton BotonJugar = new JButton("jugar");
	JLabel TextoPalabra = new JLabel("");

	// Letras y Palabra
	JButton[] LetraPalabra = new JButton[letras.length()];
	static String palabra;

	static String cadena;

	JLabel jl1 = new JLabel();

	JTextArea textArea;

	JPanel anadir;
	static int fallos = 0;
	static int aciertos = 0;
	static char[] NumeroLetras;
	static char[] NumeroDeGuiones;

	Juego(Lienzo lienzo) throws FontFormatException, IOException {
		lienzo.reset();

		// lienzo
		this.lienzo = lienzo;
		InputStream in = getClass().getResourceAsStream("/font.ttf");
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		setLayout(new BorderLayout());

		// a�adir
		JPanel sup = new JPanel(new GridLayout(1, 1));
		sup.add(TextoPalabra);

		BotonJugar.setBackground(Color.white);
		TextoPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20),
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
						BorderFactory.createEmptyBorder(20, 20, 20, 20))));
		TextoPalabra.setFont(font);
		TextoPalabra.setHorizontalAlignment(JLabel.CENTER);
		TextoPalabra.setText("PULSE JUGAR PARA COMENZAR LA PARTIDA");

		JPanel inf = new JPanel(new GridLayout(4, 7));
		inf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20),
				BorderFactory.createBevelBorder(BevelBorder.RAISED)));

		// Pulsar cada tecla
		for (int i = 0; i < letras.length(); i++) {
			LetraPalabra[i] = new JButton(letras.substring(i, i + 1));
			LetraPalabra[i].addActionListener(this);
			LetraPalabra[i].setEnabled(false);
			LetraPalabra[i].setFont(font);
			inf.add(LetraPalabra[i]);
		}

		inf.add(BotonJugar);

		BotonJugar.addActionListener(this);

		add(sup, BorderLayout.CENTER);
		add(inf, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		BotonJugar.setEnabled(false);

		if (action.equals("jugar")) {
			try {
				palabra = PalabraAleatoria();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			NumeroLetras = palabra.toCharArray();
			NumeroDeGuiones = new char[NumeroLetras.length];
			cadena = " ";
			for (JButton b : LetraPalabra)
				b.setEnabled(true);
			BotonJugar.setBackground(Color.GRAY);
			for (int i = 0; i < NumeroLetras.length; i++) {
				NumeroDeGuiones[i] = '_';
				cadena += (NumeroDeGuiones[i] = '_') + " ";
				TextoPalabra.setText(cadena);

			}

		} else {

			char c = action.charAt(0);
			boolean fallo = true;
			for (int i = 0; i < NumeroLetras.length; i++) {
				if (c == NumeroLetras[i]) {
					NumeroDeGuiones[i] = c;
					TextoPalabra.setText(String.valueOf(NumeroDeGuiones));
					fallo = false;
				}
			}
			if (fallo)
				if (lienzo.incFallos()) {
					JOptionPane.showMessageDialog(lienzo, "HAS PERDIDO, FIN DEL JUEGO");
					for (JButton b : LetraPalabra)
						b.setEnabled(false);
					BotonJugar.setEnabled(true);
					BotonJugar.setBackground(Color.white);
					TextoPalabra.setText("PULSE JUGAR PARA COMENZAR LA PARTIDA");
					lienzo.reset();
				}
		}

	}

	public String PalabraAleatoria() throws IOException {
		String palabra = "";
		ArrayList<String> palabras = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(Juego.class.getResourceAsStream("/palabras.txt")));
		Random r = new Random();

		String linea = " ";
		while ((linea = br.readLine()) != null) {
			palabras.add(linea);
		}

		int palabrarandom = r.nextInt((palabras.size() - 1));
		palabra = palabras.get(palabrarandom);

		return palabra;

	}

}