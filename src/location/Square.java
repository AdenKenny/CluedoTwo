package location;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a square on the Cluedo board.
 *
 * @author Aden Kenny and Simon Pope.
 *
 */

public class Square extends Location {
	public Square(Map<String, Location> adjacent) {
		super(adjacent);
	}

	public Square() {
		super(new HashMap<String, Location>());
	}
}
