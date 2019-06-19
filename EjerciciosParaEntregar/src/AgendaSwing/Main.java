package AgendaSwing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener, KeyListener, WindowListener {

	private static final long serialVersionUID = 1L;

	private Contacts contacts = new Contacts();
	private JTextField cmd;
	private JTextArea textArea;
	private JFileChooser fileChooser = new JFileChooser();

	public Main() throws IOException {
		super("Lixo da Agenda");
		setIconImage(ImageIO.read(getClass().getResource("/img/Open file.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();

		JButton load = new JButton(new ImageIcon(getClass().getResource("/img/Open file.png")));
		load.setActionCommand("LOAD");
		load.addActionListener(this);
		toolBar.add(load);

		JButton save = new JButton(new ImageIcon(getClass().getResource("/img/Save.png")));
		save.setActionCommand("SAVE");
		save.addActionListener(this);
		toolBar.add(save);

		JButton saveAs = new JButton(new ImageIcon(getClass().getResource("/img/Save as.png")));
		saveAs.setActionCommand("SAVEAS");
		saveAs.addActionListener(this);
		toolBar.add(saveAs);

		add(toolBar, BorderLayout.NORTH);

		textArea = new JTextArea(30, 80);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		add(textArea, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		cmd = new JTextField();
		cmd.addKeyListener(this);
		panel.add(cmd, BorderLayout.CENTER);

		JButton exec = new JButton(new ImageIcon(getClass().getResource("/img/Play.png")));
		exec.setActionCommand("EXEC");
		exec.addActionListener(this);
		panel.add(exec, BorderLayout.EAST);

		add(panel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		addWindowListener(this);
	}

	private void exec() {
		String result = contacts.exec(cmd.getText());
		if (result != null) {
			textArea.append(result + "\n");
		}
		cmd.setText("");
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			try {
				new Main().setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		cmd.requestFocus();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int respuesta = JOptionPane.showConfirmDialog(Main.this, "¿Estás Seguro?", "Cierre de la aplicación",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			exec();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("LOAD")) {
			load();
		} else if (e.getActionCommand().equals("SAVE")) {
			save();
		} else if (e.getActionCommand().equals("SAVEAS")) {
			saveAs();
		} else if (e.getActionCommand().equals("EXEC")) {
			exec();
		}
	}

	private void load() {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			contacts.load(fileChooser.getSelectedFile());
	}

	private void save() {

	}

	private void saveAs() {

	}

}