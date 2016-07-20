package util;

public class Token {

	protected String name;
	protected Location location;

	public Token(String name, Location location) {
		this.name = name;
		this.location = location;
	}

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
