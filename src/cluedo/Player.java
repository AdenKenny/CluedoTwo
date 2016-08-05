package cluedo;

import java.util.HashSet;
import java.util.Set;

import items.Card;
import items.Token;

/**
 * A class representing a player in the game. Stores information such as the cards in the player's
 * hand, the status of the player (Eliminated from the game or not), and the token which represents
 * the player on the board.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Player {
	private boolean status; // Hasn't been eliminated.
	private String username;
	private Token personToken;
	private Set<Card> hand; // Represents a player's hand.

	/**
	 * Creates a player object, takes the player's username and a token representing the character
	 * they picked.
	 *
	 * @param username
	 *            - A string representing the player's username.
	 * @param personToken
	 *            - A token representing the character the player selected.
	 */

	public Player(String username, Token personToken) {
		this.status = true;
		this.username = username;
		this.personToken = personToken;
		this.hand = new HashSet<>();
	}

	/**
	 * Returns true or false based on if the player is still participating in the game. i.e. the
	 * player has not been eliminated from the game.
	 *
	 * @return - A boolean based on if the player has lost or not.
	 */

	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Returns the player's username, used for addressing the players and for
	 * other player related methods.
	 *
	 * @return - A string representing this player's username.
	 */

	public String getUsername() {
		return this.username;
	}

	/**
	 * Returns a player's token, so related to their position on the board itself.
	 *
	 * @return - A token representing a player.
	 */

	public Token getToken() {
		return this.personToken;
	}

	/**
	 * Sets the player's game status, either in or out of the game. Called when a player loses the
	 * game.
	 *
	 * @param status
	 *            - The boolean that is passed to the method.
	 */

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Adds a card to this player's hand.
	 *
	 * @param card
	 *            - The card that is added.
	 */

	public void addCard(Card card) {
		this.hand.add(card);
	}

	/**
	 * Checks to see if the player's hand contains a card.
	 *
	 * @param other
	 *            - The card to be checked if the player's hand contains.
	 * @return Boolean representing if the card is contained.
	 */

	public boolean checkHand(Card other) {
		for (Card card : this.hand) { // Iterate through hand.
			if (card.equalsCard(other)) {
				return true; // Does contain other card.
			}
		}
		return false; // Does not.
	}

	/**
	 * Returns the hand of the player, with one card per line.
	 * @return String
	 */
	public String handString() {
		String handString = "";
		for (Card c : this.hand) {
			handString += c.getName() + "\n";
		}
		return handString;
	}

}
