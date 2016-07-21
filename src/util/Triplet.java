package util;

import java.util.Set;

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

	public String checkCards(Set<Player> players) {
		for (Player player : players) {
			if (player.checkHand(this.person)) {
				return player.getUsername() + " refuted " + this.person.getName() + ".";
			}
			if (player.checkHand(this.weapon)) {
				return player.getUsername() + " refuted " + this.weapon.getName() + ".";
			}
			if (player.checkHand(this.room)) {
				return player.getUsername() + " refuted " + this.room.getName() + ".";
			}
		}

		return null;
	}

	/**
	 * Checks to see if the contents of one triplet equals another.
	 *
	 * @param other The triplet to compare this to.
	 * @return True or false based on the contents of the two triplets.
	 */

	/*
	 * This could, and probably should just be a hashCode() and equals().
	 */

	public boolean equalsTriplet(Triplet other) {
		return this.person.equals(other.getPerson())
				&& this.weapon.equals(other.getWeapon())
				&& this.room.equals(other.getRoom());
	}


	public Card getPerson() {
		return this.person;
	}

	public Card getWeapon() {
		return this.weapon;
	}

	public Card getRoom() {
		return this.room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
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
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}

}
