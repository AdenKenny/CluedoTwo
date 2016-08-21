package location;

import java.util.HashMap;


/**
 * A class representing a square on the Cluedo board.
 *
 * @author Aden Kenny and Simon Pope.
 */

public class Square extends Location {

	private int x;
	private int y;

	public Square(int x, int y) {
		super(new HashMap<String, Location>());
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}
