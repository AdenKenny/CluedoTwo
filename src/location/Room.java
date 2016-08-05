package location;

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
	public String getDisplay() {
		if (this.tokens.isEmpty()) {
			return null;
		}
		String display = "";
		for (Token t : this.tokens) {
			display += t.getDisplay();
		}
		return display;
	}

}
