package cluedo;

/**
 * A class representing a card that is held in a player's hand or in a triplet.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}


}
