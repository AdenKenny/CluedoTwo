package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.GameOfCluedo;

public class Frame extends JFrame {

	private Canvas canvas;
	private Mouse mouse;
	private JPanel buttonPanel;

	boolean gameStarted;

	public Frame() {
		super("Cluedo");

		this.canvas = new Canvas();

		setPreferredSize(new Dimension(950, 790));
		setLayout(new BorderLayout()); // use border layout

		add(this.canvas, BorderLayout.CENTER); // add canvas

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createButtonPanel();

		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable

		setVisible(true);

		this.mouse = new Mouse(); //Mouse stuff.
		addMouseListener(this.mouse);


	}

	public void addGame() {
		GameOfCluedo cluedo = new GameOfCluedo();
		this.canvas.addBoard(cluedo.getBoard());
		this.mouse.addGame(cluedo);
		this.gameStarted = true;
	}

	/**
	 * Creates the panel on which buttons and other game info are displayed.
	 */

	private void createButtonPanel() {
		this.buttonPanel = new JPanel();
		this.buttonPanel.setSize(300, 500);
		this.buttonPanel.setLocation(700, 600);

		JButton handButton = new JButton("View hand");
		handButton.setLocation(900, 600);
		handButton.setSize(150, 150);
		handButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Frame.this.gameStarted) {
					return;
				}

			}
		});
		this.buttonPanel.add(handButton);

		this.add(this.buttonPanel, BorderLayout.EAST);
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

