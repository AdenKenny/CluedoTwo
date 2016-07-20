package cluedo;

import java.util.HashSet;
import java.util.Set;

import util.Token;

/**
 * A class representing a player in the game. Stores information such as
 * the cards in the player's hand, the status of the player (Eliminated from
 * the game or not), and the token which represents the player on the board.
 * 
 * @author Aden Kenny and Simon Pope.
 *
 */

public class Player {
	private boolean status; //Hasn't been eliminated.
	private String username;
	private Token personToken;
	private Set<Card> hand;
	
	public Player(String username, Token personToken) {
		this.status = true;
		this.username = username;
		this.personToken = personToken;
		this.hand = new HashSet<>();
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Token getToken() {
		return this.personToken;
	}
	
	/**
	 * Adds a card to this player's hand.
	 * 
	 * @param card The card that is added.
	 */
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * Checks to see if the player's hand contains a card.
	 * 
	 * @param other The card to be checked if the player's hand contains.
	 * @return Boolean representing if the card is contained.
	 */
	
	public boolean checkHand(Card other) {
		for (Card card : this.hand) { //Iterate through hand.
			if (card.equalsCard(other)) {
				return true; //Does contain other card.
			}
		}
		return false; //Does not.
	}
}
