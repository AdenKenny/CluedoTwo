package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cluedo.Cluedo;

public class Frame extends JFrame {

	Canvas canvas;

	public Frame(Cluedo cluedo) {
		super("Cluedo");

		//get the number of players
		Object[] possibleNumber = { 3, 4, 5, 6 };
		Object selectedNumber = JOptionPane.showInputDialog(null,
				"How many players?", "Number of Players",
				JOptionPane.INFORMATION_MESSAGE, null,
				possibleNumber, possibleNumber[0]);
		if (selectedNumber == null) {
			System.exit(0);
		}
		selectedNumber = 0;

		this.canvas = new Canvas();
		setPreferredSize(new Dimension(1000, 790));
		setLayout(new BorderLayout()); // use border layout
		add(canvas, BorderLayout.CENTER); // add canvas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable
		setVisible(true);
		Mouse mouse = new Mouse(cluedo);
		addMouseListener(mouse);
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
