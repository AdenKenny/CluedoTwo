package util;

import java.util.Map;

public class Room extends Location{

	private String name;

	public Room(Map<String, Location> adjacent) {
		super(adjacent);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
