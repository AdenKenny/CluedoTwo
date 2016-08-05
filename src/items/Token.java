package items;

import location.Location;

/**
 * A class representing a Token on the board. A token is any item on the game board
 * such as a person or a weapon.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Token {

	private String name;
	private String display;
	private Location location;
	private boolean isCharacter;

	/**
	 * The constructor takes the name of the token and the starting Location.
	 *
	 * @param name -  A string representing the name of the Token.
	 * @param location - The starting Location of the Token.
	 */

	public Token(String name, Location location, boolean isCharacter, String display) {
		this.name = name;
		location.addToken(this);
		this.isCharacter = isCharacter;
		this.display = display;
	}

	//Getters and Setters.

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	/**
	 * Removes the token from the old location and set the token location.
	 * @param location - the destination
	 */
	public void setLocation(Location location) {
		if (this.location != null) {
			this.location.removeToken(this);
		}
		this.location = location;
	}

	public String getDisplay() {
		return this.display;
	}

	public boolean isCharacter() {
		return this.isCharacter;
	}
}
