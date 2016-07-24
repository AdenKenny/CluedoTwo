package cluedo;

import java.util.Set;

import util.Board;
import util.Location;
import util.Token;
import util.Triplet;

public class Cluedo {

	private Triplet murderInfo; //Triplet of the actual murder details.
	private Board board; //The game board.
	private Set<Player> players;

	public Cluedo() {
	}

	/**
	 * Gets a random number between 1 and 6 representing a dice roll
	 * for moving around the board.
	 *
	 * @return An int between 1 and 6.
	 */

	public int rollDice() {
		return (int)(Math.random() * 6 + 1);
	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
