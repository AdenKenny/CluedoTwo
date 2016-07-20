package cluedo;

import util.Token;

public class Card {
	private String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public boolean equalsToken(Token token) {
		return token.getName().equals(this.name);
	}
	
	public String getName() {
		return this.name;
	}
}
