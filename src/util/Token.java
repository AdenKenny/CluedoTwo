package util;

/**
 * A class representing a Token on the board. A token is any item on the game board
 * such as a person or a weapon.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Token {

	protected String name;
	String display;
	protected Location location;
	protected boolean isCharacter;

	/**
	 * The constructor takes the name of the token and the starting Location.
	 *
	 * @param name A string representing the name of the Token.
	 * @param location The starting Location of the Token.
	 */

	public Token(String name, Location location, boolean isCharacter, String display) {
		this.name = name;
		location.addToken(this);
		this.isCharacter = isCharacter;
		this.display = display;
	}
	
	public boolean moveAdjacent(String dir) {
		Location newLoc = this.location.getAdjacent().get(dir);
		if (newLoc == null) {
			return false;
		}
		newLoc.addToken(this);
		return true;
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
		if (this.location != null) {
			this.location.removeToken(this);
		}
		this.location = location;
	}

	public boolean getIsCharacter() {
		return this.isCharacter;
	}

	public void setIsCharacter(boolean isCharacter) {
		this.isCharacter = isCharacter;
	}

	public String getDisplay() {
		return display;
	}
	
	public boolean isCharacter() {
		return isCharacter;
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
