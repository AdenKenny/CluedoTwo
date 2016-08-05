package location;

import java.util.HashMap;
import java.util.Map;

public class Square extends Location {
	public Square(Map<String, Location> adjacent) {
		super(adjacent);
	}
	
	public Square() {
		super(new HashMap<String, Location>());
	}
}
