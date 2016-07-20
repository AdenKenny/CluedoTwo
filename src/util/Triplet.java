package util;

/**
 * A class representing a Person, a Weapon, and a Room. This makes up
 * a guess or a suggestion towards resolving the murder.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Triplet {

	private final Token person;
	private final Token weapon;
	private final Room room;

	public Triplet(Token person, Token weapon, Room room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}
	
	public Card checkCards(Set<Player> players) {
		
	}

	public Token getPerson() {
		return this.person;
	}

	public Token getWeapon() {
		return this.weapon;
	}

	public Room getRoom() {
		return this.room;
	}

}
