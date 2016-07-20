package cluedo;

public class Card {
	private String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public boolean equalsCard(Card card) {
		return card.getName().equals(this.name);
	}
	
	public String getName() {
		return this.name;
	}
}
