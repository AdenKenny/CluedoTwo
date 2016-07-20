package cluedo;

import java.util.Set;

import util.Room;
import util.Triplet;

public class Cluedo {

	private Triplet murderInfo; //Triplet of the actual murder details.
	private Set<Room> rooms; //Set of the rooms in the game.

	public Cluedo() {

	}

	private boolean checkMove() {
		return false;

	}

	public static void main(String[] args) {
		new Cluedo();
	}

}
