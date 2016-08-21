package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cluedo.GameOfCluedo;


/**
 * A class that holds the main Swing elements of the game of Cluedo.
 * This calls the 'GameOfCluedo' class which does the game logic.
 * The graphical user interface is done in this class.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Frame extends JFrame {
	
	public static final int BOARD_LEFT = 30;
	
	public static final int NUM_SQUARES_HORIZONTAL = 24;
	public static final int NUM_SQUARES_VERTICAL = 25;

	public static final double SQUARE_WIDTH = 31.2;
	public static final double SQUARE_HEIGHT = 29.6;
	
	public static final int BOARD_RIGHT = (int) (NUM_SQUARES_HORIZONTAL * SQUARE_WIDTH) + BOARD_LEFT;

	private Canvas canvas; //The canvas on which the board is drawn.
	private Mouse mouse; //Handles mouse inputs for movement.
	private Audio audio; //Handles the audio for the game.

	GameOfCluedo cluedo; //Cluedo class that handles game logic.

	public Frame() {
		super("Cluedo");

		this.canvas = new Canvas();
		this.audio = new Audio();

		setPreferredSize(new Dimension(806, 835));
		setLayout(new BorderLayout());

		add(this.canvas, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setJMenuBar(new MenuBar(this).getBar()); //Create menu bar.

		createButtonPanel(); //Sets up buttons down the bottom.
		
		pack();
		setResizable(false); // Prevents frame from being resizeable.

		setVisible(true);

		this.mouse = new Mouse(); // Mouse stuff.
		addMouseListener(this.mouse); //Adds the mouse listener to the mouse class.

	}
	
	public void mockGame() {
		this.cluedo = new GameOfCluedo(this); //Creates all the requirements for a new game.
		this.canvas.addBoard(this.cluedo.getBoard());
		this.mouse.addGame(this.cluedo);
		this.cluedo.mockGame();
	}

	/**
	 * Creates a new game of Cluedo. Used in the menu bar new game option.
	 */

	public void newGame() {

		this.cluedo = new GameOfCluedo(this); //Creates all the requirements for a new game.
		this.canvas.addBoard(this.cluedo.getBoard());

		this.cluedo.setupPlayers();

		if(this.cluedo == null) { //If there is an error setting up game.
			return;
		}

		this.mouse.addGame(this.cluedo);
		this.cluedo.startGame();

	}

	/**
	 * Ends the game of Cluedo. Called in cancelling opening windows (name input etc...)
	 * Or if a player wins.
	 */

	public void endGame() {
		this.cluedo = null;
		this.canvas.removeBoard();
		this.mouse.endGame();
	}

	/**
	 * Creates the panel on which buttons and other game info are displayed.
	 */

	private void createButtonPanel() {
		Box box = Box.createHorizontalBox();
		
		JButton rollDice = new JButton("Roll Dice");
		rollDice.setMaximumSize(new Dimension(160, 30));
		rollDice.setToolTipText("Roll the dice to see how far you can move");
		rollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}

				Frame.this.cluedo.rollDice();
			}
		});
		
		box.add(rollDice);
		
		JButton handButton = new JButton("View hand");
		handButton.setMaximumSize(new Dimension(160, 30));
		handButton.setToolTipText("Show your hand");
		handButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Frame.this.cluedo == null) {
					return;
				}

				Frame.this.showHand();
			}
		});

		box.add(handButton);

		JButton suggestionButton = new JButton("Suggestion");
		suggestionButton.setMaximumSize(new Dimension(160, 30));
		suggestionButton.setToolTipText("Make a suggestion (You must be in a room)");
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
		accusationButton.setMaximumSize(new Dimension(160, 30));
		accusationButton.setToolTipText("Make an accusation (If you get this wrong you lose)");
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
		endTurnButton.setMaximumSize(new Dimension(160, 30));
		endTurnButton.setToolTipText("End your turn");
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

	/**
	 * Creates the box on which the cards in a player's hand are displayed.
	 */

	@SuppressWarnings("unused")
	public void showHand() {

		String playerName = this.cluedo.getCurrentPlayer().getHandUserName();

		new HandBox(this, playerName + "'s hand", this.cluedo.showHand());
	}

	public static Object askOptions(String message, Object[] options) {
		return JOptionPane.showInputDialog(null,
				message, null,
				JOptionPane.INFORMATION_MESSAGE, null,
				options, options[0]);
	}

	public static String askText(String message) {
		return JOptionPane.showInputDialog(message);
	}
	

	/**
	 * Returns the canvas that is displayed on this frame.
	 *
	 * @return The canvas on which the board is drawn on.
	 */

	public Canvas getCanvas() {
		return this.canvas;
	}

	@SuppressWarnings("unused")
	public void showMessage(String message) {
		new MessageDialog(this, message);
	}

	public void muteAudio() {
		this.audio.muteAudio();
	}

	public void unmuteAudio() {
		this.audio.unmuteAudio();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new Frame();
	}

}
