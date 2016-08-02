package util;

import java.util.Map;

/**
 * A class representing a room on the Cluedo board.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

public class Room extends Location{

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

}
