package util;

/**
 * A class representing a Person, a Weapon, and a Room. This makes up
 * a guess or a suggestion towards resolving the murder.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Triplet {

	private Person personToken;
	private Weapon weaponToken;
	private Room roomLocation;

	public Triplet(Person personToken, Weapon weaponToken, Room roomLocation) {
		this.personToken = personToken;
		this.weaponToken = weaponToken;
		this.roomLocation = roomLocation;
	}

	public Person getPersonToken() {
		return this.personToken;
	}

	public void setPersonToken(Person personToken) {
		this.personToken = personToken;
	}

	public Weapon getWeaponToken() {
		return this.weaponToken;
	}

	public void setWeaponToken(Weapon weaponToken) {
		this.weaponToken = weaponToken;
	}

	public Room getRoomLocation() {
		return this.roomLocation;
	}

	public void setRoomLocation(Room roomLocation) {
		this.roomLocation = roomLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.personToken == null) ? 0 : this.personToken.hashCode());
		result = prime * result + ((this.roomLocation == null) ? 0 : this.roomLocation.hashCode());
		result = prime * result + ((this.weaponToken == null) ? 0 : this.weaponToken.hashCode());
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
		if (this.personToken == null) {
			if (other.personToken != null)
				return false;
		} else if (!this.personToken.equals(other.personToken))
			return false;
		if (this.roomLocation == null) {
			if (other.roomLocation != null)
				return false;
		} else if (!this.roomLocation.equals(other.roomLocation))
			return false;
		if (this.weaponToken == null) {
			if (other.weaponToken != null)
				return false;
		} else if (!this.weaponToken.equals(other.weaponToken))
			return false;
		return true;
	}

}
