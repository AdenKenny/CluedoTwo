package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.GameOfCluedo;

public class Frame extends JFrame {

	Canvas canvas;
	Mouse mouse;
	JPanel buttonPanel;
	JButton handButton;

	public Frame() {
		super("Cluedo");

		this.canvas = new Canvas();

		setPreferredSize(new Dimension(1000, 790));
		setLayout(new BorderLayout()); // use border layout

		add(this.canvas, BorderLayout.CENTER); // add canvas

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createButtonPanel();

		this.handButton = new JButton("View hand");
		this.handButton.setLocation(900, 600);
		this.handButton.setSize(150, 150);

		this.buttonPanel.add(this.handButton);

		this.add(this.buttonPanel, BorderLayout.EAST);
		this.setJMenuBar(new MenuBar(this).getBar());

		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable

		setVisible(true);

		this.mouse = new Mouse(); //Mouse stuff.
		addMouseListener(this.mouse);


	}

	public void addGame(GameOfCluedo cluedo) {
		this.canvas.addBoard(cluedo.getBoard());
		this.mouse.addGame(cluedo);
	}

	/**
	 * Creates the panel on which buttons and other game info are displayed.
	 */

	private void createButtonPanel() {

		this.buttonPanel = new JPanel();
		this.buttonPanel.setSize(300, 500);
		this.buttonPanel.setLocation(700, 600);

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

	public void showPopup(String message) {
		JOptionPane.showConfirmDialog(this, new JLabel(message),
				null, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new Frame();
	}

}

