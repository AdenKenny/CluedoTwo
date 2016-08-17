package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import cluedo.Cluedo;

public class Frame extends JFrame {

	Canvas canvas;

	public Frame(Cluedo cluedo) {
		super("Cluedo");
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
