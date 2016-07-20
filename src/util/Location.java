package util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Location {

	protected Map<String, Location> adjacent;
	protected Set<Token> tokens;

	public Location(Map<String, Location> adjacent) {
		this.adjacent = adjacent;
		this.tokens = new HashSet<>();
	}

	public void addToken(Token t) {
		this.tokens.add(t);
	}

	public void removeToken(Token t) {
		this.tokens.remove(t);
	}

	public boolean containsToken(Token t) {
		return this.tokens.contains(t) ? true : false;
	}

	public Set<Token> getTokens() {
		return this.tokens;
	}

	public boolean checkMove(Location l) {
		// TODO Auto-generated method stub
		return false;
	}

}
