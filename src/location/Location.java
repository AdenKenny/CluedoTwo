package location;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import items.Token;

/**
 * An abstract class representing a location on the board. Both 'Room' and 'Square'
 * classes extend this.
 *
 * @author Aden Kenny and Simon Pope.
 */

public abstract class Location {

	private Map<String, Location> adjacent; //A map of adjacent locations to this one.
	protected Set<Token> tokens; //A set of the tokens contained at this location.

	/**
	 * Contructor takes a Map of adjacent locations.
	 * @param adjacent A Map of adjacent locations.
	 */

	public Location(Map<String, Location> adjacent) {
		this.adjacent = adjacent;
		this.tokens = new HashSet<>();
	}

	/**
	 * Adds an adjacent location, which means people can move to that location from
	 * this one.
	 * @param dir - the String associated with that move
	 * @param adj - the adjacent Location
	 */
	public void addAdjacent(String dir, Location adj) {
		this.adjacent.put(dir, adj);
	}

	/**
	 * Returns all of the adjacent locations which can be moved to from this location.
	 * @return Map of Strings to Locations
	 */
	public Map<String, Location> getAdjacent() {
		return this.adjacent;
	}

	/**
	 * Adds a token to the set of tokens at this location and sets the tokens
	 * location to this.
	 *
	 * @param t - The token to be added.
	 */

	public void addToken(Token t) {
		this.tokens.add(t);
		t.setLocation(this);
	}

	/**
	 * Removes a token from the set of tokens at this location.
	 *
	 * @param t - The token to be removed.
	 */

	public void removeToken(Token t) {
		this.tokens.remove(t);
	}

	/**
	 * Checks to see if token is contained at this Location.
	 *
	 * @param t - The token to be checked for at this Location.
	 * @return
	 */

	public boolean containsToken(Token t) {
		return this.tokens.contains(t) ? true : false;
	}

	/**
	 * Gets a set of tokens contained at this Location.
	 *
	 * @return The set of tokens at this Location.
	 */

	public Set<Token> getTokens() {
		return this.tokens;
	}

	/**
	 * Returns the number of tokens at this Location.
	 *
	 * @return The size of the set of Tokens.
	 */

	public int getNumbTokens() {
		return this.tokens.size();
	}
}
