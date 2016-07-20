package cluedo;

import java.util.Set;

import util.Token;


public class Player {
	private boolean inGame; //Hasn't been eliminated.
	private Token personToken;
	private Set<Card> hand;
	
	public Player(Token personToken) {
		this.personToken = personToken;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public boolean checkHand(Token token) {
		for (Card card : hand) {
			if (card.equalsToken(token)) {
				return true;
			}
		}
		return false;
	}
}
