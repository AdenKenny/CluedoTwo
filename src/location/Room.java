package location;

import java.awt.Image;
import java.util.Map;

import items.Token;


/**
 * A class representing a room on the Cluedo board.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Room extends Location {

	private String name;

	public Room(String name, Map<String, Location> adjacent) {
		super(adjacent);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a string of the tokens in the room.
	 *
	 * @return - A String representing the tokens in the room.
	 */
	public Image[] getDisplay() {
		Image[] display = new Image[this.tokens.size()];
		int i = 0;
		for (Token t : this.tokens) {
			display[i++] = t.getImage();
		}
		return display;
	}

}
