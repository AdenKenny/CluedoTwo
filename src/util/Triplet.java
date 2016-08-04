package util;

import java.util.List;

import cluedo.Card;
import cluedo.Player;

/**
 * A class representing a Person, a Weapon, and a Room. This makes up
 * a guess or a suggestion towards resolving the murder.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Triplet {

	private final Card person;
	private final Card weapon;
	private final Card room;

	public Triplet(Card person, Card weapon, Card room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}

	/**
	 * Checks the player's card to see if they can refute a guess.
	 *
	 * @param players The set of players in the game.
	 * @return A string representing the outcome of the refutation.
	 */

	public Pair<Boolean, String> checkCards(List<Player> players) {
		for (Player player : players) {
			if (player.checkHand(this.person)) {
				String tmp = player.getUsername() + " refuted " + this.person.getName() + ".";
				return new Pair<>(true, tmp); //This was a wrong guess and someone can refute.
			}

			if (player.checkHand(this.weapon)) {
				String tmp = player.getUsername() + " refuted " + this.weapon.getName() + ".";
				return new Pair<>(true, tmp);
			}

			if (player.checkHand(this.room)) {
				String tmp = player.getUsername() + " refuted " + this.room.getName() + ".";
				return new Pair<>(true, tmp);
			}
		}

		return new Pair<>(false, "No one could refute this.");
	}

	@Override
	public String toString() {
		return "Triplet [person=" + this.person.getName() + ", weapon=" +
		this.weapon.getName() + ", room=" + this.room.getName() + "]";
	}

	/**
	 * Checks to see if the contents of one triplet equals another.
	 *
	 * @param other The triplet to compare this to.
	 * @return True or false based on the contents of the two triplets.
	 */

	public boolean equalsTriplet(Triplet other) {
		return this.person.equals(other.getPerson())
				&& this.weapon.equals(other.getWeapon())
				&& this.room.equals(other.getRoom());
	}

	/**
	 * Checks to see if a player's hand contains any of the cards in this
	 * triplet. Used to check if a player suggests a card that is in his hand.
	 *
	 * @param player The player who's hand will be checked.
	 * @return Boolean based on if the player holds a card in this triplet.
	 */

	public boolean containsPlayer(Player player) {

		if(player.checkHand(this.person)) {
			return true;
		}

		else if(player.checkHand(this.room)) {
			return true;
		}

		else if(player.checkHand(this.weapon)) {
			return true;
		}

		return false;
	}


	/**
	 * Returns the person card in this triplet.
	 *
	 * @return - A card representing the person in this triplet.
	 */

	public Card getPerson() {
		return this.person;
	}

	/**
	 * Returns the weapon card in this triplet.
	 *
	 * @return - A card representing the weapon in this triplet.
	 */

	public Card getWeapon() {
		return this.weapon;
	}

	/**
	 * Returns the room card in this triplet.
	 *
	 * @return - A card representing the room in this triplet.
	 */

	public Card getRoom() {
		return this.room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.person == null) ? 0 : this.person.hashCode());
		result = prime * result + ((this.room == null) ? 0 : this.room.hashCode());
		result = prime * result + ((this.weapon == null) ? 0 : this.weapon.hashCode());
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
		Triplet other = (Triplet) obj;
		if (this.person == null) {
			if (other.person != null)
				return false;
		} else if (!this.person.equals(other.person))
			return false;
		if (this.room == null) {
			if (other.room != null)
				return false;
		} else if (!this.room.equals(other.room))
			return false;
		if (this.weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!this.weapon.equals(other.weapon))
			return false;
		return true;
	}

}
