package items;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.Canvas;
import gui.Frame;
import location.Location;


/**
 * A class representing a Token on the board. A token is any item on the game board such as a person or a weapon.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Token {

	private String name;
	private Image image;
	private Location location;
	private boolean isCharacter;

	/**
	 * The constructor takes the name of the token and the starting Location.
	 *
	 * @param name
	 *            A string representing the name of the Token.
	 * @param location
	 *            The starting Location of the Token.
	 */

	public Token(String name, Location location, boolean isCharacter, String imageName) {
		this.name = name;
		location.addToken(this);
		this.isCharacter = isCharacter;
		this.image = Canvas.loadImage(imageName);
	}

	// Getters and Setters.

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return this.location;
	}

	/**
	 * Removes the token from the old location and set the token location.
	 *
	 * @param location
	 *            The destination
	 */
	public void setLocation(Location location) {
		if (this.location != null) {
			this.location.removeToken(this);
		}
		this.location = location;
	}

	public Image getImage() {
		return this.image;
	}

	public boolean isCharacter() {
		return this.isCharacter;
	}

}
