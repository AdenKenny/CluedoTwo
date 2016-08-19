package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;



import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.GameOfCluedo;

public class Frame extends JFrame {

	private Canvas canvas;
	private Mouse mouse;

	private JPanel buttonPanel;
	private Audio audio;


	GameOfCluedo cluedo;

	public Frame() {
		super("Cluedo");

		this.canvas = new Canvas();
		this.audio = new Audio();

		setPreferredSize(new Dimension(810, 835));
		setLayout(new BorderLayout()); // use border layout

		add(this.canvas, BorderLayout.CENTER); // add canvas

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setJMenuBar(new MenuBar(this).getBar());

		createButtonPanel();

		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable

		setVisible(true);

		this.mouse = new Mouse(); //Mouse stuff.
		addMouseListener(this.mouse);



	}

	public void newGame() {

		this.cluedo = new GameOfCluedo(this);
		this.canvas.addBoard(this.cluedo.getBoard());
		this.cluedo.setupPlayers();
		this.mouse.addGame(this.cluedo);
		this.cluedo.startGame();

	}

	/**
	 * Creates the panel on which buttons and other game info are displayed.
	 */

	private void createButtonPanel() {
		Box box = Box.createHorizontalBox();

		JButton handButton = new JButton("View hand");
		handButton.setMaximumSize(new Dimension(200, 30));
		handButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}
				Frame.this.cluedo.showHand();
			}
		});
		box.add(handButton);

		JButton suggestionButton = new JButton("Suggestion");
		suggestionButton.setMaximumSize(new Dimension(200, 30));
		suggestionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}
				Frame.this.cluedo.suggestion();
			}
		});
		box.add(suggestionButton);

		JButton accusationButton = new JButton("Accusation");
		accusationButton.setMaximumSize(new Dimension(200, 30));
		accusationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}
				Frame.this.cluedo.accusation();
			}
		});
		box.add(accusationButton);

		JButton endTurnButton = new JButton("End Turn");
		endTurnButton.setMaximumSize(new Dimension(200, 30));
		endTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}
				Frame.this.cluedo.nextTurn();
			}
		});
		box.add(endTurnButton);

		this.add(box, BorderLayout.SOUTH);
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
		/*
		JOptionPane.showConfirmDialog(this, new JLabel(message),
				null, JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE);
				*/
		JOptionPane pane = new JOptionPane(message);
		JDialog dialog = pane.createDialog(this, "Message");
		dialog.setVisible(true);
	}

	public void muteAudio() {
		this.audio.muteAudio();
	}

	public void unmuteAudio() {
		this.audio.unmuteAudio();
	}

	public static void main(String[] args) {
		new Frame();
	}

}

