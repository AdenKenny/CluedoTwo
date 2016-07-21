package util;

/**
 * A class representing a Token on the board. A token is any item on the game board
 * such as a person or a weapon.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Token {

	protected String name;
	protected Location location;

	/**
	 * The constructor takes the name of the token and the starting Location.
	 *
	 * @param name A string representing the name of the Token.
	 * @param location The starting Location of the Token.
	 */

	public Token(String name, Location location) {
		this.name = name;
		this.location = location;
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

	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Moves the token to a new location, a Location is passed.
	 * Removes this token from the old location then adds it to
	 * the new location.
	 *
	 * @param location The location this token is being moved to.
	 */

	public void move(Location location) {
		this.location.removeToken(this);
		this.location = location;
		this.location.addToken(this);
	}
}
