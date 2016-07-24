package cluedo;

import java.util.Set;

import util.Board;
import util.Triplet;

public class Cluedo {

	private Triplet murderInfo; //Triplet of the actual murder details.
	private Board board; //The game board.
	private Set<Player> players;

	public Cluedo() {
	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
