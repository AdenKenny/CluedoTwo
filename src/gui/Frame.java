package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import cluedo.Board;

public class Frame extends JFrame {

	Canvas canvas;

	public Frame(Board board) {
		super("Cluedo");
		this.canvas = new Canvas(board);
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout()); // use border layout
		add(canvas, BorderLayout.CENTER); // add canvas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable
		setVisible(true);
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
