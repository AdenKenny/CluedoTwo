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

}
