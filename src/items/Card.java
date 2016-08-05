package items;

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

	/**
	 * Checks to see if this card equals another card. i.e. their names
	 * are equal.
	 *
	 * @param card - The card which we are checking against.
	 * @return - boolean, true or false if they're equal.
	 */

	public boolean equalsCard(Card card) {
		return card.getName().equals(this.name);
	}



	/**
	 * Returns the string representing the name of the card.
	 * @return - String, the name field.
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}


}
