package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cluedo.Cluedo;

public class Frame extends JFrame {

	Canvas canvas;

	public Frame(Cluedo cluedo) {
		super("Cluedo");
		this.canvas = new Canvas(cluedo.getBoard());
		setPreferredSize(new Dimension(1000, 790));
		setLayout(new BorderLayout()); // use border layout
		add(this.canvas, BorderLayout.CENTER); // add canvas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable
		setVisible(true);
		Mouse mouse = new Mouse(cluedo);
		addMouseListener(mouse);
	}

	public Object askOptions(String message, Object[] options) {
		return JOptionPane.showInputDialog(null,
				message, null,
				JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
	}

	public String askText(String message) {
		return JOptionPane.showInputDialog(message);
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public void showMessage(String message) {
		JOptionPane.showConfirmDialog(this, new JLabel(message),
				null, JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
}
