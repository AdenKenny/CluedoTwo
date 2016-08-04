package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import cluedo.Cluedo;

public class StartingTest {

	@Test
	public void testEntertainment() {
		Cluedo game = new Cluedo();
		System.out.println("Was the game fun?");
		String reply = "";
		try {
			Scanner in = new Scanner(System.in);
			reply = in.nextLine();
		}
		catch(RuntimeException e) {
			fail();
		}
		assertEquals(reply, "yes");
	}

}
